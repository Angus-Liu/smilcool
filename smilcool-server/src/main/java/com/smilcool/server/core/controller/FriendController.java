package com.smilcool.server.core.controller;

import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.dto.Result.Empty;
import com.smilcool.server.core.pojo.form.FriendAddForm;
import com.smilcool.server.core.pojo.po.User;
import com.smilcool.server.core.service.FriendService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 好友接口
 */
@AllArgsConstructor
@RestController
@RequestMapping("/friend")
public class FriendController {

    private final FriendService friendService;

    /**
     * 好友列表
     */
    @GetMapping
    public Result<List<User>> getFriendList() {
        List<User> friendList = friendService.getFriendList();
        return Result.success(friendList);
    }

    /**
     * 添加好友
     */
    @PostMapping
    public Result<Empty> addFriend(@RequestBody @Valid FriendAddForm form) {
        friendService.addFriend(form);
        return Result.success();
    }
}
