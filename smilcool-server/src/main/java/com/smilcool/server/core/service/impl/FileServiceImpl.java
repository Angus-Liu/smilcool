package com.smilcool.server.core.service.impl;

import com.smilcool.server.core.dao.FileMapper;
import com.smilcool.server.core.pojo.po.File;
import com.smilcool.server.core.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/12
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public List<File> getFileList() {
        List<File> fileList = fileMapper.selectAll();
        return fileList;
    }
}
