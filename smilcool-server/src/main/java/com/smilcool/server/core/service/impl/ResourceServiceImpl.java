package com.smilcool.server.core.service.impl;

import com.smilcool.server.core.dao.ResourceMapper;
import com.smilcool.server.core.model.Resource;
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
    public List<Resource> list() {
        return resourceMapper.selectAll();
    }
}
