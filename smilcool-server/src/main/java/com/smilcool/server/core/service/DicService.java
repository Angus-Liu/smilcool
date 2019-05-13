package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.form.DicItemAddForm;
import com.smilcool.server.core.pojo.form.DicTypeAddForm;
import com.smilcool.server.core.pojo.po.DicItem;
import com.smilcool.server.core.pojo.po.DicType;
import com.smilcool.server.core.pojo.vo.DicItemVO;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/12
 */
public interface DicService {
    // --- DicType ----
    DicType addDicType(DicTypeAddForm form);

    DicType getDicType(String code);

    List<DicType> getDicTypeList();

    // --- DicItem ----
    DicItem addDicItem(DicItemAddForm form);

    DicItem getDicItem(String dicTypeCode, String code);

    List<DicItemVO> getDicItemList(String dicTypeCode);
}
