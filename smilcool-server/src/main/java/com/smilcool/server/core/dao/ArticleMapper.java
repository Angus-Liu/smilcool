package com.smilcool.server.core.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.po.Article;
import com.smilcool.server.core.pojo.vo.ArticleLatestCommentVO;
import com.smilcool.server.core.pojo.vo.ArticleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    /* 以下是自定义内容 */

    ArticleVO selectArticleVOByPrimaryKey(Integer id);

    Page<ArticleVO> selectArticleVOByCondition(Page page, @Param("article") Article condition);

    List<ArticleLatestCommentVO> selectArticleLatestCommentVO();
}