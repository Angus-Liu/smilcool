package com.smilcool.server.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.form.LostFoundAddForm;
import com.smilcool.server.core.pojo.form.LostFoundQueryForm;
import com.smilcool.server.core.pojo.po.LostFound;
import com.smilcool.server.core.pojo.vo.LostFoundVO;

/**
 * @author Angus
 * @date 2019/5/17
 */
public interface LostFoundService {

    LostFound getLostFound(Integer id);

    LostFound addLostFound(LostFoundAddForm form);

    Page<LostFoundVO> pageLostFoundVO(Page page, LostFoundQueryForm form);

    LostFoundVO getLostFoundVO(Integer id);
}
