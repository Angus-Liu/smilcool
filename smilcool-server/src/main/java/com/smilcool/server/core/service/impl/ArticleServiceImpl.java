package com.smilcool.server.core.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.common.enums.DicTypeEnum;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.common.util.MockUtil;
import com.smilcool.server.core.dao.ArticleMapper;
import com.smilcool.server.core.pojo.form.ArticleAddForm;
import com.smilcool.server.core.pojo.form.ArticleQueryForm;
import com.smilcool.server.core.pojo.po.Article;
import com.smilcool.server.core.pojo.vo.ArticleLatestCommentVO;
import com.smilcool.server.core.pojo.vo.ArticleVO;
import com.smilcool.server.core.service.ArticleService;
import com.smilcool.server.core.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Angus
 * @date 2019/4/12
 */
@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ResourceService resourceService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Article addArticle(ArticleAddForm form) {
        // 获取当前登陆用户
        Integer currentUserId = MockUtil.currentUserId();
        // 添加资源，获取资源ID
        Integer resourceId = resourceService
                .addResource(currentUserId, DicTypeEnum.ARTICLE_CATEGORY.name, form.getArticleCategory());
        // 添加文章
        Article article = BeanUtil.copyProp(form, Article.class);
        article.setUserId(currentUserId);
        article.setResourceId(resourceId);
        articleMapper.insertSelective(article);
        return getArticle(article.getId());
    }

    @Override
    public Article getArticle(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article == null) {
            throw new SmilcoolException("文章不存在");
        }
        return article;
    }

    @Override
    public ArticleVO getArticleVO(Integer id) {
        ArticleVO articleVO = articleMapper.selectArticleVOByPrimaryKey(id);
        if (articleVO == null) {
            throw new SmilcoolException("文章不存在");
        }
        return articleVO;
    }

    @Override
    public List<ArticleVO> listArticleVO() {
        List<ArticleVO> articleList = articleMapper.selectArticleVO();
        return articleList;
    }

    @Override
    public Page<ArticleVO> pageArticleVO(Page page, ArticleQueryForm form) {
        Article condition = BeanUtil.copyProp(form, Article.class);
        return articleMapper.selectArticleVOByCondition(page, condition);
    }

    @Override
    public List<ArticleLatestCommentVO> listArticleLatestCommentVO() {
        return articleMapper.selectArticleLatestCommentVO();
    }
}
