package com.smilcool.server.core.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.ArticleAddForm;
import com.smilcool.server.core.pojo.form.ArticleQueryForm;
import com.smilcool.server.core.pojo.po.Article;
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
    public Result<List<ArticleVO>> getArticleVOList() {
        List<ArticleVO> articleList = articleService.listArticleVO();
        return Result.success(articleList);
    }

    @ApiOperation("文章分页")
    @GetMapping("/page")
    public Result<Page<ArticleVO>> pageArticleVO(Page page, ArticleQueryForm form) {
        Page<ArticleVO> articlePage = articleService.pageArticleVO(page, form);
        return Result.success(articlePage);
    }

    @ApiOperation("文章")
    @GetMapping("/{id}")
    public Result<ArticleVO> getArticleVO(@PathVariable Integer id) {
        ArticleVO articleVO = articleService.getArticleVO(id);
        return Result.success(articleVO);
    }
}
