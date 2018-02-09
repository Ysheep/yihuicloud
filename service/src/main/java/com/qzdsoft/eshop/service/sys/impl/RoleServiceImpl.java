package com.qzdsoft.eshop.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.qzdsoft.eshop.domain.Role;
import com.qzdsoft.eshop.domain.RoleRes;
import com.qzdsoft.eshop.mapper.sys.RoleMapper;
import com.qzdsoft.eshop.mapper.sys.RoleQueryMapper;
import com.qzdsoft.eshop.mapper.sys.RoleResMapper;
import com.qzdsoft.eshop.mapper.sys.RoleResQueryMapper;
import com.qzdsoft.eshop.service.sys.RoleService;
import com.qzdsoft.eshop.vo.res.ZtreeNode;
import com.qzdsoft.eshop.vo.role.RoleInfo;
import com.qzdsoft.eshop.vo.role.RoleQueryInfo;
import com.qzdsoft.vo.LayTableResponse;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
@Service
public class RoleServiceImpl implements RoleService {
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	@Autowired
	private RoleQueryMapper roleQueryMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleResMapper roleResMapper;
	@Autowired
	private RoleResQueryMapper roleResQueryMapper;
	@Override
	public List<RoleInfo> selectData(RoleQueryInfo info) {
		PageHelper.startPage(info.getPage(), info.getLimit());
		List<RoleInfo> lists = roleQueryMapper.selectData(info);
		return lists;
	}
	@Override
	public Response save(Role role) {
		roleMapper.insert(role);
		return new Response(ResultEnum.SUCCESS);
	}
	@Override
	public Role selctById(Integer id) {
		roleMapper.selectByPrimaryKey(id);
		return roleMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public Response<String> editExecute(Role res) {
		Role info = roleMapper.selectByPrimaryKey(res.getRoleId());
		if(info == null){
			return new Response(ResultEnum.ERROR);
		}
		info.setAllFunction(res.getAllFunction());
		info.setName(res.getName());
		roleMapper.updateByPrimaryKey(info);
		return  new Response(ResultEnum.SUCCESS);
	}
	@Override
	@Transactional
	public Response<String> delExecute(Integer id) {
		roleResQueryMapper.deleteList(id);
		roleMapper.deleteByPrimaryKey(id);
		return new Response(ResultEnum.SUCCESS);
	}
	@Override
	public List<ZtreeNode> findByRoleId(Integer roleid) {
		
		return roleQueryMapper.findByRoleId(roleid);
	}
	@Override
	public Response roleInit(Integer roleId, String id) {
			roleResQueryMapper.deleteList(roleId);
			if(id==null || id =="") {
				return  new Response<String>(ResultEnum.SUCCESS);
			}
			String[] ids = id.split(",");
			int length = ids.length;
			List<RoleRes> roleList = new ArrayList<RoleRes>();
			for(int i=0;i<length;i++) {
				RoleRes res = new RoleRes();
				res.setRoleId(roleId);
				res.setResourceId(Integer.parseInt(ids[i]));
				roleList.add(res);
			}
			int insertcount = roleResMapper.insertList(roleList);
			if(insertcount!=length) {
				 logger.error("编辑菜单权限错误，插入数据不对updatecount={},提交新增数据条数：{}",insertcount,length);
		           throw new RuntimeException("编辑菜单权限错误错误，更新数据不对insercount="+insertcount);
			}
		return  new Response<String>(ResultEnum.SUCCESS);
	}
	@Override
	public List<Role> selectAll() {
		
		return roleMapper.selectAll();
	}
	
	
}
