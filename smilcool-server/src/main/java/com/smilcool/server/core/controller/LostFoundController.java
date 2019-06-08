package com.smilcool.server.core.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.LostFoundAddForm;
import com.smilcool.server.core.pojo.form.LostFoundQueryForm;
import com.smilcool.server.core.pojo.po.LostFound;
import com.smilcool.server.core.pojo.vo.LostFoundVO;
import com.smilcool.server.core.service.LostFoundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Angus
 * @date 2019/4/1
 */
@Api(tags = "2.4", description = "失物寻物接口")
@RestController
@RequestMapping("/lost-found")
public class LostFoundController {

    @Autowired
    private LostFoundService lostFoundService;

    @ApiOperation("失物寻物添加")
    @PostMapping
    public Result<LostFound> addLostFound(@RequestBody @Valid LostFoundAddForm form) {
        LostFound lostFound = lostFoundService.addLostFound(form);
        return Result.success(lostFound);
    }

    @ApiOperation("失物寻物分页")
    @GetMapping("/page")
    public Result<Page<LostFoundVO>> pageLostFoundVO(Page page, LostFoundQueryForm form) {
        Page<LostFoundVO> lostFoundPage = lostFoundService.pageLostFoundVO(page, form);
        return Result.success(lostFoundPage);
    }

    @ApiOperation("失物寻物详情")
    @GetMapping("/{id}")
    public Result<LostFoundVO> getLostFoundVO(@PathVariable Integer id) {
        LostFoundVO lostFoundVO = lostFoundService.getLostFoundVO(id);
        return Result.success(lostFoundVO);
    }
}
