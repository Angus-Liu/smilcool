package com.smilcool.server.core.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Angus
 * @date 2019/3/20
 */
@Data
public class UserVO {
    private Integer id;

    private String username;

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

    private Integer state;

    private String remark;

    /**
     * JsonFormat：按照指定格式序列化日期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /**
     * 角色信息
     */
    private List<RoleVO> roleList;
}
