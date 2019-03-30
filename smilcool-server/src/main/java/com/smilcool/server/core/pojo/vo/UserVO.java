package com.smilcool.server.core.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smilcool.server.common.util.serializer.UserStateSerializer;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

/**
 * @author Angus
 * @date 2019/3/20
 */
@Data
public class UserVO {
    private Integer id;

    private String username;

    /**
     * JsonProperty(access = WRITE_ONLY)：防止序列化该字段
     */
    @JsonProperty(access = WRITE_ONLY)
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

    private String email;

    /**
     * JsonSerialize：使用指定的 Serializer 序列化该字段
     */
    @JsonSerialize(using = UserStateSerializer.class)
    private Integer state;

    private String remark;

    /**
     * JsonFormat：按照指定格式序列化日期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Boolean isDel;

    /**
     * 角色信息（角色名集合）
     */
    private List<String> roles;

    /**
     * 权限信息（权限名集合）
     */
    private List<String> permissions;
}
