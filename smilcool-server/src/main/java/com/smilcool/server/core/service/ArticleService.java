package com.smilcool.server.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.form.ArticleAddForm;
import com.smilcool.server.core.pojo.form.ArticleQueryForm;
import com.smilcool.server.core.pojo.po.Article;
import com.smilcool.server.core.pojo.vo.ArticleLatestCommentVO;
import com.smilcool.server.core.pojo.vo.ArticleVO;

import java.util.List;

/**
 * @author Angus
 * @date 2019/4/12
 */
public interface ArticleService {

    Article addArticle(ArticleAddForm form);

    Article getArticle(Integer id);

    ArticleVO getArticleVO(Integer id);

    Page<ArticleVO> pageArticleVO(Page page, ArticleQueryForm form);

    List<ArticleLatestCommentVO> listArticleLatestCommentVO();
}
