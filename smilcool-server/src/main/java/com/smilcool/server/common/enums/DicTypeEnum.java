package com.smilcool.server.common.enums;

/**
 * @author Angus
 * @date 2019/5/12
 */
public enum DicTypeEnum {

    ARTICLE_CATEGORY("文章类别", "article-category"),
    FILE_CATEGORY("文件类别", "file-category"),
    SECOND_HAND_CATEGORY("二手交易类别", "second-hand-category"),
    ;

    public final String name;
    public final String code;

    DicTypeEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
