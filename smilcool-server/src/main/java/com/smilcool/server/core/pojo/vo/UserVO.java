package com.smilcool.server.core.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smilcool.server.common.util.serializer.UserStateSerializer;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

/**
 * @author Angus
 * @date 2019/3/20
 */
@Data
public class UserVO {
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     *  @JsonProperty(access = WRITE_ONLY) 防止序列化该字段
     */
    @JsonProperty(access = WRITE_ONLY)
    @NotBlank(message = "密码不能为空")
    private String password;

    private String nickname;

    private String avatar;

    private String sex;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String sign;

    private String intro;

    private String grade;

    private String college;

    private String major;

    private String phone;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

    @JsonSerialize(using = UserStateSerializer.class)
    private Integer state;

    private String remark;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date updateTime;

    private Boolean isDel;
}
