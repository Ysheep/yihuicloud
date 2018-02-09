/**
 * 
 */
package com.qzdsoft.eshop.service.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qzdsoft.eshop.domain.Resource;
import com.qzdsoft.eshop.domain.Role;
import com.qzdsoft.eshop.vo.res.ZtreeNode;
import com.qzdsoft.eshop.vo.role.RoleInfo;
import com.qzdsoft.eshop.vo.role.RoleQueryInfo;
import com.qzdsoft.vo.LayTableResponse;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年11月14日
 * @see
 * @since 1.0
 */
public interface RoleService {
	/**
	 * 角色初始化查询
	 * @param info
	 * @return
	 */
	List<RoleInfo> selectData(RoleQueryInfo info);
	/**
	 * 新增保存
	 * @param role
	 * @return
	 */
	Response save(Role role);
	/**
	 * 根据id查询角色信息
	 * @param id
	 * @return
	 */
	Role selctById(Integer id);
	
	/**
	 * 修改保存
	 * @param res
	 * @return
	 */
	Response<String> editExecute(Role res); 
	/**
	 * 角色删除
	 * @param id
	 * @return
	 */
	Response<String> delExecute(Integer id);
	
	 /**
     * 根据角色id
     * @param roleid
     * @return
     */
    List<ZtreeNode> findByRoleId(Integer roleid);
    
    /**
     * 权限菜单设置保存
     * @param roleId
     * @param id
     * @return
     */
    Response roleInit(Integer roleId,String id);
    
    List<Role> selectAll();
}
