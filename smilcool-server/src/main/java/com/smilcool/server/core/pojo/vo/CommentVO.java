package com.smilcool.server.core.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smilcool.server.core.pojo.po.Comment;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Angus
 * @date 2019/4/23
 */
@Data
public class CommentVO {
    private Integer id;

    private Integer parentId;

    /**
     * 评论用户信息
     */
    private Integer userId;

    private UserVO user;

    private Integer resourceId;

    /**
     * 回复用户信息
     */
    private Integer replyUserId;

    private UserVO replyUser;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 子评论
     */
    private List<CommentVO> children;
}
