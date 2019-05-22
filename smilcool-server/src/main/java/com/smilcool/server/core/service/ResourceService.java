package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.form.ResourceAddForm;
import com.smilcool.server.core.pojo.form.ResourceQueryForm;
import com.smilcool.server.core.pojo.vo.ResourceVO;

import java.util.List;

/**
 * @author Angus
 * @date 2019/3/20
 */
public interface ResourceService {

    Integer addResource(ResourceAddForm form);

    Integer addResource(Integer userId, String resourceDicType, String resourceDicItem);

    ResourceVO getResourceVO(Integer id);

    List<ResourceVO> getResourceList(ResourceQueryForm form);

    void addCommentCount(Integer id);

    void addZanCount(Integer id);
}
