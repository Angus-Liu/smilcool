package com.smilcool.server.core.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * 包含文章和评论信息
 *
 * @author Angus
 * @date 2019/4/29
 */
@Data
public class ArticleInfo {
    private ArticleVO article;
    private List<CommentVO> commentList;
    // TODO 获取对应的资源信息
}
