package com.smilcool.server.core.pojo.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Angus
 * @date 2019/5/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleQueryForm {
    /**
     * 发布用户
     */
    private Integer userId;

    /**
     * 文章类别
     */
    private String articleCategory;

    /**
     * 通用查询字段
     */
    private String general;

    /**
     * 更新时间开始点
     */
    private String updateTimeStart;

    /**
     * 更新时间结束点
     */
    private String updateTimeEnd;
}
