package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
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

    @Override
    public Comment getComment(Integer id) {
        Comment comment = commentMapper.selectByPrimaryKey(id);
        if (comment == null) {
            throw new SmilcoolException("评论不存在");
        }
        return comment;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Comment addComment(CommentAddForm form) {
        Comment comment = BeanUtil.copyProp(form, Comment.class);
        // 当前登录用户
        comment.setUserId(userService.currentUserId());
        // 添加评论
        commentMapper.insertSelective(comment);
        // 更新指定资源的评论数 +1
        resourceService.addCommentCount(form.getResourceId());
        return getComment(comment.getId());
    }

    @Override
    public List<CommentVO> listCommentVO(Integer resourceId) {
        // 获取父评论，按时间逆序排
        List<CommentVO> parentCommentList = commentMapper.selectParentCommentVOByResourceId(resourceId);
        // 获取子评论，按时间顺序排
        parentCommentList.forEach(parentComment -> {
            List<CommentVO> childCommentList = commentMapper.selectChildCommentVOByParentId(parentComment.getId());
            parentComment.setChildren(childCommentList);
        });
        return parentCommentList;
    }
}
