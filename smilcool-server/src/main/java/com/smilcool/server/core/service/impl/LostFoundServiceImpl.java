package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.enums.DicTypeEnum;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.common.util.MockUtil;
import com.smilcool.server.core.dao.LostFoundMapper;
import com.smilcool.server.core.pojo.form.LostFoundAddForm;
import com.smilcool.server.core.pojo.page.LostFoundPage;
import com.smilcool.server.core.pojo.po.LostFound;
import com.smilcool.server.core.pojo.vo.LostFoundVO;
import com.smilcool.server.core.service.CommentService;
import com.smilcool.server.core.service.LostFoundService;
import com.smilcool.server.core.service.ResourceService;
import com.smilcool.server.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Angus
 * @date 2019/5/17
 */
@Service
public class LostFoundServiceImpl implements LostFoundService {

    @Autowired
    private LostFoundMapper lostFoundMapper;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Override
    public LostFound addLostFound(LostFoundAddForm form) {
        // 获取当前登录用户ID
        Integer currentUserId = MockUtil.currentUserId();
        // 添加资源，获取资源ID
        Integer resourceId = resourceService
                .addResource(currentUserId, DicTypeEnum.LOST_FOUND_CATEGORY.name, form.getLostFoundCategory());
        // 添加失物寻物
        LostFound lostFound = BeanUtil.copyProp(form, LostFound.class);
        lostFound.setUserId(currentUserId);
        lostFound.setResourceId(resourceId);
        lostFoundMapper.insertSelective(lostFound);
        return getLostFound(lostFound.getId());
    }

    @Override
    public LostFound getLostFound(Integer id) {
        LostFound lostFound = lostFoundMapper.selectByPrimaryKey(id);
        if (lostFound == null) {
            throw new SmilcoolException("失物寻物信息不存在");
        }
        return lostFound;
    }

    @Override
    public List<LostFound> getLostFoundList() {
        List<LostFound> lostFoundList = lostFoundMapper.select();
        return lostFoundList;
    }

    @Override
    public List<LostFoundPage> getLostFoundPageList() {
        List<LostFound> lostFoundList = lostFoundMapper.select();
        List<LostFoundPage> lostFoundPageList = new ArrayList<>();
        lostFoundList.forEach(lostFound -> {
            LostFoundPage lostFoundPage = LostFoundPage.builder()
                    // 失物寻物信息
                    .lostFound(BeanUtil.copyProp(lostFound, LostFoundVO.class))
                    // 发布用户信息
                    .user(userService.getUserVO(lostFound.getUserId()))
                    // 所属资源信息
                    .resource(resourceService.getResourceVO(lostFound.getResourceId()))
                    // 评论信息
                    .commentList(commentService.listCommentVO(lostFound.getResourceId()))
                    .build();
            lostFoundPageList.add(lostFoundPage);
        });
        return lostFoundPageList;
    }
}
