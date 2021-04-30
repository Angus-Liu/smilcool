package com.smilcool.server.core.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.dto.Result.Empty;
import com.smilcool.server.core.pojo.form.FileAddForm;
import com.smilcool.server.core.pojo.form.FileQueryForm;
import com.smilcool.server.core.pojo.po.File;
import com.smilcool.server.core.pojo.vo.FileVO;
import com.smilcool.server.core.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 文件接口
 */
@AllArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    /**
     * 文件添加
     */
    @PostMapping
    public Result<File> addFile(@RequestBody @Valid FileAddForm form) {
        File file = fileService.addFile(form);
        return Result.success(file);
    }

    /**
     * 文件分页
     */
    @GetMapping("/page")
    public Result<Page<FileVO>> pageFileVO(Page<?> page, FileQueryForm form) {
        Page<FileVO> filePage = fileService.pageFileVO(page, form);
        return Result.success(filePage);
    }

    /**
     * 文件详情
     */
    @GetMapping("/{id}")
    public Result<FileVO> getFileVO(@PathVariable Integer id) {
        FileVO fileVO = fileService.getFileVO(id);
        return Result.success(fileVO);
    }

    /**
     * 文件下载量更新
     */
    @PutMapping("/{id}/download-count")
    public Result<Empty> addDownloadCount(@PathVariable("id") Integer id) {
        fileService.addDownloadCount(id);
        return Result.success();
    }
}
