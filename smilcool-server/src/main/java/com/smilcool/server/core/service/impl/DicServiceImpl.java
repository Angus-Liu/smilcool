package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.DicItemMapper;
import com.smilcool.server.core.dao.DicTypeMapper;
import com.smilcool.server.core.pojo.form.DicItemAddForm;
import com.smilcool.server.core.pojo.form.DicTypeAddForm;
import com.smilcool.server.core.pojo.po.DicItem;
import com.smilcool.server.core.pojo.po.DicType;
import com.smilcool.server.core.pojo.vo.DicItemVO;
import com.smilcool.server.core.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/12
 */
@Service
public class DicServiceImpl implements DicService {

    @Autowired
    private DicTypeMapper dicTypeMapper;

    @Autowired
    private DicItemMapper dicItemMapper;

    @Override
    public DicType addDicType(DicTypeAddForm form) {
        // 检查字典类型名或字典类型码是否已存在
        int count = dicTypeMapper.countByNameOrCode(form.getName(), form.getCode());
        if (count != 0) {
            throw new SmilcoolException("字典类型已存在");
        }
        DicType dicType = BeanUtil.copyProp(form, DicType.class);
        dicTypeMapper.insertSelective(dicType);
        return getDicType(dicType.getCode());
    }

    @Override
    public DicType getDicType(String code) {
        DicType dicType = dicTypeMapper.selectByCode(code);
        if (dicType == null) {
            throw new SmilcoolException("字典类型不存在");
        }
        return dicType;
    }

    @Override
    public List<DicType> getDicTypeList() {
        List<DicType> dicTypeList = dicTypeMapper.select();
        return dicTypeList;
    }

    @Override
    public DicItem addDicItem(DicItemAddForm form) {
        // 检查字典类型是否存在
        DicType dicType = dicTypeMapper.selectByCode(form.getDicTypeCode());
        if (dicType == null) {
            throw new SmilcoolException("字典类型不存在");
        }
        // 检查该字典类型下字典项目名或字典项目码是否已存在
        int count = dicItemMapper.countByDicTypeCodeAndNameOrCode(form.getDicTypeCode(), form.getName(), form.getCode());
        if (count != 0) {
            throw new SmilcoolException("字典类型下已存在该字典项目");
        }
        // 为指定字典类型添加新项目
        DicItem dicItem = BeanUtil.copyProp(form, DicItem.class);
        dicItemMapper.insertSelective(dicItem);
        return getDicItem(dicItem.getDicTypeCode(), dicItem.getCode());
    }

    @Override
    public DicItem getDicItem(String dicTypeCode, String code) {
        DicItem dicItem = dicItemMapper.selectByDicTypeCodeAndCode(dicTypeCode, code);
        if (dicItem == null) {
            throw new SmilcoolException("字典类型下不存在该字典项目");
        }
        return dicItem;
    }

    @Override
    public List<DicItemVO> getDicItemList(String dicTypeCode) {
        List<DicItem> dicItemList = dicItemMapper.selectByDicTypeCode(dicTypeCode);
        return BeanUtil.copyProp(dicItemList, DicItemVO.class);
    }
}
