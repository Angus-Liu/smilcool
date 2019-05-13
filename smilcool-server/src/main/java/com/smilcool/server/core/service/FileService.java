package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.form.FileAddForm;
import com.smilcool.server.core.pojo.po.File;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/12
 */
public interface FileService {

    File addFile(FileAddForm form);

    File getFile(Integer id);

    List<File> getFileList();
}
