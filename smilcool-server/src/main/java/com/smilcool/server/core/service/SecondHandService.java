package com.smilcool.server.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.form.SecondHandAddForm;
import com.smilcool.server.core.pojo.form.SecondHandQueryForm;
import com.smilcool.server.core.pojo.po.SecondHand;
import com.smilcool.server.core.pojo.vo.SecondHandVO;

/**
 * @author Angus
 * @date 2019/4/14
 */
public interface SecondHandService {

    SecondHand getSecondHand(Integer id);

    SecondHand addSecondHand(SecondHandAddForm form);

    Page<SecondHandVO> pageSecondHandVO(Page page, SecondHandQueryForm form);
}
