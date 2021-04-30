package com.smilcool.server.core.controller;

import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.DicItemAddForm;
import com.smilcool.server.core.pojo.form.DicTypeAddForm;
import com.smilcool.server.core.pojo.po.DicItem;
import com.smilcool.server.core.pojo.po.DicType;
import com.smilcool.server.core.pojo.vo.DicItemVO;
import com.smilcool.server.core.service.DicService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 字典接口
 */
@AllArgsConstructor
@RestController
@RequestMapping("/dic")
public class DicController {

    private final DicService dicService;

    /**
     * 字典类型添加
     */
    @PostMapping("/type")
    public Result<DicType> addDicType(@RequestBody @Valid DicTypeAddForm form) {
        DicType dicType = dicService.addDicType(form);
        return Result.success(dicType);
    }

    /**
     * 字典类型列表
     */
    @GetMapping("/type")
    public Result<List<DicType>> getDicTypeList() {
        List<DicType> dicTypeList = dicService.getDicTypeList();
        return Result.success(dicTypeList);
    }

    /**
     * 字典项目添加
     */
    @PostMapping("/item")
    public Result<DicItem> addDicItem(@RequestBody @Valid DicItemAddForm form) {
        DicItem dicItem = dicService.addDicItem(form);
        return Result.success(dicItem);
    }

    /**
     * 字典项目列表
     */
    @GetMapping({"/item"})
    public Result<List<DicItemVO>> getDicItemList(@RequestParam(required = false) String dicTypeCode) {
        List<DicItemVO> dicItemList = dicService.getDicItemList(dicTypeCode);
        return Result.success(dicItemList);
    }
}
