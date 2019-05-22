package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.form.CommentAddForm;
import com.smilcool.server.core.pojo.po.Comment;
import com.smilcool.server.core.pojo.vo.CommentVO;

import java.util.List;

/**
 * @author Angus
 * @date 2019/4/23
 */
public interface CommentService {
    Comment getComment(Integer id);

    Comment addComment(CommentAddForm form);

    List<CommentVO> listCommentVO(Integer resourceId);
}
