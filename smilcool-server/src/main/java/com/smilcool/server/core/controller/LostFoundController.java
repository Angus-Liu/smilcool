package com.smilcool.server.core.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.LostFoundAddForm;
import com.smilcool.server.core.pojo.form.LostFoundQueryForm;
import com.smilcool.server.core.pojo.po.LostFound;
import com.smilcool.server.core.pojo.vo.LostFoundVO;
import com.smilcool.server.core.service.LostFoundService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 失物寻物接口
 */
@AllArgsConstructor
@RestController
@RequestMapping("/lost-found")
public class LostFoundController {

    private final LostFoundService lostFoundService;

    /**
     * 失物寻物添加
     */
    @PostMapping
    public Result<LostFound> addLostFound(@RequestBody @Valid LostFoundAddForm form) {
        LostFound lostFound = lostFoundService.addLostFound(form);
        return Result.success(lostFound);
    }

    /**
     * 失物寻物分页
     */
    @GetMapping("/page")
    public Result<Page<LostFoundVO>> pageLostFoundVO(Page<?> page, LostFoundQueryForm form) {
        Page<LostFoundVO> lostFoundPage = lostFoundService.pageLostFoundVO(page, form);
        return Result.success(lostFoundPage);
    }

    /**
     * 失物寻物详情
     */
    @GetMapping("/{id}")
    public Result<LostFoundVO> getLostFoundVO(@PathVariable Integer id) {
        LostFoundVO lostFoundVO = lostFoundService.getLostFoundVO(id);
        return Result.success(lostFoundVO);
    }
}
