package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.form.LostFoundAddForm;
import com.smilcool.server.core.pojo.page.LostFoundPage;
import com.smilcool.server.core.pojo.po.LostFound;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/17
 */
public interface LostFoundService {

    LostFound addLostFound(LostFoundAddForm form);

    LostFound getLostFound(Integer id);

    List<LostFound> getLostFoundList();

    List<LostFoundPage> getLostFoundPageList();
}
