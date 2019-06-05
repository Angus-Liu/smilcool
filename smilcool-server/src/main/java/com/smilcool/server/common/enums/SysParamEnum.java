package com.smilcool.server.common.enums;

/**
 * @author Angus
 * @date 2019/6/4
 */
public enum SysParamEnum {

    /**
     * 上次同步 MySQL 到 Elasticsearch 的时间
     */
    LAST_SYNC_MYSQL_TO_ELASTICSEARCH_TIME("LAST_SYNC_MYSQL_TO_ELASTICSEARCH_TIME"),
    ;

    public final String name;

    SysParamEnum(String name) {
        this.name = name;
    }
}
