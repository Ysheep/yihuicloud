/**
 * 
 */
package com.qzdsoft.eshop.service.sys.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qzdsoft.eshop.domain.user.User;
import com.qzdsoft.eshop.mapper.log.WalletLogMapper;
import com.qzdsoft.eshop.mapper.user.UserMapper;
import com.qzdsoft.eshop.mapper.user.UserQueryMapper;
import com.qzdsoft.eshop.service.sys.UserService;
import com.qzdsoft.eshop.vo.log.LayerWallertLogInfo;
import com.qzdsoft.eshop.vo.log.WalletLogInfo;
import com.qzdsoft.eshop.vo.sys.CustomerInfo;
import com.qzdsoft.eshop.vo.sys.UserInfo;
import com.qzdsoft.eshop.vo.sys.UserQueryInfo;
import com.qzdsoft.utils.MD5Util;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2018年1月4日
 * @see
 * @since 1.0
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserQueryMapper userQueryMapper;
	@Autowired
	private WalletLogMapper walletLogMapper;

	@Override
	public User findByUserId(Integer userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		return user;
	}

	@Override
	public List<UserInfo> selectAllByUser(UserQueryInfo info) {
		PageHelper.startPage(info.getPage(), info.getLimit());
		List<UserInfo> list = userQueryMapper.selectByInfo(info);
		return list;
	}

	@Override
	public Response<String> del(Integer userId) {
		userMapper.deleteByPrimaryKey(userId);
		return new Response<String>(ResultEnum.SUCCESS);
	}

	@Override
	public Response<String> add(User user) {
		User userCheck = new User();
		userCheck.setPhone(user.getPhone());
		Integer size = userMapper.select(userCheck).size();
		if(size>0) {
			return Response.error("该手机号已注册");
		}
		user.setPassword(MD5Util.MD5Encode(user.getPassword(), "UTF-8"));
		user.setRegTime(new Date());
		userMapper.insert(user);
		return new Response<String>(ResultEnum.SUCCESS);
	}

	@Override
	public Response<String> edit(User user) {
		User userCheck = new User();
		userCheck.setPhone(user.getPhone());
		List<User> userList = userMapper.select(userCheck);
		if(userList.size()>1) {
			return Response.error("该手机号已注册");
		}else {
			if(!userList.get(0).getUserId().equals(user.getUserId())) {
				return Response.error("该手机号已注册");
			}
		}
		if(!userList.get(0).getPassword().equals(user.getPassword())) {
			user.setPassword(MD5Util.MD5Encode(user.getPassword(), "UTF-8"));
		}
		userMapper.updateByPrimaryKeySelective(user);
		return new Response<String>(ResultEnum.SUCCESS);
	}

	@Override
	public List<CustomerInfo> findAllCustomer(String userId) {
		List<CustomerInfo> list1 = userQueryMapper.findCustomer(userId, 1);//1级客户
		List<CustomerInfo> list2 = userQueryMapper.findCustomer(userId, 2);//2级客户
		for(CustomerInfo cs : list1) {
			cs.setLevel("1级");
		}
		for(CustomerInfo cs2 : list2) {
			cs2.setLevel("2级");
			list1.add(cs2);
		}
		return list1;
	}

	@Override
	public LayerWallertLogInfo findWalletLog(Integer userId,Integer page) {
		PageHelper.startPage(page, 10);
		List<WalletLogInfo> list = walletLogMapper.findWallet(userId);
		LayerWallertLogInfo layInfo = new LayerWallertLogInfo();
		layInfo.setWallet(list);
		PageInfo<WalletLogInfo> pageInfo = new PageInfo<WalletLogInfo>(list);
		layInfo.setPage(pageInfo.getPageNum());
		layInfo.setPageTotal(pageInfo.getPages());
		return layInfo;
	}

	@Override
	public Response<String> save(UserInfo info) {
		User user = new User();
		user.setUserId(info.getUserId());
		user.setUserName(info.getUserName());
		user.setSex(info.getSex());
		user.setBirthday(info.getBirthday());
		user.setEmail(info.getEmail());
		user.setTrueName(info.getTrueName());
		user.setAddress(info.getAddress());
		
		userMapper.updateByPrimaryKeySelective(user);
		return new Response<String>(ResultEnum.SUCCESS);
	}
	
	
}
