package com.smilcool.server.core.service.impl;

import com.smilcool.server.base.config.netty.enums.MessageState;
import com.smilcool.server.core.dao.MessageMapper;
import com.smilcool.server.core.pojo.po.Message;
import com.smilcool.server.core.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/8
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Message addMessage(Message message) {
        messageMapper.insertSelective(message);
        return messageMapper.selectByPrimaryKey(message.getId());
    }

    @Override
    public List<Message> getUnsignedMessageList(Integer receiveUserId) {
        List<Message> unsignedMessageList = messageMapper
                .selectByReceiveUserIdAndState(receiveUserId, MessageState.UNSIGNED.state);
        return unsignedMessageList;
    }

    @Override
    public void signMessage(Integer id) {
        messageMapper.updateStateById(id, MessageState.SIGNED.state);
    }
}
