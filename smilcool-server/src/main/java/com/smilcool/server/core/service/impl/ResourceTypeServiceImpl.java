package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.ResourceTypeMapper;
import com.smilcool.server.core.pojo.form.ResourceTypeAddForm;
import com.smilcool.server.core.pojo.po.ResourceType;
import com.smilcool.server.core.pojo.vo.ResourceTypeVO;
import com.smilcool.server.core.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Angus
 * @date 2019/4/12
 */
@Service
public class ResourceTypeServiceImpl implements ResourceTypeService {

    @Autowired
    private ResourceTypeMapper resourceTypeMapper;

    @Override
    public ResourceType addResourceType(ResourceTypeAddForm form) {
        ResourceType resourceType = BeanUtil.copyProp(form, ResourceType.class);
        resourceTypeMapper.insertSelective(resourceType);
        return getResourceType(resourceType.getId());
    }

    @Override
    public ResourceType getResourceType(Integer id) {
        ResourceType resourceType = resourceTypeMapper.selectByPrimaryKey(id);
        if (resourceType == null) {
            throw new SmilcoolException("资源类型不存在");
        }
        return resourceType;
    }

    @Override
    public ResourceTypeVO getResourceType(String name) {
        // 获取 name 为指定值的列表
        ResourceType select = resourceTypeMapper.selectByName(name);
        if (select == null) {
            throw new SmilcoolException("资源类型不存在");
        }
        ResourceTypeVO resourceType = BeanUtil.copyProp(select, ResourceTypeVO.class);
        // 获取子列表
        resourceType.setChildren(getResourceTypeListWithChildren(resourceType.getId()));
        return resourceType;
    }

    @Override
    public List<ResourceTypeVO> getResourceTypeListWithChildren(Integer parentId) {
        // 获取 parent_id 为 parentId 的列表，parentId 为空时获取所有列表
        List<ResourceType> resourceTypes = resourceTypeMapper.selectByParentId(parentId);
        List<ResourceTypeVO> resourceTypeList = BeanUtil.copyProp(resourceTypes, ResourceTypeVO.class);
        // 递归获取子列表
        resourceTypeList.forEach(resourceType -> resourceType.setChildren(getResourceTypeListWithChildren(resourceType.getId())));
        return resourceTypeList;
    }

    @Override
    public void checkExist(Integer id) {
        ResourceType resourceType = resourceTypeMapper.selectByPrimaryKey(id);
        if (resourceType == null) {
            throw new SmilcoolException("资源类型不存在");
        }
    }

    @Override
    public List<ResourceTypeVO> getResourceTypeList(Integer parentId) {
        // 获取 parent_id 为 parentId 的列表，parentId 为空时获取所有列表
        List<ResourceType> resourceTypes = resourceTypeMapper.selectByParentId(parentId);
        List<ResourceTypeVO> resourceTypeList = BeanUtil.copyProp(resourceTypes, ResourceTypeVO.class);
        return resourceTypeList;
    }
}
