package com.smilcool.server.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.form.MomentAddForm;
import com.smilcool.server.core.pojo.form.MomentQueryForm;
import com.smilcool.server.core.pojo.po.Moment;
import com.smilcool.server.core.pojo.vo.MomentVO;

/**
 * @author Angus
 * @date 2019/5/3
 */
public interface MomentService {
    Moment addMoment(MomentAddForm form);

    Moment getMoment(Integer id);

    Page<MomentVO> pageMomentVO(Page page, MomentQueryForm form);
}
