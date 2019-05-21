package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.common.util.MockUtil;
import com.smilcool.server.core.dao.CommentMapper;
import com.smilcool.server.core.pojo.form.CommentAddForm;
import com.smilcool.server.core.pojo.po.Comment;
import com.smilcool.server.core.pojo.vo.CommentVO;
import com.smilcool.server.core.service.CommentService;
import com.smilcool.server.core.service.ResourceService;
import com.smilcool.server.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Angus
 * @date 2019/4/23
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @Transactional
    @Override
    public Comment addComment(CommentAddForm form) {
        // 当前登录用户
        Integer currentUserId = MockUtil.currentUserId();
        Comment comment = BeanUtil.copyProp(form, Comment.class);
        comment.setUserId(currentUserId);
        commentMapper.insertSelective(comment);
        // 指定资源的评论数 +1
        resourceService.increaseResourceCommentCount(form.getResourceId());
        return getComment(comment.getId());
    }

    @Override
    public Comment getComment(Integer id) {
        Comment comment = commentMapper.selectByPrimaryKey(id);
        if (comment == null) {
            throw new SmilcoolException("评论不存在");
        }
        return comment;
    }

    @Override
    public List<CommentVO> getCommentVOList(Integer resourceId) {
        // 父评论按时间逆序排（后评论的在前面）
        List<CommentVO> parentCommentList = commentMapper.selectParentCommentVOByResourceId(resourceId);
        // 获取子评论信息，子评论按时间顺序排（后评论的在后面）
        parentCommentList.forEach(parentComment -> {
            List<CommentVO> childCommentList = commentMapper.selectChildCommentVOByParentId(parentComment.getId());
            parentComment.setChildren(childCommentList);
        });
        return parentCommentList;
    }
}
