package com.smilcool.server.core.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.common.enums.DicTypeEnum;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.common.util.MockUtil;
import com.smilcool.server.core.dao.MomentMapper;
import com.smilcool.server.core.pojo.form.MomentAddForm;
import com.smilcool.server.core.pojo.page.MomentPage;
import com.smilcool.server.core.pojo.po.Moment;
import com.smilcool.server.core.pojo.vo.MomentVO;
import com.smilcool.server.core.service.CommentService;
import com.smilcool.server.core.service.MomentService;
import com.smilcool.server.core.service.ResourceService;
import com.smilcool.server.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Angus
 * @date 2019/5/3
 */
@Service
public class MomentServiceImpl implements MomentService {

    @Autowired
    private MomentMapper momentMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ResourceService resourceService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Moment addMoment(MomentAddForm form) {
        // 获取当前登录用户ID
        Integer currentUserId = MockUtil.currentUserId();
        // 添加资源，获取资源ID
        Integer resourceId = resourceService
                .addResource(currentUserId, DicTypeEnum.MOMENT_CATEGORY.name, form.getMomentCategory());
        // 添加动态
        Moment moment = BeanUtil.copyProp(form, Moment.class);
        moment.setUserId(currentUserId);
        moment.setResourceId(resourceId);
        momentMapper.insertSelective(moment);
        return getMoment(moment.getId());
    }

    @Override
    public Moment getMoment(Integer id) {
        Moment moment = momentMapper.selectByPrimaryKey(id);
        if (moment == null) {
            throw new SmilcoolException("动态不存在");
        }
        return moment;
    }

    @Override
    public List<MomentPage> getMomentPageList() {
        List<Moment> momentList = momentMapper.select();
        List<MomentPage> momentPageList = new ArrayList<>();
        momentList.forEach(moment -> {
            MomentPage momentPage = MomentPage.builder()
                    // 动态信息
                    .moment(BeanUtil.copyProp(moment, MomentVO.class))
                    // 发布用户信息
                    .user(userService.getUserVO(moment.getUserId()))
                    // 所属资源信息
                    .resource(resourceService.getResourceVO(moment.getResourceId()))
                    // 评论信息
                    .commentList(commentService.getCommentVOList(moment.getResourceId()))
                    .build();
            momentPageList.add(momentPage);
        });
        return momentPageList;
    }
}
