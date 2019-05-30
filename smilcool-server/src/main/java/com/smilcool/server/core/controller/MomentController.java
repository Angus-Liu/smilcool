package com.smilcool.server.core.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.MomentAddForm;
import com.smilcool.server.core.pojo.form.MomentQueryForm;
import com.smilcool.server.core.pojo.po.Moment;
import com.smilcool.server.core.pojo.vo.MomentVO;
import com.smilcool.server.core.service.MomentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Angus
 * @date 2019/5/3
 */
@Api(tags = "2.7", description = "动态接口")
@RestController
@RequestMapping("/moment")
public class MomentController {

    @Autowired
    private MomentService momentService;

    @ApiOperation("动态添加")
    @PostMapping
    public Result<Moment> addMoment(@RequestBody @Valid MomentAddForm form) {
        Moment moment = momentService.addMoment(form);
        return Result.success(moment);
    }

    @ApiOperation("动态分页")
    @GetMapping("/page")
    public Result<Page<MomentVO>> pageMomentVO(Page page, MomentQueryForm form) {
        Page<MomentVO> momentPage = momentService.pageMomentVO(page, form);
        return Result.success(momentPage);
    }
}
