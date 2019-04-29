package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.form.ArticleAddForm;
import com.smilcool.server.core.pojo.po.Article;
import com.smilcool.server.core.pojo.vo.ArticleInfo;
import com.smilcool.server.core.pojo.vo.ArticleVO;

import java.util.List;

/**
 * @author Angus
 * @date 2019/4/12
 */
public interface ArticleService {

    Article getArticle(Integer id);

    List<ArticleVO> getArticleList();

    Article addArticle(ArticleAddForm form);

    ArticleInfo getArticleInfo(Integer id);
}
