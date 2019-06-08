package com.smilcool.server.core.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.form.LostFoundQueryForm;
import com.smilcool.server.core.pojo.po.LostFound;
import com.smilcool.server.core.pojo.vo.LostFoundVO;
import org.apache.ibatis.annotations.Param;

public interface LostFoundMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LostFound record);

    int insertSelective(LostFound record);

    LostFound selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LostFound record);

    int updateByPrimaryKeyWithBLOBs(LostFound record);

    int updateByPrimaryKey(LostFound record);

    /* 以下是自定义内容 */

    Page<LostFoundVO> selectLostFoundVOByQueryForm(Page page, @Param("form") LostFoundQueryForm form);

    LostFoundVO selectLostFoundVOByPrimaryKey(Integer id);
}