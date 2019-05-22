package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.enums.DicTypeEnum;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.common.util.MockUtil;
import com.smilcool.server.core.dao.FileMapper;
import com.smilcool.server.core.pojo.form.FileAddForm;
import com.smilcool.server.core.pojo.page.FilePage;
import com.smilcool.server.core.pojo.po.File;
import com.smilcool.server.core.pojo.vo.FileVO;
import com.smilcool.server.core.service.CommentService;
import com.smilcool.server.core.service.FileService;
import com.smilcool.server.core.service.ResourceService;
import com.smilcool.server.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public File addFile(FileAddForm form) {
        // 获取当前登录用户ID
        Integer currentUserId = MockUtil.currentUserId();
        // 添加资源，获取资源ID
        Integer resourceId = resourceService
                .addResource(currentUserId, DicTypeEnum.FILE_CATEGORY.name, form.getFileCategory());
        // 添加文件
        File file = BeanUtil.copyProp(form, File.class);
        file.setUserId(currentUserId);
        file.setResourceId(resourceId);
        fileMapper.insertSelective(file);
        return getFile(file.getId());
    }

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
        List<File> fileList = fileMapper.select();
        return fileList;
    }

    @Override
    public List<FilePage> getFilePageList() {
        List<File> fileList = fileMapper.select();
        List<FilePage> filePageList = new ArrayList<>();
        fileList.forEach(file -> {
            FilePage filePage = FilePage.builder()
                    // 文件信息
                    .file(BeanUtil.copyProp(file, FileVO.class))
                    // 发布用户信息
                    .user(userService.getUserVO(file.getUserId()))
                    // 所属资源信息
                    .resource(resourceService.getResourceVO(file.getResourceId()))
                    // 评论信息
                    .commentList(commentService.listCommentVO(file.getResourceId()))
                    .build();
            filePageList.add(filePage);
        });
        return filePageList;
    }
}
