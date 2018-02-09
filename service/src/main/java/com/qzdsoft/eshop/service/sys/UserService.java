/**
 * 
 */
package com.qzdsoft.eshop.service.sys;

import java.util.List;

import com.qzdsoft.eshop.domain.user.User;
import com.qzdsoft.eshop.vo.log.LayerWallertLogInfo;
import com.qzdsoft.eshop.vo.sys.CustomerInfo;
import com.qzdsoft.eshop.vo.sys.UserInfo;
import com.qzdsoft.eshop.vo.sys.UserQueryInfo;
import com.qzdsoft.vo.Response;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2018年1月4日
 * @see
 * @since 1.0
 */
public interface UserService {
	
	User findByUserId(Integer userId); 
	/**
	 * 按条件搜索会员信息
	 * @param info
	 * @return
	 */
	List<UserInfo> selectAllByUser(UserQueryInfo info);
	
	Response<String> del(Integer userId);
	
	Response<String> add(User user);
	
	Response<String> edit(User user);
	
	Response<String> save(UserInfo info);
	
	List<CustomerInfo> findAllCustomer(String userId);
	
	LayerWallertLogInfo findWalletLog(Integer userId,Integer page);
	
}
