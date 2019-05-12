package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.pojo.po.File;
import com.smilcool.server.core.service.DataService;
import com.smilcool.server.core.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/12
 */
@Api(tags = "2.6", description = "文件接口")
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @ApiOperation("资料列表")
    @RequestMapping("/data")
    public Result<List<File>> getFileList() {
        List<File> fileList = fileService.getFileList();
        return Result.success(fileList);
    }
}
