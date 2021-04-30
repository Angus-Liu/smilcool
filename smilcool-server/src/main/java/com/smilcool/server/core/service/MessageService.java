package com.smilcool.server.core.service;

import com.smilcool.server.base.config.netty.enums.MessageState;
import com.smilcool.server.core.dao.MessageMapper;
import com.smilcool.server.core.pojo.po.Message;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageMapper messageMapper;

    public Message addMessage(Message message) {
        messageMapper.insertSelective(message);
        return messageMapper.selectByPrimaryKey(message.getId());
    }

    public List<Message> getUnsignedMessageList(Integer receiveUserId) {
        return messageMapper.selectByReceiveUserIdAndState(receiveUserId, MessageState.UNSIGNED.state);
    }

    public void signMessage(Integer id) {
        messageMapper.updateStateById(id, MessageState.SIGNED.state);
    }
}
