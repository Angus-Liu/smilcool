package com.smilcool.server.core.service;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.core.dao.ZanMapper;
import com.smilcool.server.core.pojo.form.ZanAddForm;
import com.smilcool.server.core.pojo.po.Zan;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ZanService {

    private final ZanMapper zanMapper;
    private final UserService userService;
    private final ResourceService resourceService;

    public Zan getZan(Integer id) {
        Zan zan = zanMapper.selectByPrimaryKey(id);
        if (zan == null) {
            throw new SmilcoolException("点赞不存在");
        }
        return zan;
    }

    @Transactional(rollbackFor = Exception.class)
    public Zan addZan(ZanAddForm form) {
        // 获取当前登录用户 ID
        Integer currentUserId = userService.currentUserId();
        Integer resourceId = form.getResourceId();
        // 校验是否已点过赞
        Zan zan = zanMapper.selectByUserIdAndResourceId(currentUserId, resourceId);
        if (zan != null) {
            throw new SmilcoolException("你已经点过赞");
        }
        // 添加点赞
        zan = new Zan(currentUserId, resourceId);
        zanMapper.insertSelective(zan);
        // 更新指定资源的点赞数 +1
        resourceService.addZanCount(resourceId);
        return getZan(zan.getId());
    }
}
