package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.MomentMapper;
import com.smilcool.server.core.pojo.po.Moment;
import com.smilcool.server.core.pojo.vo.MomentInfo;
import com.smilcool.server.core.pojo.vo.MomentVO;
import com.smilcool.server.core.service.CommentService;
import com.smilcool.server.core.service.MomentService;
import com.smilcool.server.core.service.ResourceService;
import com.smilcool.server.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<MomentVO> getMomentList() {
        List<Moment> selectResultList = momentMapper.selectAll();
        List<MomentVO> momentList = BeanUtil.copyProp(selectResultList, MomentVO.class);
        return momentList;
    }

    @Override
    public List<MomentInfo> getMomentInfoList() {
        List<Moment> momentList = momentMapper.selectAll();
        List<MomentInfo> momentInfoList = new ArrayList<>();
        momentList.forEach(moment -> {
            MomentInfo momentInfo = new MomentInfo();
            // 动态信息
            momentInfo.setMoment(BeanUtil.copyProp(moment, MomentVO.class));
            // 发布用户信息
            momentInfo.setPostUser(userService.getUserSimpleInfo(moment.getUserId()));
            // 所属资源信息
            momentInfo.setResource(resourceService.getResource(moment.getResourceId()));
            // TODO 评论信息不应该包含
            momentInfo.setCommentList(commentService.getCommentList(moment.getResourceId()));
            momentInfoList.add(momentInfo);
        });
        return momentInfoList;
    }
}
