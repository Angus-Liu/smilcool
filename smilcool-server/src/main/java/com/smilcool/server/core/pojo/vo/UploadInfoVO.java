package com.smilcool.server.core.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Angus
 * @date 2019/5/19
 */
@Data
@AllArgsConstructor
public class UploadInfoVO {
    /**
     * 文件名
     */
    private String name;

    /**
     * 文件大小
     */
    private String size;

    /**
     * 文件 URL
     */
    private String url;
}
