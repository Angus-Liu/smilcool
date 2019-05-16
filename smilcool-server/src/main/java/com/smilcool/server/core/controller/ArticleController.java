package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.pojo.bo.ArticleBO;
import com.smilcool.server.core.pojo.form.ArticleAddForm;
import com.smilcool.server.core.pojo.page.ArticlePage;
import com.smilcool.server.core.pojo.po.Article;
import com.smilcool.server.core.pojo.vo.ArticleInfo;
import com.smilcool.server.core.pojo.vo.ArticleVO;
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
@Api(tags = "2.2", description = "文章接口")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @ApiOperation("文章添加")
    @PostMapping
    public Result<Article> addArticle(@RequestBody @Valid ArticleAddForm articleAddForm) {
        Article article = articleService.addArticle(articleAddForm);
        return Result.success(article);
    }

    @ApiOperation("文章列表")
    @GetMapping
    public Result<List<ArticleVO>> getArticleList() {
        List<ArticleVO> articleList = articleService.getArticleList();
        return Result.success(articleList);
    }

    @ApiOperation("文章列表")
    @GetMapping("/bo")
    public Result<List<ArticleBO>> getArticleBOList() {
        List<ArticleBO> articleList = articleService.getArticleBOList();
        return Result.success(articleList);
    }

    @ApiOperation("文章信息")
    @GetMapping("/{id}")
    public Result<ArticleInfo> getArticleInfo(@PathVariable Integer id) {
        ArticleInfo articleInfo = articleService.getArticleInfo(id);
        return Result.success(articleInfo);
    }

    @ApiOperation("文章信息")
    @GetMapping("/{id}/page")
    public Result<ArticlePage> getArticlePage(@PathVariable Integer id) {
        ArticlePage articlePage = articleService.getArticlePage(id);
        return Result.success(articlePage);
    }

}
