package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.pojo.form.ArticleAddForm;
import com.smilcool.server.core.pojo.po.Article;
import com.smilcool.server.core.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Angus
 * @date 2019/4/12
 */
@Api(description = "文章接口", tags = {"2.2"})
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @ApiOperation("文章添加")
    @PostMapping("/article")
    public Result<Article> addArticle(@RequestBody @Valid ArticleAddForm articleAddForm) {
        Article article = articleService.addArticle(articleAddForm);
        return Result.success(article);
    }

    @ApiOperation("文章列表")
    @GetMapping("/article")
    public Result<List<Article>> getArticleList() {
        List<Article> articleList = articleService.getArticleList();
        return Result.success(articleList);
    }

    @ApiOperation("文章信息")
    @GetMapping("/article/{id}")
    public Result<Article> getArticleList(@PathVariable Integer id) {
        Article article = articleService.getArticle(id);
        return Result.success(article);
    }

}
