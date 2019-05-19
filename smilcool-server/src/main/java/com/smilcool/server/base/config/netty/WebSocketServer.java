package com.smilcool.server.base.config.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Angus
 * @date 2018/12/13
 */
@Slf4j
@Component
public class WebSocketServer {

    private EventLoopGroup eventLoopGroup;
    private ServerBootstrap serverBootstrap;
    private ChannelFuture channelFuture;

    @Value("${smilcool.netty-port}")
    private Integer port;


    @Autowired
    public WebSocketServer(ChildChannelInitializer childChannelInitializer) {
        eventLoopGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap()
                .group(eventLoopGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(childChannelInitializer);
    }

    /**
     * PostConstruct 注解作用为 Spring Bean 初始化之后执行该方法
     * <p>
     * 注解 @PostConstruct 与 @PreDestroy 详解及实例
     * https://blog.csdn.net/wo541075754/article/details/52174900
     */
    @PostConstruct
    public void start() throws InterruptedException {
        channelFuture = serverBootstrap.bind(port).sync();
        log.debug("Netty 启动...");
    }

    /**
     * PreDestroy 注解作用为 Spring Bean 销毁前执行该方法
     */
    @PreDestroy
    public void destroy() throws InterruptedException {
        // 关闭 Server Channel
        channelFuture.channel().close().sync();
        // 关闭 EventLoopGroup
        eventLoopGroup.shutdownGracefully().sync();
        log.debug("Netty 关闭...");
    }
}
