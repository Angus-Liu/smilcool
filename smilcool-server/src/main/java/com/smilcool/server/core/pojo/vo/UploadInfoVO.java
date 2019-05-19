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
    private String fileName;
    private String fileSize;
    private String url;
}
