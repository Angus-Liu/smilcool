package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.vo.UploadInfoVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Angus
 * @date 2019/5/19
 */
public interface LocalStorageService {
    /**
     * 文件存储
     *
     * @param type 作为路径前缀，用以区分不同类型的文件
     * @param file 文件
     * @return
     */
    UploadInfoVO upload(String type, MultipartFile file);
}
