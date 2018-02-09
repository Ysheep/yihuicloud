package com.qzdsoft.eshop.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qzdsoft.eshop.domain.Resource;
import com.qzdsoft.eshop.util.MyMapper;

public interface ResourceMapper extends MyMapper<Resource> {
	
	 List<Resource> getBySort(@Param("seq")Integer seq,@Param("pid")Integer pid);
}