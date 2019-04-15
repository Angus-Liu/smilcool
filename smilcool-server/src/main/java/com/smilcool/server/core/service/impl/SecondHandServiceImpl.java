package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.SecondHandMapper;
import com.smilcool.server.core.pojo.form.SecondHandAddForm;
import com.smilcool.server.core.pojo.po.SecondHand;
import com.smilcool.server.core.pojo.vo.SecondHandVO;
import com.smilcool.server.core.service.ResourceService;
import com.smilcool.server.core.service.SecondHandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Angus
 * @date 2019/4/14
 */
@Service
public class SecondHandServiceImpl implements SecondHandService {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private SecondHandMapper secondHandMapper;

    @Override
    public SecondHandVO addSecondHand(SecondHandAddForm form) {
        // 添加资源，获取资源ID
        // TODO: 2019/4/14 校验 resourceTypeId
        Integer resourceId = resourceService.addResource(form.getUserId(), form.getResourceTypeId());
        // 添加二手交易
        SecondHand secondHand = BeanUtil.copyProp(form, SecondHand.class);
        secondHand.setResourceId(resourceId);
        secondHandMapper.insertSelective(secondHand);
        return getSecondHand(secondHand.getId());
    }

    @Override
    public SecondHandVO getSecondHand(Integer id) {
        SecondHand secondHand = secondHandMapper.selectByPrimaryKey(id);
        if (secondHand == null) {
            throw new SmilcoolException("二手交易不存在");
        }
        // TODO: 2019/4/14  获取用户信息，获取资源及资源类型信息
        return BeanUtil.copyProp(secondHand, SecondHandVO.class);
    }

    @Override
    public List<SecondHandVO> getSecondHandList() {
        List<SecondHand> secondHandList = secondHandMapper.selectAll();
        return BeanUtil.copyProp(secondHandList, SecondHandVO.class);
    }
}
