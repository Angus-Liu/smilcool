package com.smilcool.server.core.controller;

import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.FriendAddForm;
import com.smilcool.server.core.pojo.po.User;
import com.smilcool.server.core.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Angus
 * @date 2019/5/20
 */
@Api(tags = "3.3", description = "好友接口")
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @ApiOperation("好友列表")
    @GetMapping
    public Result<List<User>> getFriendList() {
        List<User> friendList = friendService.getFriendList();
        return Result.success(friendList);
    }

    @ApiOperation("添加好友")
    @PostMapping
    public Result addFriend(@RequestBody @Valid FriendAddForm form) {
        friendService.addFriend(form);
        return Result.success();
    }
}
