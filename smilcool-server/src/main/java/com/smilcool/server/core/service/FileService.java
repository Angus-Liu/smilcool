package com.smilcool.server.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.form.FileAddForm;
import com.smilcool.server.core.pojo.form.FileQueryForm;
import com.smilcool.server.core.pojo.po.File;
import com.smilcool.server.core.pojo.vo.FileVO;

/**
 * @author Angus
 * @date 2019/5/12
 */
public interface FileService {

    File getFile(Integer id);

    File addFile(FileAddForm form);

    Page<FileVO> pageFileVO(Page page, FileQueryForm form);

    FileVO getFileVO(Integer id);

    void addDownloadCount(Integer id);
}
