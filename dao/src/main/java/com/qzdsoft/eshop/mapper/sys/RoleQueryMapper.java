package com.qzdsoft.eshop.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qzdsoft.eshop.vo.res.ZtreeNode;
import com.qzdsoft.eshop.vo.role.RoleInfo;
import com.qzdsoft.eshop.vo.role.RoleQueryInfo;

public interface RoleQueryMapper  {
	
	List<RoleInfo> selectData(RoleQueryInfo info);
	
    /**
     * 根据角色id
     * @param roleid
     * @return
     */
    List<ZtreeNode> findByRoleId(@Param("roleId")Integer roleid);
}