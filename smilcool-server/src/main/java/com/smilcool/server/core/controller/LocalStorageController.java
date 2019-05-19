package com.smilcool.server.core.controller;

import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.vo.UploadInfoVO;
import com.smilcool.server.core.service.LocalStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Angus
 * @date 2019/5/19
 */
@Api(tags = "6.0", description = "本地存储接口")
@RestController
@RequestMapping("/local-storage")
public class LocalStorageController {

    @Autowired
    private LocalStorageService localStorageService;

    @ApiOperation("上传")
    @PostMapping("/upload")
    public Result<UploadInfoVO> upload(@RequestParam("type") String type, @RequestParam("file") MultipartFile file) {
        UploadInfoVO uploadInfoVO = localStorageService.upload(type, file);
        return Result.success(uploadInfoVO);
    }

}
