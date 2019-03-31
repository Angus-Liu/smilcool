package com.smilcool.server.core.pojo.form;

import lombok.Data;

import java.util.Date;

/**
 * @author Angus
 * @date 2019/3/31
 */
@Data
public class UserSearchForm {

    private String username;

    private String nickname;

    private String sex;

    private String grade;

    private String college;

    private String major;

    private String phone;

    private String email;

    private Integer state;
}
