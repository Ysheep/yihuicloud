/**
 * 
 */
package com.qzdsoft.eshop.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qzdsoft.eshop.domain.Agent;
import com.qzdsoft.eshop.domain.SysUser;
import com.qzdsoft.eshop.vo.sys.LoginUserInfo;
import com.qzdsoft.eshop.vo.sys.SysUserInfo;
import com.qzdsoft.eshop.vo.sys.UserQueryInfo;
import com.qzdsoft.vo.TypeInfo;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170420 2017年11月15日
 * @see
 * @since 1.0
 */
public interface SysUserQueryMapper
{
    LoginUserInfo queryUser(@Param("username")String username,@Param("password")String password);
    
    List<SysUserInfo> selectByQueryParam(UserQueryInfo info);
    /**
     * 查询所有按钮
     * @return
     */
   List<TypeInfo> selectAllButton();
   /**
    * 查询指定角色的按钮
    * @param roleId
    * @return
    */
   List<TypeInfo> selectAuthorizedButton(Integer roleId);
   /**
    * 查询指定的角色是否拥有所有权限
    * @param roleId
    * @return
    */
   Integer isHasAllFunction(Integer roleId);

   List<String> selectAllUrl();

   List<String> selectAuthorizedUrl(Integer roleId);
   /**
    * 根据id查询系统用户
    * @param id
    * @return
    */
  SysUserInfo selectById(Integer id);
   /**
    * 根据id查询代理商
    * @param id
    * @return
    */
  SysUserInfo selectBy(Integer id);
   

}
