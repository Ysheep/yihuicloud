package com.qzdsoft.eshop.service.sys;

import java.util.List;

import com.qzdsoft.eshop.domain.Resource;
import com.qzdsoft.eshop.vo.res.MenuInfo;
import com.qzdsoft.eshop.vo.res.ResourceInfo;
import com.qzdsoft.eshop.vo.res.ResourceQueryInfo;
import com.qzdsoft.eshop.vo.res.ZtreeNode;
import com.qzdsoft.eshop.vo.sys.LoginUserInfo;
import com.qzdsoft.vo.LayTableResponse;
import com.qzdsoft.vo.Response;

public interface ResourceService {
	
	/**
	 * 资源页面初始查询
	 * @param info
	 * @return
	 */
	LayTableResponse<ResourceInfo> selectData(ResourceQueryInfo info);
	/**
	 * 新增保存
	 * @param res
	 * @return
	 */
	Response<String> save(Resource res);
	/**
	 * 根据id查询资源信息
	 * @param resourceId
	 * @return
	 */
	ResourceInfo selectById(Integer resourceId);
	
	/**
	 * 修改保存
	 * @param res
	 * @return
	 */
	Response<String> editExecute(Resource res);
	
	/**
	 * 修改保存
	 * @param res
	 * @return
	 */
	Response<String> delExecute(Integer id);
	
	List<ZtreeNode> findZtree();
	
	/**
	 * 查询登录用户具有的菜单
	 * @return
	 */
	List<MenuInfo> findPermissionMenu(LoginUserInfo user);
}
