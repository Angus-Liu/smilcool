package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.pojo.form.SecondHandAddForm;
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
public class SecondHandController {

    @Autowired
    private SecondHandService secondHandService;

    @ApiOperation("二手交易添加")
    @PostMapping("/second-hand")
    public Result<SecondHandVO> addSecondHand(@RequestBody @Valid SecondHandAddForm form) {
        SecondHandVO secondHand = secondHandService.addSecondHand(form);
        return Result.success(secondHand);
    }


    @ApiOperation("二手交易列表")
    @GetMapping("/second-hand")
    public Result<List<SecondHandVO>> getSecondHandList() {
        List<SecondHandVO> secondHandList = secondHandService.getSecondHandList();
        return Result.success(secondHandList);
    }

    @ApiOperation("二手交易信息")
    @GetMapping("/second-hand/{id}")
    public Result<SecondHandVO> getSecondHand(@PathVariable Integer id) {
        SecondHandVO secondHand = secondHandService.getSecondHand(id);
        return Result.success(secondHand);
    }
}
