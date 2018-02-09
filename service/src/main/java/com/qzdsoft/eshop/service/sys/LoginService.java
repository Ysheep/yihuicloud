/**
 * 
 */
package com.qzdsoft.eshop.service.sys;

import java.util.List;

import com.qzdsoft.eshop.domain.user.User;
import com.qzdsoft.eshop.vo.sys.LoginUserInfo;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.TypeInfo;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170420 2017年11月15日
 * @see
 * @since 1.0
 */
public interface LoginService
{
    /**
     * 登录（系统用户和代理商都可以登录）
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    LoginUserInfo login(String userName,String password);
    /**
     * 查询有按钮的权限
     * @param user
     * @return
     */
    List<TypeInfo> selectAuthorizedButton(LoginUserInfo user);
   /**
     * 查询所有具有权限的url
     * @param uid
     * @return
     */
    List<String> selectAuthorizedUrl(LoginUserInfo user);
    
    /**
     * PC用户注册
     */
    Response<String> regist(String phone,String pwd,String code,String recommend);
    /**
     * PC用户登录
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    User loginPC(String userName,String password);
    
    /**
     * 微信登入
     * @param code
     * @param state
     * @return
     */
    User weichatlogin(String code,String state);
}
