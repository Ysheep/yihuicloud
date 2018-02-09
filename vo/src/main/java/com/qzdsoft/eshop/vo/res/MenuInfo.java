package com.qzdsoft.eshop.vo.res;

import java.util.ArrayList;
import java.util.List;

public class MenuInfo
{
    private String name;
    private Integer id;
    private Integer supperid;
    private String url;
    private String icon;
    private List<MenuInfo> childrens = new ArrayList<>();
    
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public Integer getSupperid()
    {
        return supperid;
    }
    public void setSupperid(Integer supperid)
    {
        this.supperid = supperid;
    }
    public String getUrl()
    {
        return url;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }
    public List<MenuInfo> getChildrens()
    {
        return childrens;
    }
    public void setChildrens(List<MenuInfo> childrens)
    {
        this.childrens = childrens;
    }
    
    public String getIcon()
    {
        return icon;
    }
    public void setIcon(String icon)
    {
        this.icon = icon;
    }
    @Override
    public String toString()
    {
        return "MenuInfo [name=" + name + ", id=" + id + ", supperid=" + supperid + ", url=" + url + ", childrens=" + childrens + "]";
    }
    
}
