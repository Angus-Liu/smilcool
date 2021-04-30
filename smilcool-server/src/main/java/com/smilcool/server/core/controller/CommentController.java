package com.smilcool.server.core.controller;

import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.CommentAddForm;
import com.smilcool.server.core.pojo.po.Comment;
import com.smilcool.server.core.pojo.vo.CommentVO;
import com.smilcool.server.core.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 评论接口
 */
@AllArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    /**
     * 评论添加
     */
    @PostMapping
    public Result<Comment> addComment(@RequestBody @Valid CommentAddForm form) {
        Comment comment = commentService.addComment(form);
        return Result.success(comment);
    }

    /**
     * 评论列表
     */
    @GetMapping("/{resourceId}")
    public Result<List<CommentVO>> getCommentList(@PathVariable Integer resourceId) {
        List<CommentVO> commentList = commentService.listCommentVO(resourceId);
        return Result.success(commentList);
    }
}
