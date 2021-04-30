package com.smilcool.server.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.common.enums.DicTypeEnum;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.SecondHandMapper;
import com.smilcool.server.core.pojo.form.SecondHandAddForm;
import com.smilcool.server.core.pojo.form.SecondHandQueryForm;
import com.smilcool.server.core.pojo.po.SecondHand;
import com.smilcool.server.core.pojo.vo.SecondHandVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecondHandService {

    private final SecondHandMapper secondHandMapper;
    private final ResourceService resourceService;
    private final UserService userService;

    public SecondHand getSecondHand(Integer id) {
        SecondHand secondHand = secondHandMapper.selectByPrimaryKey(id);
        if (secondHand == null) {
            throw new SmilcoolException("二手交易不存在");
        }
        return secondHand;
    }

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

    public Page<SecondHandVO> pageSecondHandVO(Page page, SecondHandQueryForm form) {
        return secondHandMapper.selectSecondHandVOByQueryForm(page, form);
    }

    public SecondHandVO getSecondHandVO(Integer id) {
        SecondHandVO secondHandVO = secondHandMapper.selectSecondHandVOByPrimaryKey(id);
        if (secondHandVO == null) {
            throw new SmilcoolException("二手交易不存在");
        }
        return secondHandVO;
    }
}
