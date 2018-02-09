package com.qzdsoft.eshop.service.sys.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.qzdsoft.eshop.domain.Resource;
import com.qzdsoft.eshop.domain.Role;
import com.qzdsoft.eshop.mapper.sys.ResourceMapper;
import com.qzdsoft.eshop.mapper.sys.ResourceQueryMapper;
import com.qzdsoft.eshop.mapper.sys.RoleMapper;
import com.qzdsoft.eshop.service.sys.ResourceService;
import com.qzdsoft.eshop.vo.res.MenuInfo;
import com.qzdsoft.eshop.vo.res.ResourceInfo;
import com.qzdsoft.eshop.vo.res.ResourceQueryInfo;
import com.qzdsoft.eshop.vo.res.ZtreeNode;
import com.qzdsoft.eshop.vo.sys.LoginUserInfo;
import com.qzdsoft.utils.ResourceType;
import com.qzdsoft.vo.LayTableResponse;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
@Service
public class ResourceServiceImpl implements ResourceService {
	private static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
	@Autowired
	private ResourceQueryMapper resourceQueryMapper;
	@Autowired
	private ResourceMapper resourceMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public LayTableResponse<ResourceInfo> selectData(ResourceQueryInfo info) {
		PageHelper.startPage(info.getPage(), info.getLimit());
		List<ResourceInfo> list = resourceQueryMapper.selectData(info);
		return new LayTableResponse<ResourceInfo>(list);
	}

	@Override
	public Response<String> save(Resource res) {
//		if(!checkSortAvailable(res.getSeq(),res.getPid(),null)) {
//			return Response.error("当前排序数已被使用");
//		}
		res.setCtime(new Date());
		resourceMapper.insert(res);
		return new Response(ResultEnum.SUCCESS);
	}

	@Override
	public ResourceInfo selectById(Integer resourceId) {
		ResourceInfo info = resourceQueryMapper.selectByResounrceId(resourceId);
		return info;
	}

	@Override
	public Response<String> editExecute(Resource res) {
//		if(!checkSortAvailable(res.getSeq(),res.getPid(),res.getResourceId())) {
//			return Response.error("当前排序数已被使用");
//		}
		Resource info = resourceMapper.selectByPrimaryKey(res.getResourceId());
		if(info == null){
			return new Response(ResultEnum.ERROR);
		}
		info.setName(res.getName());
		info.setPid(res.getPid());
		info.setType(res.getType());
//		info.setSeq(res.getSeq());
		info.setUrl(res.getUrl());
		resourceMapper.updateByPrimaryKey(info);
		return  new Response(ResultEnum.SUCCESS);
	}

	@Override
	public Response<String> delExecute(Integer id) {
		int count = resourceMapper.deleteByPrimaryKey(id);
		return new Response(ResultEnum.SUCCESS);
	}

	@Override
	public List<ZtreeNode> findZtree() {
		List<ZtreeNode> lists = resourceQueryMapper.findForZtree(Integer.parseInt(ResourceType.DIRECTORIES_MENU));
		return lists;
	}

    /**
     * @see com.qzdsoft.eshop.service.res.ResourceService#findPermissionMenu(java.lang.Integer)
     */
    @Override
    public List<MenuInfo> findPermissionMenu(LoginUserInfo user)
    {
        List<MenuInfo> menus = new ArrayList<>();
        Role role = roleMapper.selectByPrimaryKey(user.getRoleId());
        if(role.getAllFunction()==1) {
            //如果具有所有权限查询所有菜单
            menus  = resourceQueryMapper.queryAllMenu();
        }else {
            menus  = resourceQueryMapper.queryMenuByRole(role.getRoleId());
        }
        
        List<MenuInfo> menuInfos = new ArrayList<>();
        for(int i=0;i<menus.size();i++) {
            MenuInfo menu = menus.get(i);
            //获取所有根节点的菜单
            if(menu.getSupperid()==null) {
                menuInfos.add(menu);
                menus.remove(i);
                i--;
                getChildrenMenus(menu,menus);
            }
        }
        
        return menuInfos;
    }
    private void getChildrenMenus(MenuInfo parent,List<MenuInfo> menus) {
        for(int i=0;i<menus.size();i++) {
            MenuInfo menu = menus.get(i);
            if(menu.getSupperid()!=null&&menu.getSupperid().equals(parent.getId())) {
                parent.getChildrens().add(menu);
                menus.remove(i);
                i--;
                getChildrenMenus(menu, menus);
            }
        }
    }
    
    /**
     * 检查排序数是否可用
     * @param sort 排序数
     * @param id id为空时新增排序，编辑排序
     * @return
     */
    public boolean checkSortAvailable(Integer seq,Integer pid,Integer id){
    	List<Resource> list = resourceMapper.getBySort(seq, pid);
        if(null==id){
            return !(null != list && list.size() > 0);
        }else{
            if(null==list || list.size()==0){
                return true;
            }else{
                if(list.size()>1){
                    return false;
                }else{
                    return list.get(0).getResourceId().equals(id);
                }
            }
        }
    
    }
}
