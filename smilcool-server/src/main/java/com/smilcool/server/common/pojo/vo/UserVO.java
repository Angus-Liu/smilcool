package com.smilcool.server.common.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smilcool.server.common.util.serializer.UserStateSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author Angus
 * @date 2019/3/20
 */
@Data
public class UserVO {
    private Integer id;

    private String username;

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

    @JsonSerialize(using = UserStateSerializer.class)
    private Integer state;

    private String remark;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date updateTime;

    private Boolean isDel;
}
