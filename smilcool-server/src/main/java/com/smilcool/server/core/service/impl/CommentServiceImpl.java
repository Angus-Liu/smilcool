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

    @Transactional
    @Override
    public Comment addComment(CommentAddForm form) {
        // TODO: 2019/4/23 userId 应该是登陆用户
        Comment comment = BeanUtil.copyProp(form, Comment.class);
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
    public List<CommentVO> getCommentVOList() {
        List<Comment> comments = commentMapper.select();
        return BeanUtil.copyProp(comments, CommentVO.class);
    }

    @Override
    public List<CommentVO> getCommentVOList(Integer resourceId) {
        // 父评论按时间逆序排（后评论的在前面）
        List<Comment> comments = commentMapper.selectByResourceId(resourceId);
        List<CommentVO> commentList = BeanUtil.copyProp(comments, CommentVO.class);

        commentList.forEach(comment -> {
            // 获取发布用户信息
            comment.setPostUser(userService.getUserSimpleVO(comment.getUserId()));
            // 获取子评论信息，子评论按时间顺序排（后评论的在后面）
            List<Comment> results = commentMapper.selectByParentId(comment.getId());
            List<CommentVO> children = BeanUtil.copyProp(results, CommentVO.class);
            // 获取子评论发布用户和回复用户信息
            children.forEach(child -> {
                child.setPostUser(userService.getUserSimpleVO(child.getUserId()));
                child.setReplyUser(userService.getUserSimpleVO(child.getReplyUserId()));
            });
            comment.setChildren(children);
        });
        return commentList;
    }
}
