package com.smilcool.server.core.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.common.enums.DicTypeEnum;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.SecondHandMapper;
import com.smilcool.server.core.pojo.form.SecondHandAddForm;
import com.smilcool.server.core.pojo.form.SecondHandQueryForm;
import com.smilcool.server.core.pojo.po.SecondHand;
import com.smilcool.server.core.pojo.vo.SecondHandVO;
import com.smilcool.server.core.service.ResourceService;
import com.smilcool.server.core.service.SecondHandService;
import com.smilcool.server.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Angus
 * @date 2019/4/14
 */
@Service
public class SecondHandServiceImpl implements SecondHandService {

    @Autowired
    private SecondHandMapper secondHandMapper;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UserService userService;

    @Override
    public SecondHand getSecondHand(Integer id) {
        SecondHand secondHand = secondHandMapper.selectByPrimaryKey(id);
        if (secondHand == null) {
            throw new SmilcoolException("二手交易不存在");
        }
        return secondHand;
    }

    @Override
    public SecondHand addSecondHand(SecondHandAddForm form) {
        // 获取当前登录用户
        Integer currentUserId = userService.currentUserId();
        // 添加资源，获取资源ID
        Integer resourceId = resourceService
                .addResource(currentUserId, DicTypeEnum.SECOND_HAND_CATEGORY.name, form.getSecondHandCategory());
        // 补全信息，添加二手交易
        SecondHand secondHand = BeanUtil.copyProp(form, SecondHand.class);
        secondHand.setUserId(currentUserId);
        secondHand.setResourceId(resourceId);
        secondHandMapper.insertSelective(secondHand);
        return getSecondHand(secondHand.getId());
    }

    @Override
    public Page<SecondHandVO> pageSecondHandVO(Page page, SecondHandQueryForm form) {
        SecondHand condition = BeanUtil.copyProp(form, SecondHand.class);
        return secondHandMapper.selectSecondHandVOByCondition(page, condition);
    }
}
