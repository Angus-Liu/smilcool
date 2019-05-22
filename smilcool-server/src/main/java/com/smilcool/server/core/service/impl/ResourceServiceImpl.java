package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.ResourceMapper;
import com.smilcool.server.core.pojo.form.ResourceAddForm;
import com.smilcool.server.core.pojo.form.ResourceQueryForm;
import com.smilcool.server.core.pojo.po.Resource;
import com.smilcool.server.core.pojo.vo.ResourceVO;
import com.smilcool.server.core.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Angus
 * @date 2019/3/20
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer addResource(ResourceAddForm form) {
        Resource resource = BeanUtil.copyProp(form, Resource.class);
        resourceMapper.insertSelective(resource);
        return resource.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer addResource(Integer userId, String resourceDicType, String resourceDicItem) {
        // TODO 2019/5/17 检查字典项目是否存在
        Resource resource = new Resource(userId, resourceDicType, resourceDicItem);
        resourceMapper.insertSelective(resource);
        return resource.getId();
    }

    @Override
    public ResourceVO getResourceVO(Integer id) {
        Resource resource = resourceMapper.selectByPrimaryKey(id);
        if (resource == null) {
            throw new SmilcoolException("资源不不存在");
        }
        return BeanUtil.copyProp(resource, ResourceVO.class);
    }

    @Override
    public List<ResourceVO> getResourceList(ResourceQueryForm form) {
        Resource condition = BeanUtil.copyProp(form, Resource.class);
        List<Resource> resourceList = resourceMapper.selectByCondition(condition);
        return BeanUtil.copyProp(resourceList, ResourceVO.class);
    }

    @Override
    public void addCommentCount(Integer id) {
        // TODO 2019/5/22 解决并发下丢失修改问题
        resourceMapper.updateCommentCountByPrimayKey(id, 1);
    }

    @Override
    public void addZanCount(Integer id) {
        resourceMapper.updateZanCountByPrimaryKey(id, 1);
    }
}
