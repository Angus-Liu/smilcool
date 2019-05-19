package com.smilcool.server.core.controller;

import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.LostFoundAddForm;
import com.smilcool.server.core.pojo.page.LostFoundPage;
import com.smilcool.server.core.pojo.po.LostFound;
import com.smilcool.server.core.service.LostFoundService;
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

    @ApiOperation("失物寻物列表")
    @GetMapping
    public Result<List<LostFound>> getLostFoundList() {
        List<LostFound> lostFoundList = lostFoundService.getLostFoundList();
        return Result.success(lostFoundList);
    }

    @ApiOperation("失物寻物页面")
    @GetMapping("/page")
    public Result<List<LostFoundPage>> getLostFoundPageList() {
        List<LostFoundPage> lostFoundPageList = lostFoundService.getLostFoundPageList();
        return Result.success(lostFoundPageList);
    }
}
