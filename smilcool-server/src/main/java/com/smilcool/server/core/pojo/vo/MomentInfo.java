package com.smilcool.server.core.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/3
 */
@Data
public class MomentInfo {
    private MomentVO moment;
    private UserSimpleInfo postUser;
    private ResourceVO resource;
    private List<CommentVO> commentList;
}
