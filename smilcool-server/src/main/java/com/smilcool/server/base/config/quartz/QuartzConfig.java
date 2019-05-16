package com.smilcool.server.base.config.quartz;

/**
 * @author Angus
 * @date 2019/5/16
 */
public class QuartzConfig {
    // TODO 2019/5/16 使用 Quartz 定时将数据库文档更新到 Elasticsearch 或 Redis
    // 例如每五分钟一次，每次只更新，更新时间字段（update_time）在前五分钟到此刻的数据
    // 也可以不用定时任务，改用消息队列通知更新或删除（直接更新和删除可行吗？耦合性太强）

    // TODO 2019/5/16 SpringBoot 自带定时任务
}
