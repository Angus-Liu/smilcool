package com.smilcool.server.core.service.impl;

import cn.hutool.core.io.FileUtil;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.core.pojo.vo.UploadInfoVO;
import com.smilcool.server.core.service.LocalStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Angus
 * @date 2019/5/19
 */
@Service
public class LocalStorageServiceImpl implements LocalStorageService {

    private static final double KB = 1024;
    private static final double MB = 1024 * KB;
    private static final double GB = 1024 * MB;

    @Value("${smilcool.local-storage}")
    private String localStorage;

    @Override
    public UploadInfoVO upload(String type, MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String fileSize = getFileSize(file.getSize());
        // TODO 2019/5/19 file 需要进行 Hash
        String subUrl = "/" + type + "/" + fileName;
        // 文件虚拟路径（配合 WebMvcConfig 相关配置，进行静态资源映射）
        String url = "/local-storage" + subUrl;
        // 文件本地存储真实路径
        String path = localStorage + subUrl;
        // 写入文件
        try {
            FileUtil.writeBytes(file.getBytes(), path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SmilcoolException("文件上传失败");
        }
        return UploadInfoVO.builder()
                .fileName(fileName)
                .fileSize(fileSize)
                .url(url)
                .build();
    }

    private String getFileSize(long size) {
        if (size < KB) {
            return size + "B";
        }
        if (size < MB) {
            return String.format("%.2fKB", size / KB);
        }
        if (size < GB) {
            return String.format("%.2fMB", size / MB);
        }
        return String.format("%.2fGB", size / GB);
    }
}
