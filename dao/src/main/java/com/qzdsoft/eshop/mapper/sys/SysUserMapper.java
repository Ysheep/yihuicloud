package com.qzdsoft.eshop.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qzdsoft.eshop.domain.SysUser;
import com.qzdsoft.eshop.util.MyMapper;

public interface SysUserMapper extends MyMapper<SysUser> {

	 List<SysUser> selectByPhone(@Param("phone")String phone, Integer id);

	
}