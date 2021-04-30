package com.smilcool.server.core.service;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.CommentMapper;
import com.smilcool.server.core.pojo.form.CommentAddForm;
import com.smilcool.server.core.pojo.po.Comment;
import com.smilcool.server.core.pojo.vo.CommentVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;
    private final UserService userService;
    private final ResourceService resourceService;

    public Comment getComment(Integer id) {
        Comment comment = commentMapper.selectByPrimaryKey(id);
        if (comment == null) {
            throw new SmilcoolException("评论不存在");
        }
        return comment;
    }

    @Transactional(rollbackFor = Exception.class)
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
