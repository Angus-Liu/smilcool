package com.smilcool.server.base.config.netty.handler;

import cn.hutool.json.JSONUtil;
import com.smilcool.server.base.config.netty.enums.MessageType;
import com.smilcool.server.core.pojo.po.Message;
import com.smilcool.server.core.service.MessageService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义处理消息的 Handler
 * TextWebSocketFrame 在 netty 中专门用于为 WebSocket 处理文本的对象，frame 是消息的载体
 */
@Slf4j
@Sharable
@Component
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    /**
     * 用于记录和管理所有客户端的 channel
     */
    private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 用于记录和管理 userId 与 channel 关系
     */
    private static ConcurrentHashMap<Integer, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    @Autowired
    private MessageService messageService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msgFrame) throws Exception {
        Channel channel = ctx.channel();
        // 获取客户端传输过来的消息
        Message receiveMessage = JSONUtil.toBean(msgFrame.text(), Message.class);
        log.debug("[{}] 发送消息：{}", channel.remoteAddress(), receiveMessage);
        // 根据消息类型，进行不同的业务
        switch (MessageType.of(receiveMessage.getType())) {
            // 连接
            case CONNECT: {
                // 建立连接时，将 channel 与 userId 进行进行关联
                Integer userId = receiveMessage.getSendUserId();
                userIdChannelMap.put(userId, channel);
                // TODO 2019/6/8 移除并关闭旧的关联
                log.debug("CONNECT 消息: userId = {}, channel = {}", userId, channel.id().asShortText());
                // 获取未签收信息并发送
                messageService.getUnsignedMessageList(userId).forEach(this::sendTo);
                break;
            }
            // 聊天
            case CHAT: {
                // 把聊天记录保存到数据库，并标记消息的签收状态为未签收
                log.debug("CHAT 消息: sendUserId = {}, receiveUserId = {}", receiveMessage.getSendUserId(), receiveMessage.getReceiveUserId());
                Message sendMessage = messageService.addMessage(receiveMessage);
                // 发送消息
                sendTo(sendMessage);
                break;
            }
            // 签收
            case SIGNED: {
                // 针对具体的消息进行签收，标记消息的签收状态为已签收，待签收消息 ID 在 content 字段中
                Integer signedMessageId = Integer.valueOf(receiveMessage.getContent());
                log.debug("SIGNED 消息: signedMessageId = {}", signedMessageId);
                messageService.signMessage(signedMessageId);
                break;
            }
            // 心跳：心跳消息，维持连接
            case KEEP_ALIVE: {
                log.debug("KEEP_ALIVE 消息: channel = {}", channel.id().asShortText());
                break;
            }
            default:
                log.debug("未知消息类型");
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 当客户端连接服务端之后，获取客户端的 channel，放到 ChannelGroup 中进行管理
        channelGroup.add(ctx.channel());
        log.debug("客户端连接: channel = {}", ctx.channel().id().asShortText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 当触发 handlerRemoved 方法时，从 ChannelGroup 移除对应的客户端的 channel
        channelGroup.remove(ctx.channel());
        log.debug("客户端断开: channel = {}", ctx.channel().id().asShortText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("发生异常", cause);
        // 关闭 channel 并移除
        ctx.channel().close();
        channelGroup.remove(ctx.channel());
    }

    /**
     * 发送消息
     *
     * @param sendMessage 待发送消息
     */
    private void sendTo(Message sendMessage) {
        Integer receiveUserId = sendMessage.getReceiveUserId();
        Optional.ofNullable(userIdChannelMap.get(receiveUserId))
                .ifPresent(receiveChannel -> {
                    if (channelGroup.contains(receiveChannel)) {
                        // 用户在线
                        // 注意，文本消息一定要是 TextWebSocketFrame 类型
                        receiveChannel.writeAndFlush(new TextWebSocketFrame(JSONUtil.toJsonStr(sendMessage)));
                        log.info("发送消息: receiveUserId - {}, receiveChannel - {}, message - {}",
                                receiveUserId, receiveChannel, sendMessage);
                    } else {
                        // 用户离线，移除关联
                        userIdChannelMap.remove(receiveUserId);
                    }
                });
    }
}
