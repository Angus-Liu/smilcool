package com.smilcool.server.core.pojo.page;

import com.smilcool.server.core.pojo.vo.CommentVO;
import com.smilcool.server.core.pojo.vo.MomentVO;
import com.smilcool.server.core.pojo.vo.ResourceVO;
import com.smilcool.server.core.pojo.vo.UserSimpleVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/3
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MomentPage {
    private MomentVO moment;
    private UserSimpleVO user;
    private ResourceVO resource;
    private List<CommentVO> commentList;
}
