package com.smilcool.server.core.pojo.page;

import com.smilcool.server.core.pojo.vo.CommentVO;
import com.smilcool.server.core.pojo.vo.LostFoundVO;
import com.smilcool.server.core.pojo.vo.ResourceVO;
import com.smilcool.server.core.pojo.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LostFoundPage {
    private LostFoundVO lostFound;
    private UserVO user;
    private ResourceVO resource;
    private List<CommentVO> commentList;
}
