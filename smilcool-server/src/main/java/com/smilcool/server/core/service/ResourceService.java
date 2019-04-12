package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.vo.ResourceVO;

import java.util.List;

/**
 * @author Angus
 * @date 2019/3/20
 */
public interface ResourceService {

    Integer addResource(Integer userId, Integer resourceTypeId);

    ResourceVO getResource(Integer id);

    List<ResourceVO> getResourceList();

}
