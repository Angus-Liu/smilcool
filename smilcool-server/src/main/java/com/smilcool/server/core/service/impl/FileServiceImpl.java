package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.FileMapper;
import com.smilcool.server.core.pojo.form.FileAddForm;
import com.smilcool.server.core.pojo.po.File;
import com.smilcool.server.core.service.FileService;
import com.smilcool.server.core.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/12
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private ResourceService resourceService;

    @Override
    public File getFile(Integer id) {
        File file = fileMapper.selectByPrimaryKey(id);
        if (file == null) {
            throw new SmilcoolException("文件不存在");
        }
        return file;
    }

    @Override
    public List<File> getFileList() {
        List<File> fileList = fileMapper.selectAll();
        return fileList;
    }

    @Transactional
    @Override
    public File addFile(FileAddForm form) {
        // TODO 2019/5/12 获取登录用户ID
        // 添加资源，获取资源ID
        Integer resourceId = resourceService.addResource(form.getUserId(), form.getResourceTypeId());
        // 添加文件
        File file = BeanUtil.copyProp(form, File.class);
        file.setResourceId(resourceId);
        fileMapper.insertSelective(file);
        return getFile(file.getId());
    }
}
