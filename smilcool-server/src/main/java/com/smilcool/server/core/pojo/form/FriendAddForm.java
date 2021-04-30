package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FriendAddForm {
    @NotNull(message = "朋友用户 ID 不能为空")
    private Integer friendUserId;
}
