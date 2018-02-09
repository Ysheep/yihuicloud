package com.qzdsoft.eshop.service.sys;

import java.util.List;

import com.qzdsoft.eshop.domain.SysUser;
import com.qzdsoft.eshop.vo.sys.LoginUserInfo;
import com.qzdsoft.eshop.vo.sys.SysUserInfo;
import com.qzdsoft.eshop.vo.sys.UserQueryInfo;
import com.qzdsoft.vo.Response;
/**
 * 系统用户管理
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年11月21日
 * @see
 * @since 1.0
 */
public interface SysUserService {
	/**
	 * 系统用户页面查询
	 * @param info
	 * @return
	 */
	List<SysUserInfo> data(UserQueryInfo info);
	/**
	 * 根据id查询系统用户
	 * @param uId
	 * @return
	 */
	SysUser selectById(Integer uId);
	/**
	 * 删除
	 * @param uId
	 * @return
	 */
	Response del(Integer uId);
	/**
	 * 修改
	 * @param user
	 * @return
	 */
	Response edit(SysUser user);
	/**
	 * 新增
	 * @param user
	 * @return
	 */
	Response add(SysUser user);
	/**
	 * 修改密码保存
	 * @param id
	 * @param opwd
	 * @param npd
	 * @param npda
	 * @return
	 */
	Response pwdSave(Integer id, String opwd, String npd, String npda);
	/**
	 * 个人信息
	 * @param info
	 * @return
	 */
	SysUserInfo pInformation(LoginUserInfo info);
	
}
