package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.form.ZanAddForm;
import com.smilcool.server.core.pojo.po.Zan;

/**
 * @author Angus
 * @date 2019/5/22
 */
public interface ZanService {
    Zan getZan(Integer id);

    Zan addZan(ZanAddForm form);
}
