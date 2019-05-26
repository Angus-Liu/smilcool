package com.smilcool.server.core.controller;

import com.smilcool.server.base.config.elasticsearch.document.ArticleDocument;
import com.smilcool.server.base.config.elasticsearch.mapper.HighlightResultMapper;
import com.smilcool.server.base.config.elasticsearch.repository.ArticleRepository;
import com.smilcool.server.core.service.TestService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.*;

/**
 * @author Angus
 * @date 2019/4/26
 */
@Api(tags = "4.1", description = "测试接口")
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @GetMapping("/cache")
    public Object getCache(@RequestParam String key) {
        Object res = testService.getCache(key);
        return res;
    }

    @PutMapping("/cache")
    public Object updateCache(@RequestParam String key) {
        Object res = testService.updateCache(key);
        return res;
    }

    @GetMapping("/search")
    public Page<ArticleDocument> search(String q, @RequestParam(defaultValue = "0") Integer page,
                                        @RequestParam(defaultValue = "5") Integer size) {
        // 创建一个SearchQuery对象
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                // 设置查询条件，此处可以使用QueryBuilders创建多种查询
                .withQuery(QueryBuilders.queryStringQuery(q))
                // 高亮配置
                .withHighlightFields(
                        new HighlightBuilder.Field("title"),
                        new HighlightBuilder.Field("brief"))
                // 还可以设置分页信息
                .withPageable(PageRequest.of(page, size))
                // 创建SearchQuery对象
                .build();
        return articleRepository.search(searchQuery);
    }

    @GetMapping("/search2")
    public Page<ArticleDocument> search2(String q, @RequestParam(defaultValue = "0") Integer page,
                                         @RequestParam(defaultValue = "5") Integer size) {
        // 创建一个SearchQuery对象
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                // 设置查询条件，此处可以使用 QueryBuilders 创建多种查询
                .withQuery(QueryBuilders.queryStringQuery(q))
                // 高亮配置
                .withHighlightBuilder(new HighlightBuilder().noMatchSize(100))
                // 高亮字段
                .withHighlightFields(
                        new HighlightBuilder.Field("title"),
                        new HighlightBuilder.Field("brief"))
                // 分页信息
                .withPageable(PageRequest.of(page, size))
                // 创建SearchQuery对象
                .build();
        return elasticsearchTemplate.queryForPage(searchQuery, ArticleDocument.class, new HighlightResultMapper());
    }
}
