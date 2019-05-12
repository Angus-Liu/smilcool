package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.pojo.form.FileAddForm;
import com.smilcool.server.core.pojo.po.File;
import com.smilcool.server.core.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("文件列表")
    @GetMapping
    public Result<List<File>> getFileList() {
        List<File> fileList = fileService.getFileList();
        return Result.success(fileList);
    }

    @ApiOperation("文件添加")
    @PostMapping
    public Result<File> addFile(@RequestBody FileAddForm form) {
        File file = fileService.addFile(form);
        return Result.success(file);
    }
}
