package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.vo.UploadInfoVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Angus
 * @date 2019/5/19
 */
public interface LocalStorageService {
    UploadInfoVO upload(String type, MultipartFile file);
}
