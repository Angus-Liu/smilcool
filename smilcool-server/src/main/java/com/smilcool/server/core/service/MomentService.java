package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.form.MomentAddForm;
import com.smilcool.server.core.pojo.page.MomentPage;
import com.smilcool.server.core.pojo.po.Moment;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/3
 */
public interface MomentService {
    Moment addMoment(MomentAddForm form);

    Moment getMoment(Integer id);

    List<MomentPage> getMomentPageList();
}
