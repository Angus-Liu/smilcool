package com.smilcool.server.core.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.FileAddForm;
import com.smilcool.server.core.pojo.form.FileQueryForm;
import com.smilcool.server.core.pojo.po.File;
import com.smilcool.server.core.pojo.vo.FileVO;
import com.smilcool.server.core.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @ApiOperation("文件添加")
    @PostMapping
    public Result<File> addFile(@RequestBody @Valid FileAddForm form) {
        File file = fileService.addFile(form);
        return Result.success(file);
    }

    @ApiOperation("文件分页")
    @GetMapping("/page")
    public Result<Page<FileVO>> pageFileVO(Page page, FileQueryForm form) {
        Page<FileVO> filePage = fileService.pageFileVO(page, form);
        return Result.success(filePage);
    }

    @ApiOperation("文件详情")
    @GetMapping("/{id}")
    public Result<FileVO> getFileVO(@PathVariable Integer id) {
        FileVO fileVO = fileService.getFileVO(id);
        return Result.success(fileVO);
    }

    @ApiOperation("文件下载量更新")
    @PutMapping("/{id}/download-count")
    public Result addDownloadCount(@PathVariable("id") Integer id) {
        fileService.addDownloadCount(id);
        return Result.success();
    }
}
