package com.qzdsoft.eshop.mapper.sys;

import org.apache.ibatis.annotations.Param;

public interface RoleResQueryMapper  {
	
	int deleteList(@Param("roleId")Integer roleId);
}