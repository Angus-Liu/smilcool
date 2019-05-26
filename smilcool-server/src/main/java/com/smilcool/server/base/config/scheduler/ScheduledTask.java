package com.smilcool.server.base.config.scheduler;

import cn.hutool.json.JSONUtil;
import com.smilcool.server.base.config.elasticsearch.document.ArticleDocument;
import com.smilcool.server.base.config.elasticsearch.repository.ArticleRepository;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.pojo.form.ArticleQueryForm;
import com.smilcool.server.core.pojo.po.Article;
import com.smilcool.server.core.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务配置
 *
 * @author Angus
 * @date 2019/5/16
 */
@Slf4j
@Configuration
@EnableScheduling
public class ScheduledTask {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 同步 MySQL 到 Elasticsearch
     */
    @Scheduled(cron = "0 */1 * * * *")
    public void syncMySqlToElasticsearch() {
        // 获取需同步数据更新时间段
        // TODO 2019/5/26 updateTimeEnd 字段每次应持久化存储
        LocalDateTime now = LocalDateTime.now();
        String updateTimeStart = now.minusMinutes(1).format(DATE_TIME_FORMATTER);
        String updateTimeEnd = now.format(DATE_TIME_FORMATTER);
        log.info("开始同步 MySQL 到 Elasticsearch，同步数据更新时间段：[{}] - [{}]", updateTimeStart, updateTimeEnd);
        // 查询该时间端 MySQL 中更新过的数据
        ArticleQueryForm form = ArticleQueryForm.builder()
                .updateTimeStart(updateTimeStart)
                .updateTimeEnd(updateTimeEnd)
                .build();
        List<Article> articleList = articleService.listArticle(form);
        if (articleList.size() > 0) {
            // 进行同步
            List<ArticleDocument> articleDocumentList = new ArrayList<>();
            articleList.forEach(article -> {
                ArticleDocument articleDocument = BeanUtil.copyProp(article, ArticleDocument.class);
                // 将以 JSON 数组形式存储的 tags 字符串字段转化为 List<String> 类型
                articleDocument.setTagList(JSONUtil.parseArray(article.getTags()).toList(String.class));
                articleDocumentList.add(articleDocument);
                // TODO 2019/5/26 根据 article deleted 字段判断是否需要删除
            });
            articleRepository.saveAll(articleDocumentList);
        }
        log.info("结束同步 MySQL 到 Elasticsearch，同步数据更新时间段：[{}] - [{}]，共同步 {} 条数据",
                updateTimeStart, updateTimeEnd, articleList.size());
    }
}
