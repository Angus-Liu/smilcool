package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.ResourceMapper;
import com.smilcool.server.core.pojo.form.ResourceQueryForm;
import com.smilcool.server.core.pojo.po.Resource;
import com.smilcool.server.core.pojo.vo.ResourceVO;
import com.smilcool.server.core.service.ResourceService;
import com.smilcool.server.core.service.ResourceTypeService;
import com.smilcool.server.core.service.UserService;
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
    private UserService userService;

    @Autowired
    private ResourceTypeService resourceTypeService;

    @Autowired
    private ResourceMapper resourceMapper;

    @Transactional
    @Override
    public Integer addResource(Integer userId, Integer resourceTypeId) {
        // 检查用户是否存在
        // TODO: 2019/4/12 后期改为取当前登录用户
        userService.checkExist(userId);
        // 检查资源类型是否存在
        resourceTypeService.checkExist(resourceTypeId);
        Resource resource = new Resource(userId, resourceTypeId);
        resourceMapper.insertSelective(resource);
        return resource.getId();
    }

    @Override
    public ResourceVO getResource(Integer id) {
        Resource resource = resourceMapper.selectByPrimaryKey(id);
        if (resource == null) {
            throw new SmilcoolException("资源不不存在");
        }
        return BeanUtil.copyProp(resource, ResourceVO.class);
    }

    @Override
    public List<ResourceVO> getResourceList() {
        List<Resource> resourceList = resourceMapper.selectAll();
        return BeanUtil.copyProp(resourceList, ResourceVO.class);
    }

    @Override
    public List<ResourceVO> getResourceList(ResourceQueryForm form) {
        Resource condition = BeanUtil.copyProp(form, Resource.class);
        List<Resource> resourceList = resourceMapper.selectByCondition(condition);
        return BeanUtil.copyProp(resourceList, ResourceVO.class);
    }
}
