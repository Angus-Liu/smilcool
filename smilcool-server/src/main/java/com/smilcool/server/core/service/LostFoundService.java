package com.smilcool.server.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.common.enums.DicTypeEnum;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.LostFoundMapper;
import com.smilcool.server.core.pojo.form.LostFoundAddForm;
import com.smilcool.server.core.pojo.form.LostFoundQueryForm;
import com.smilcool.server.core.pojo.po.LostFound;
import com.smilcool.server.core.pojo.vo.LostFoundVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LostFoundService {

    private final LostFoundMapper lostFoundMapper;
    private final ResourceService resourceService;
    private final UserService userService;

    public LostFound getLostFound(Integer id) {
        LostFound lostFound = lostFoundMapper.selectByPrimaryKey(id);
        if (lostFound == null) {
            throw new SmilcoolException("失物寻物信息不存在");
        }
        return lostFound;
    }

    public LostFound addLostFound(LostFoundAddForm form) {
        // 获取当前登录用户ID
        Integer currentUserId = userService.currentUserId();
        // 添加资源，获取资源ID
        Integer resourceId = resourceService
                .addResource(currentUserId, DicTypeEnum.LOST_FOUND_CATEGORY.name, form.getLostFoundCategory());
        // 补全信息，添加失物寻物
        LostFound lostFound = BeanUtil.copyProp(form, LostFound.class);
        lostFound.setUserId(currentUserId);
        lostFound.setResourceId(resourceId);
        lostFoundMapper.insertSelective(lostFound);
        return getLostFound(lostFound.getId());
    }

    public Page<LostFoundVO> pageLostFoundVO(Page page, LostFoundQueryForm form) {
        return lostFoundMapper.selectLostFoundVOByQueryForm(page, form);
    }

    public LostFoundVO getLostFoundVO(Integer id) {
        LostFoundVO lostFoundVO = lostFoundMapper.selectLostFoundVOByPrimaryKey(id);
        if (lostFoundVO == null) {
            throw new SmilcoolException("失物寻物不存在");
        }
        return lostFoundVO;
    }
}
