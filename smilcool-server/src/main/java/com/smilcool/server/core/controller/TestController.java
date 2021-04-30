package com.smilcool.server.core.controller;

import com.smilcool.server.base.config.elasticsearch.document.ArticleDocument;
import com.smilcool.server.base.config.elasticsearch.repository.ArticleRepository;
import com.smilcool.server.core.service.TestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.web.bind.annotation.*;

/**
 * 测试接口
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    private final ArticleRepository articleRepository;

    @GetMapping("/cache")
    public Object getCache(@RequestParam String key) {
        return testService.getCache(key);
    }

    @PutMapping("/cache")
    public Object updateCache(@RequestParam String key) {
        return testService.updateCache(key);
    }

    @GetMapping("/search")
    public Page<ArticleDocument> search(String q, @RequestParam(defaultValue = "0") Integer page,
                                        @RequestParam(defaultValue = "5") Integer size) {
        // 创建一个SearchQuery对象
        Query searchQuery = new NativeSearchQueryBuilder()
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
}
