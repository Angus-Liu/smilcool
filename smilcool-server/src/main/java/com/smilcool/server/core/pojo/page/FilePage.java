package com.smilcool.server.core.pojo.page;

import com.smilcool.server.core.pojo.vo.CommentVO;
import com.smilcool.server.core.pojo.vo.FileVO;
import com.smilcool.server.core.pojo.vo.ResourceVO;
import com.smilcool.server.core.pojo.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilePage {
    /**
     * 文件信息
     */
    private FileVO file;
    /**
     * 发布用户
     */
    private UserVO user;
    /**
     * 资源信息
     */
    private ResourceVO resource;
    /**
     * 评论信息
     */
    private List<CommentVO> commentList;
}
