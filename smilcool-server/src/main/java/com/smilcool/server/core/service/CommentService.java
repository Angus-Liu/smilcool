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
    Comment addComment(CommentAddForm form);

    Comment getComment(Integer id);

    List<CommentVO> listCommentVO(Integer resourceId);
}
