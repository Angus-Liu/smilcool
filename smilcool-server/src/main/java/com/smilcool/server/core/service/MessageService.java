package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.po.Message;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/8
 */
public interface MessageService {

    Message addMessage(Message message);

    List<Message> getUnsignedMessageList(Integer receiveUserId);

    void signMessage(Integer id);
}
