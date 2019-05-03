package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.pojo.po.Moment;
import com.smilcool.server.core.pojo.vo.MomentInfo;
import com.smilcool.server.core.pojo.vo.MomentVO;
import com.smilcool.server.core.service.MomentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/3
 */
@Api(tags = "2.7", description = "动态接口")
@RestController
public class MomentController {

    @Autowired
    private MomentService momentService;

    @ApiOperation("动态列表")
    @GetMapping("/moment")
    public Result<List<MomentInfo>> getMomentList() {
        List<MomentInfo> momentInfoList = momentService.getMomentInfoList();
        return Result.success(momentInfoList);
    }

}
