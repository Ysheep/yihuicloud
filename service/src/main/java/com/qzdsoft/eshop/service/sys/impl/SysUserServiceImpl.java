/**
 * 
 */
package com.qzdsoft.eshop.service.sys.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.qzdsoft.eshop.domain.Agent;
import com.qzdsoft.eshop.domain.SysUser;
import com.qzdsoft.eshop.domain.user.User;
import com.qzdsoft.eshop.mapper.sys.SysUserMapper;
import com.qzdsoft.eshop.mapper.sys.SysUserQueryMapper;
import com.qzdsoft.eshop.service.sys.SysUserService;
import com.qzdsoft.eshop.vo.sys.LoginUserInfo;
import com.qzdsoft.eshop.vo.sys.SysUserInfo;
import com.qzdsoft.eshop.vo.sys.UserQueryInfo;
import com.qzdsoft.utils.StringUtil;
import com.qzdsoft.utils.SysUtils;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年11月21日
 * @see
 * @since 1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {
	private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);
	@Autowired
	private SysUserQueryMapper sysUserQueryMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	/**
	 * @see com.qzdsoft.eshop.service.sys.SysUserService#data(com.qzdsoft.eshop.vo.sys.UserQueryInfo)
	 */
	@Override
	public List<SysUserInfo> data(UserQueryInfo info) {
		// TODO Auto-generated method stub
		PageHelper.startPage(info.getPage(), info.getLimit());
		List<SysUserInfo> lists = sysUserQueryMapper.selectByQueryParam(info);
		return lists;
	}

	/**
	 * @see com.qzdsoft.eshop.service.sys.SysUserService#selectById(java.lang.Integer)
	 */
	@Override
	public SysUser selectById(Integer uId) {
		
		return sysUserMapper.selectByPrimaryKey(uId);
	}

	/**
	 * @see com.qzdsoft.eshop.service.sys.SysUserService#del(java.lang.Integer)
	 */
	@Override
	public Response del(Integer uId) {
		sysUserMapper.deleteByPrimaryKey(uId);
		return new Response(ResultEnum.SUCCESS);
	}

	/**
	 * @see com.qzdsoft.eshop.service.sys.SysUserService#edit(com.qzdsoft.eshop.domain.SysUser)
	 */
	@Override
	public Response edit(SysUser user) {
		SysUser u = sysUserMapper.selectByPrimaryKey(user.getUserId());
		if(u == null) {
			logger.error("系统用户修改错误: 根据id查询系统用户为空,id={}",user.getUserId());
			return Response.error("用户不存在");
		}
		if(!checkPhoneAvailable(user.getPhone(),u.getUserId())) {
			return Response.error("手机号已注册");
		}
		u.setDeleteStatus(user.getDeleteStatus());
		u.setName(user.getName());
		u.setPhone(user.getPhone());
		u.setRoleId(user.getRoleId());
		if(user.getPassword()!=null && user.getPassword() != "") {
			u.setPassword(SysUtils.string2MD5(user.getPassword()));
		}
		sysUserMapper.updateByPrimaryKey(u);
		return new Response(ResultEnum.SUCCESS);
	}

	/**
	 * @see com.qzdsoft.eshop.service.sys.SysUserService#add(com.qzdsoft.eshop.domain.SysUser)
	 */
	@Override
	public Response add(SysUser user) {
		if(!checkPhoneAvailable(user.getPhone(),null)) {
			return Response.error("手机号已注册");
		}
		SysUser nuser = new SysUser();
		nuser.setCtime(new Date());
		nuser.setDeleteStatus(user.getDeleteStatus());
		nuser.setRoleId(user.getRoleId());
		nuser.setPassword(SysUtils.string2MD5(user.getPassword()));
		nuser.setPhone(user.getPhone());
		nuser.setName(user.getName());
		sysUserMapper.insert(nuser);
		return new Response(ResultEnum.SUCCESS);
	}
	
	
	
	@Override
	public Response pwdSave(Integer id, String opwd, String npd, String npda) {
		SysUser user = sysUserMapper.selectByPrimaryKey(id);
		user.setPassword(SysUtils.string2MD5(npd));
		sysUserMapper.updateByPrimaryKey(user);
		return new Response(ResultEnum.SUCCESS);
	}
	
	
	
	@Override
	public SysUserInfo pInformation(LoginUserInfo info) {
		int type = info.getType();
		SysUserInfo user = new SysUserInfo();
		if(type == 1) {//代理商
			user = sysUserQueryMapper.selectBy(Integer.parseInt(info.getLoginId()));
		}else{
			user = sysUserQueryMapper.selectById(Integer.parseInt(info.getLoginId()));
		}
		return user;
	}

	/**
	 * 校验手机号是否重复
	 * @param phone
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public boolean checkPhoneAvailable(String phone,Integer id) {
		List<SysUser> list = sysUserMapper.selectByPhone(phone,id);
        if(null==id){
            return !(null != list && list.size() > 0);
        }else{
            if(null==list || list.size()==0){
                return true;
            }else{
                if(list.size()>1){
                    return false;
                }else{
                    return list.get(0).getUserId().equals(id);
                }
            }
        }
	}

	
	
	
}
