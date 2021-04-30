package com.smilcool.server.core.controller;

import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.vo.UploadInfoVO;
import com.smilcool.server.core.service.LocalStorageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 本地存储接口
 */
@AllArgsConstructor
@RestController
@RequestMapping("/local-storage")
public class LocalStorageController {

    private final LocalStorageService localStorageService;

    /**
     * 上传
     */
    @PostMapping("/upload")
    public Result<UploadInfoVO> upload(@RequestParam("type") String type, @RequestParam("file") MultipartFile file) {
        UploadInfoVO uploadInfoVO = localStorageService.upload(type, file);
        return Result.success(uploadInfoVO);
    }
}
