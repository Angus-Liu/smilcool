package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.form.SecondHandAddForm;
import com.smilcool.server.core.pojo.vo.SecondHandVO;

import java.util.List;

/**
 * @author Angus
 * @date 2019/4/14
 */
public interface SecondHandService {
    SecondHandVO addSecondHand(SecondHandAddForm form);

    SecondHandVO getSecondHand(Integer id);

    List<SecondHandVO> getSecondHandList();
}
