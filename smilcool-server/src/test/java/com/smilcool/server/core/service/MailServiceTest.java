package com.smilcool.server.core.service;

import com.smilcool.server.SmilcoolServerApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author Angus
 * @date 2019/5/27
 */
public class MailServiceTest extends SmilcoolServerApplicationTests {

    @Autowired
    private MailService mailService;

    @Test
    public void testSendSimpleMail() {
        mailService.sendSimpleMail("youremail@example.com", "Here is my resume", "Hi Angus, Here is my resume, I Think you will like");
    }

}