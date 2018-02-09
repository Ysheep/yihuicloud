package com.qzdsoft.eshop.domain;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Resource {
    /**
     * 资源id
     */
    @Id
    @Column(name = "resource_id")
    private Integer resourceId;

    /**
     * 父级资源id
     */
    private Integer pid;

    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private String url;

    /**
     * 0菜单、1按钮
     */
    private Short type;
    
//    private Integer seq;
//    
//    private String icon;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date ctime;

    /**
     * 获取资源id
     *
     * @return resource_id - 资源id
     */
    public Integer getResourceId() {
        return resourceId;
    }

    /**
     * 设置资源id
     *
     * @param resourceId 资源id
     */
    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * 获取父级资源id
     *
     * @return pid - 父级资源id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置父级资源id
     *
     * @param pid 父级资源id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取路径
     *
     * @return url - 路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置路径
     *
     * @param url 路径
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取0菜单、1按钮
     *
     * @return type - 0菜单、1按钮
     */
    public Short getType() {
        return type;
    }

    /**
     * 设置0菜单、1按钮
     *
     * @param type 0菜单、1按钮
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * 获取创建时间
     *
     * @return ctime - 创建时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置创建时间
     *
     * @param ctime 创建时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

}