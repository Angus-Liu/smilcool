package com.smilcool.server.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.common.enums.DicTypeEnum;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.MomentMapper;
import com.smilcool.server.core.pojo.form.MomentAddForm;
import com.smilcool.server.core.pojo.form.MomentQueryForm;
import com.smilcool.server.core.pojo.po.Moment;
import com.smilcool.server.core.pojo.vo.MomentVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MomentService {

    private final MomentMapper momentMapper;
    private final UserService userService;
    private final ResourceService resourceService;

    @Transactional(rollbackFor = Exception.class)
    public Moment addMoment(MomentAddForm form) {
        // 获取当前登录用户ID
        Integer currentUserId = userService.currentUserId();
        // 添加资源，获取资源ID
        Integer resourceId = resourceService
                .addResource(currentUserId, DicTypeEnum.MOMENT_CATEGORY.name, form.getMomentCategory());
        // 添加动态
        Moment moment = BeanUtil.copyProp(form, Moment.class);
        // 设置发布用户为当前登录用户ID
        moment.setUserId(currentUserId);
        moment.setResourceId(resourceId);
        momentMapper.insertSelective(moment);
        return getMoment(moment.getId());
    }

    public Moment getMoment(Integer id) {
        Moment moment = momentMapper.selectByPrimaryKey(id);
        if (moment == null) {
            throw new SmilcoolException("动态不存在");
        }
        return moment;
    }

    public Page<MomentVO> pageMomentVO(Page page, MomentQueryForm form) {
        return momentMapper.selectMomentVOByQueryForm(page, form);
    }
}
