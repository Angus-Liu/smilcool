package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.enums.DicTypeEnum;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.common.util.MockUtil;
import com.smilcool.server.core.dao.ArticleMapper;
import com.smilcool.server.core.pojo.bo.ArticleBO;
import com.smilcool.server.core.pojo.form.ArticleAddForm;
import com.smilcool.server.core.pojo.page.ArticlePage;
import com.smilcool.server.core.pojo.po.Article;
import com.smilcool.server.core.pojo.vo.*;
import com.smilcool.server.core.service.ArticleService;
import com.smilcool.server.core.service.CommentService;
import com.smilcool.server.core.service.ResourceService;
import com.smilcool.server.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Angus
 * @date 2019/4/12
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

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
        Article article = getArticle(id);
        return BeanUtil.copyProp(article, ArticleVO.class);
    }

    @Override
    public List<ArticleVO> getArticleList() {
        List<Article> articleList = articleMapper.select();
        return BeanUtil.copyProp(articleList, ArticleVO.class);
    }

    @Override
    public List<ArticleBO> getArticleBOList() {
        List<Article> articleList = articleMapper.select();
        List<ArticleBO> articleBOList = new ArrayList<>();
        articleList.forEach(article -> {
            ArticleBO articleBO = BeanUtil.copyProp(article, ArticleBO.class);
            // 发布用户
            articleBO.setUserVO(userService.getUserVO(article.getUserId()));
            // 资源信息
            articleBO.setResourceVO(resourceService.getResourceVO(article.getResourceId()));
            articleBOList.add(articleBO);
        });
        return articleBOList;
    }

    @Override
    public ArticleInfo getArticleInfo(Integer id) {
        // 获取文章
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article == null) {
            throw new SmilcoolException("文章不存在");
        }
        // 获取评论
        List<CommentVO> commentList = commentService.getCommentVOList(article.getResourceId());
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setArticle(BeanUtil.copyProp(article, ArticleVO.class));
        articleInfo.setCommentList(commentList);
        return articleInfo;
    }

    @Override
    public ArticlePage getArticlePage(Integer id) {
        // 获取文章
        ArticleVO articleVO = getArticleVO(id);
        // 获取发布用户
        UserVO userVO = userService.getUserVO(articleVO.getUserId());
        // 获取资源信息
        ResourceVO resourceVO = resourceService.getResourceVO(articleVO.getResourceId());
        // 获取评论
        List<CommentVO> commentVOList = commentService.getCommentVOList(articleVO.getResourceId());
        return ArticlePage.builder()
                .article(articleVO)
                .user(userVO)
                .resource(resourceVO)
                .commentList(commentVOList)
                .build();
    }
}
