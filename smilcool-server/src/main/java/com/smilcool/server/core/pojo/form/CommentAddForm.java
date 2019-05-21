package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Angus
 * @date 2019/4/23
 */
@Data
public class CommentAddForm {
    private Integer parentId;

    @NotNull(message = "资源ID不能为空")
    private Integer resourceId;

    private Integer replyUserId;

    @NotBlank(message = "评论内容不能为空")
    private String content;
}
