package com.smilcool.server.core.pojo.vo;

import cn.hutool.system.UserInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Angus
 * @date 2019/4/23
 */
@Data
public class CommentVO {
    private Integer resourceId;

    private Integer parentId;

    private Integer id;

    /**
     * 发布用户信息
     */
    private Integer userId;

    private UserSimpleInfo postUser;

    /**
     * 回复用户信息
     */
    private Integer replyUserId;

    private UserSimpleInfo replyUser;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 子评论
     */
    private List<CommentVO> children;
}
