package com.smilcool.server.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.common.enums.DicTypeEnum;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.FileMapper;
import com.smilcool.server.core.pojo.form.FileAddForm;
import com.smilcool.server.core.pojo.form.FileQueryForm;
import com.smilcool.server.core.pojo.po.File;
import com.smilcool.server.core.pojo.vo.FileVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FileService {

    private final FileMapper fileMapper;
    private final ResourceService resourceService;
    private final UserService userService;

    public File getFile(Integer id) {
        File file = fileMapper.selectByPrimaryKey(id);
        if (file == null) {
            throw new SmilcoolException("文件不存在");
        }
        return file;
    }

    @Transactional(rollbackFor = Exception.class)
    public File addFile(FileAddForm form) {
        // 获取当前登录用户ID
        Integer currentUserId = userService.currentUserId();
        // 添加资源，获取资源ID
        Integer resourceId = resourceService
                .addResource(currentUserId, DicTypeEnum.FILE_CATEGORY.name, form.getFileCategory());
        // 补全文件信息，添加文件
        File file = BeanUtil.copyProp(form, File.class);
        file.setUserId(currentUserId);
        file.setResourceId(resourceId);
        fileMapper.insertSelective(file);
        return getFile(file.getId());
    }

    public Page<FileVO> pageFileVO(Page<?> page, FileQueryForm form) {
        return fileMapper.selectFileVOByQueryForm(page, form);
    }

    public FileVO getFileVO(Integer id) {
        FileVO fileVO = fileMapper.selectFileVOByPrimaryKey(id);
        if (fileVO == null) {
            throw new SmilcoolException("文件不存在");
        }
        return fileVO;
    }

    public void addDownloadCount(Integer id) {
        fileMapper.updateDownloadCountByPrimaryKey(id, 1);
    }
}
