package com.smilcool.server.core.pojo.form;

import lombok.Data;

/**
 * @author Angus
 * @date 2019/5/23
 */
@Data
public class LostFoundQueryForm {
    private Integer userId;
    private String lostFoundCategory;
}
