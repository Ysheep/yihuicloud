package com.qzdsoft.eshop.mapper.deliverytemplate;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qzdsoft.eshop.domain.deliverytemplate.Area;
import com.qzdsoft.eshop.util.MyMapper;
import com.qzdsoft.vo.TypeInfo;

public interface AreaMapper extends MyMapper<Area> {
	
	List<Area> selectAllArea(Integer level);
	
	List<Area> selectById(@Param("level")Integer level,@Param("pid")Integer pid);
	
	List<TypeInfo> findAreaById(@Param("id")Integer id);
}