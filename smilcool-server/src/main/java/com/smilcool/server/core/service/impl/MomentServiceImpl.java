package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.MomentMapper;
import com.smilcool.server.core.pojo.page.MomentPage;
import com.smilcool.server.core.pojo.po.Moment;
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
    public List<MomentPage> getMomentPageList() {
        List<Moment> momentList = momentMapper.selectAll();
        List<MomentPage> momentPageList = new ArrayList<>();
        momentList.forEach(moment -> {
            MomentPage momentPage = MomentPage.builder()
                    // 动态信息
                    .moment(BeanUtil.copyProp(moment, MomentVO.class))
                    // 发布用户信息
                    .user(userService.getUserSimpleVO(moment.getUserId()))
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
