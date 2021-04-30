package com.smilcool.server.core.service;

import com.smilcool.server.SmilcoolServerApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MailServiceTest extends SmilcoolServerApplicationTests {

    @Autowired
    private MailService mailService;

    @Test
    public void testSendSimpleMail() {
        mailService.sendSimpleMail(
                "youremail@example.com",
                "Here is my resume",
                "Hi Angus, Here is my resume, I Think you will like"
        );
    }

}