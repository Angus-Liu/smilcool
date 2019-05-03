package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.po.Moment;
import com.smilcool.server.core.pojo.vo.MomentInfo;
import com.smilcool.server.core.pojo.vo.MomentVO;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/3
 */
public interface MomentService {
    List<MomentVO> getMomentList();

    List<MomentInfo> getMomentInfoList();
}
