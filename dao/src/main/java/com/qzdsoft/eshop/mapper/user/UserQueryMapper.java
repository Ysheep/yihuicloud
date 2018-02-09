package com.qzdsoft.eshop.mapper.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qzdsoft.eshop.vo.sys.CustomerInfo;
import com.qzdsoft.eshop.vo.sys.UserInfo;
import com.qzdsoft.eshop.vo.sys.UserQueryInfo;

public interface UserQueryMapper {
	
	List<UserInfo> selectByInfo(UserQueryInfo info);
	
	List<CustomerInfo> findCustomer(@Param("userId")String userId,@Param("type")Integer type);
}