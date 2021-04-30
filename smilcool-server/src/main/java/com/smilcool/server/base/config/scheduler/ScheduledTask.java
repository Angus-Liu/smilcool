package com.smilcool.server.base.config.scheduler;

import cn.hutool.json.JSONUtil;
import com.smilcool.server.base.config.elasticsearch.document.ArticleDocument;
import com.smilcool.server.base.config.elasticsearch.repository.ArticleRepository;
import com.smilcool.server.common.enums.SysParamEnum;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.pojo.form.ArticleQueryForm;
import com.smilcool.server.core.pojo.po.Article;
import com.smilcool.server.core.service.ArticleService;
import com.smilcool.server.core.service.SysParamService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@AllArgsConstructor
public class ScheduledTask {

    private final ArticleService articleService;
    private final ArticleRepository articleRepository;
    private final SysParamService sysParamService;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 同步 MySQL 到 Elasticsearch
     */
    @Scheduled(cron = "0 */1 * * * *")
    public void syncMySqlToElasticsearch() {
        // 从数据库获取上次更新的时间
        String updateTimeStart = sysParamService
                .getSysParam(SysParamEnum.LAST_SYNC_MYSQL_TO_ELASTICSEARCH_TIME)
                .getValue();
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
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
            articleList.forEach(article -> {
                if (article.getDeleted()) {
                    // 删除指定文章
                    articleRepository.deleteById(article.getId());
                } else {
                    // 更新指定文章
                    ArticleDocument articleDocument = BeanUtil.copyProp(article, ArticleDocument.class);
                    // 将以 JSON 数组形式存储的 tags 字符串字段转化为 List<String> 类型
                    articleDocument.setTagList(JSONUtil.parseArray(article.getTags()).toList(String.class));
                    articleRepository.save(articleDocument);
                }
            });
        }
        // 将本次更新时间存储到数据库
        sysParamService.updateSysParam(SysParamEnum.LAST_SYNC_MYSQL_TO_ELASTICSEARCH_TIME, updateTimeEnd);
        log.info("结束同步 MySQL 到 Elasticsearch，同步数据更新时间段：[{}] - [{}]，共同步 {} 条数据",
                updateTimeStart, updateTimeEnd, articleList.size());
    }
}
