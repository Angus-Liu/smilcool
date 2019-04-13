package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.form.ResourceTypeAddForm;
import com.smilcool.server.core.pojo.po.ResourceType;
import com.smilcool.server.core.pojo.vo.ResourceTypeVO;

import java.util.List;

/**
 * @author Angus
 * @date 2019/4/12
 */
public interface ResourceTypeService {
    ResourceType addResourceType(ResourceTypeAddForm form);

    ResourceType getResourceType(Integer id);

    ResourceTypeVO getResourceType(String tag);

    List<ResourceTypeVO> getResourceTypeList(Integer parentId);

    void checkExist(Integer id);
}
