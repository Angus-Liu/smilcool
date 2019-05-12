package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.po.File;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/12
 */
public interface FileService {
    List<File> getFileList();
}
