package com.smilcool.server.common.enums;

/**
 * 字典类型枚举
 *
 * @author Angus
 * @date 2019/5/12
 */
public enum DicTypeEnum {

    /**
     * 文章类别字典
     */
    ARTICLE_CATEGORY("文章类别", "article-category"),

    /**
     * 文件类别字典
     */
    FILE_CATEGORY("文件类别", "file-category"),

    /**
     * 失物寻物类别字典
     */
    LOST_FOUND_CATEGORY("失物寻物类别", "lost-found-category"),

    /**
     * 二手交易类别字典
     */
    SECOND_HAND_CATEGORY("二手交易类别", "second-hand-category"),

    /**
     * 动态类别字典
     */
    MOMENT_CATEGORY("动态类别", "moment-category");

    /**
     * 字典类型名
     */
    public final String name;

    /**
     * 字典类型码
     */
    public final String code;

    DicTypeEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
