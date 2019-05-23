package com.smilcool.server.core.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.SecondHandAddForm;
import com.smilcool.server.core.pojo.form.SecondHandQueryForm;
import com.smilcool.server.core.pojo.po.SecondHand;
import com.smilcool.server.core.pojo.vo.SecondHandVO;
import com.smilcool.server.core.service.SecondHandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Angus
 * @date 2019/4/1
 */
@Api(tags = "2.5", description = "二手交易接口")
@RestController
@RequestMapping("/second-hand")
public class SecondHandController {

    @Autowired
    private SecondHandService secondHandService;

    @ApiOperation("二手交易添加")
    @PostMapping
    public Result<SecondHand> addSecondHand(@RequestBody @Valid SecondHandAddForm form) {
        SecondHand secondHand = secondHandService.addSecondHand(form);
        return Result.success(secondHand);
    }


    @ApiOperation("二手交易分页")
    @GetMapping("/page")
    public Result<Page<SecondHandVO>> pageSecondHandVO(Page page, SecondHandQueryForm form) {
        Page<SecondHandVO> secondHandPage = secondHandService.pageSecondHandVO(page, form);
        return Result.success(secondHandPage);
    }
}
