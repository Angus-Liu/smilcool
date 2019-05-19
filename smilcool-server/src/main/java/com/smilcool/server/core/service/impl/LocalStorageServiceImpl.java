package com.smilcool.server.core.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.core.pojo.vo.UploadInfoVO;
import com.smilcool.server.core.service.LocalStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        try {
            // 原文件名
            String name = file.getOriginalFilename();
            // 文件大小
            String size = getFileSize(file.getSize());
            // 文件后缀
            String suffix = name.substring(name.lastIndexOf("."));
            // 文件 MD5 值（作为文件名，可对文件进行去重作用，以及防原文件名重复产生覆盖）
            String md5 = DigestUtil.md5Hex(file.getBytes());
            // 文件相对存储路径
            String subUrl = "/" + type + "/" + md5 + suffix;
            // 文件虚拟路径（配合 WebMvcConfig 相关配置，进行静态资源映射）
            String url = "/local-storage" + subUrl;
            // 文件本地存储路径
            String path = localStorage + subUrl;
            // 写入文件
            FileUtil.writeBytes(file.getBytes(), path);
            return new UploadInfoVO(name, size, url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SmilcoolException("文件上传失败");
        }
    }

    /**
     * 获取格式化后的文件大小
     *
     * @param size 文件大小，字节
     * @return 格式化后的文件大小
     */
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
