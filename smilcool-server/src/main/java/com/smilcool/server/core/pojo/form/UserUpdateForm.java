package com.smilcool.server.core.pojo.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Angus
 * @date 2019/5/25
 */
@Data
public class UserUpdateForm {
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
}
