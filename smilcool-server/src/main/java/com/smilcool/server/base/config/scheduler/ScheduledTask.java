package com.smilcool.server.base.config.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 定时任务配置
 *
 * @author Angus
 * @date 2019/5/16
 */
@Configuration
@EnableScheduling
public class ScheduledTask {

//    @Scheduled(cron = "*/1 * * * * *")
    public void reportCurrentByCron() {
        System.out.println("Scheduling Tasks Examples By Cron: The time is now " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

}
