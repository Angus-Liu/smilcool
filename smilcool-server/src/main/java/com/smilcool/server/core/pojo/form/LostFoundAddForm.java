package com.smilcool.server.core.pojo.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Angus
 * @date 2019/5/17
 */
@Data
public class LostFoundAddForm {

    @NotBlank(message = "失物寻物类别不能为空")
    private String lostFoundCategory;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "描述不能为空")
    private String description;

    @NotBlank(message = "物品名称不能为空")
    private String itemName;

    @NotNull(message = "拾取/丢失时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    @NotBlank(message = "拾取/丢失地点不能为空")
    private String address;

    private String images;
}
