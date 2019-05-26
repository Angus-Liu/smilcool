package com.smilcool.server.core.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Angus
 * @date 2019/5/26
 */
@Data
@AllArgsConstructor
public class TagVO {
    private String name;
    private Long count;
}
