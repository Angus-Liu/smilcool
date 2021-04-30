package com.smilcool.server.core.controller;

import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.ZanAddForm;
import com.smilcool.server.core.pojo.po.Zan;
import com.smilcool.server.core.service.ZanService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/zan")
public class ZanController {

    private final ZanService zanService;

    /**
     * 点赞添加
     */
    @PostMapping
    public Result<Zan> addZan(@RequestBody @Valid ZanAddForm form) {
        Zan zan = zanService.addZan(form);
        return Result.success(zan);
    }
}
