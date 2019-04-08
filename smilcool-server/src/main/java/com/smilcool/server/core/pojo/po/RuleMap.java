package com.smilcool.server.core.pojo.po;

import java.util.Date;

public class RuleMap {
    private Integer id;

    private String url;

    private String description;

    private Integer type;

    private Boolean authc;

    private Boolean useRoles;

    private String roles;

    private Boolean usePerms;

    private String perms;

    private Boolean useRest;

    private String rest;

    private Integer seq;

    private Integer state;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getAuthc() {
        return authc;
    }

    public void setAuthc(Boolean authc) {
        this.authc = authc;
    }

    public Boolean getUseRoles() {
        return useRoles;
    }

    public void setUseRoles(Boolean useRoles) {
        this.useRoles = useRoles;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Boolean getUsePerms() {
        return usePerms;
    }

    public void setUsePerms(Boolean usePerms) {
        this.usePerms = usePerms;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Boolean getUseRest() {
        return useRest;
    }

    public void setUseRest(Boolean useRest) {
        this.useRest = useRest;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}