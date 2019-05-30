package com.smilcool.server.core.service;

/**
 * @author Angus
 * @date 2019/5/27
 */
public interface MailService {
    void sendSimpleMail(String to, String subject, String content);
}
