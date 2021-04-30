package com.smilcool.server.core.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.base.config.elasticsearch.document.ArticleDocument;
import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.ArticleAddForm;
import com.smilcool.server.core.pojo.form.ArticleQueryForm;
import com.smilcool.server.core.pojo.po.Article;
import com.smilcool.server.core.pojo.vo.ArticleLatestCommentVO;
import com.smilcool.server.core.pojo.vo.ArticleVO;
import com.smilcool.server.core.pojo.vo.TagVO;
import com.smilcool.server.core.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 文章接口
 */
@AllArgsConstructor
@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    /**
     * 文章添加
     */
    @PostMapping
    public Result<Article> addArticle(@RequestBody @Valid ArticleAddForm articleAddForm) {
        Article article = articleService.addArticle(articleAddForm);
        return Result.success(article);
    }

    /**
     * 文章分页
     */
    @GetMapping("/page")
    public Result<Page<ArticleVO>> pageArticleVO(Page<?> page, ArticleQueryForm form) {
        Page<ArticleVO> articlePage = articleService.pageArticleVO(page, form);
        return Result.success(articlePage);
    }

    /**
     * 文章详情
     */
    @GetMapping("/{id}")
    public Result<ArticleVO> getArticleVO(@PathVariable Integer id) {
        ArticleVO articleVO = articleService.getArticleVO(id);
        return Result.success(articleVO);
    }

    /**
     * 获取最新评论
     */
    @GetMapping("/latest-comment")
    public Result<List<ArticleLatestCommentVO>> listArticleLatestCommentVO() {
        List<ArticleLatestCommentVO> latestCommentList = articleService.listArticleLatestCommentVO();
        return Result.success(latestCommentList);
    }

    /**
     * 获取热门标签
     */
    @GetMapping("/hot-tag")
    public Result<List<TagVO>> getHotTag() {
        List<TagVO> hotTagList = articleService.listHotTag();
        return Result.success(hotTagList);
    }

    /**
     * 文章搜索
     */
    @GetMapping("/search")
    public Result<org.springframework.data.domain.Page<ArticleDocument>>
    search(String q, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size) {
        org.springframework.data.domain.Page<ArticleDocument> articlePage = articleService.search(q, page, size);
        return Result.success(articlePage);
    }

    // TODO 2019/5/26 逻辑删除和修改功能
}
