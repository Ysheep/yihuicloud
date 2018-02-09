package com.qzdsoft.eshop.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qzdsoft.eshop.domain.Resource;
import com.qzdsoft.eshop.vo.res.MenuInfo;
import com.qzdsoft.eshop.vo.res.ResourceInfo;
import com.qzdsoft.eshop.vo.res.ResourceQueryInfo;
import com.qzdsoft.eshop.vo.res.ZtreeNode;

public interface ResourceQueryMapper {
	
	List<ResourceInfo> selectData(ResourceQueryInfo info);
	
	ResourceInfo selectByResounrceId(@Param("resourceId")Integer resourceId);
	
	 List<ZtreeNode> findForZtree(@Param("type")Integer type);
	 
	 List<MenuInfo> queryAllMenu();

    /**
     * 根据角色id查询菜单信息
     * @param roleId
     * @return
     */
    List<MenuInfo> queryMenuByRole(Integer roleId);
	 
	
}