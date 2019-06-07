package com.smilcool.server.core.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.form.FileQueryForm;
import com.smilcool.server.core.pojo.po.File;
import com.smilcool.server.core.pojo.vo.FileVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface FileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);

    /* 以下是自定义方法 */

    FileVO selectFileVOByPrimaryKey(Integer id);

    Page<FileVO> selectFileVOByQueryForm(Page page, @Param("form") FileQueryForm form);

    @Update("UPDATE file SET download_count = download_count + #{count} WHERE id = #{id}")
    void updateDownloadCountByPrimaryKey(@Param("id") Integer id, @Param("count") Integer count);
}