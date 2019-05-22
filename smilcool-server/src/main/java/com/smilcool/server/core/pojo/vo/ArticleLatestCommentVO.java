package com.smilcool.server.core.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 文章最新评论
 *
 * @author Angus
 * @date 2019/5/22
 */
@Data
public class ArticleLatestCommentVO {
    /**
     * 文章 ID
     */
    private String articleId;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 发布用户 ID
     */
    private Integer userId;

    /**
     * 发布用户昵称
     */
    private String userNickname;

    /**
     * 发布用户头像
     */
    private String userAvatar;

    /**
     * 评论 ID（可作为锚点）
     */
    private String commentId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date commentCreateTime;
}
