package com.smilcool.server.core.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.form.SecondHandQueryForm;
import com.smilcool.server.core.pojo.po.SecondHand;
import com.smilcool.server.core.pojo.vo.SecondHandVO;
import org.apache.ibatis.annotations.Param;

public interface SecondHandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SecondHand record);

    int insertSelective(SecondHand record);

    SecondHand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecondHand record);

    int updateByPrimaryKeyWithBLOBs(SecondHand record);

    int updateByPrimaryKey(SecondHand record);

    /* 以下是自定义内容 */

    SecondHandVO selectSecondHandVOByPrimaryKey(Integer id);

    Page<SecondHandVO> selectSecondHandVOByQueryForm(Page page, @Param("form") SecondHandQueryForm form);
}