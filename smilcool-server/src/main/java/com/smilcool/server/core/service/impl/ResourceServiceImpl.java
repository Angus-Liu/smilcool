package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.ResourceMapper;
import com.smilcool.server.core.pojo.po.Resource;
import com.smilcool.server.core.pojo.vo.ResourceVO;
import com.smilcool.server.core.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Angus
 * @date 2019/3/20
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<ResourceVO> list() {
        List<Resource> resourceList = resourceMapper.selectAll();
        return BeanUtil.copyProp(resourceList, ResourceVO.class);
    }
}
