package com.smilcool.server.core.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.form.MomentQueryForm;
import com.smilcool.server.core.pojo.po.Moment;
import com.smilcool.server.core.pojo.vo.MomentVO;
import org.apache.ibatis.annotations.Param;

public interface MomentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Moment record);

    int insertSelective(Moment record);

    Moment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Moment record);

    int updateByPrimaryKeyWithBLOBs(Moment record);

    int updateByPrimaryKey(Moment record);

    /* 以下是自定义内容 */

    Page<MomentVO> selectMomentVOByQueryForm(Page page, @Param("form") MomentQueryForm form);
}