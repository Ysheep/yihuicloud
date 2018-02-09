package com.qzdsoft.eshop.service.deliverytemplate.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qzdsoft.eshop.domain.deliverytemplate.Area;
import com.qzdsoft.eshop.mapper.deliverytemplate.AreaMapper;
import com.qzdsoft.eshop.service.deliverytemplate.AreaService;
import com.qzdsoft.eshop.vo.area.AreaModel;
import com.qzdsoft.eshop.vo.area.Citys;
import com.qzdsoft.eshop.vo.area.Provinces;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.TypeInfo;

@Service
public class AreaServiceImpl implements AreaService {
	
	@Autowired
	private AreaMapper areaMapper;
	
	@Override
	public Area selectById(Integer areaId) {
		
		return areaMapper.selectByPrimaryKey(areaId);
	}

	@Override
	public List<AreaModel> getAllarea() {
		List<Area> areas = areaMapper.selectAllArea(-1);
		List<AreaModel> models = new ArrayList<AreaModel>();
		if(areas.size()>0) {
			for(Area a:areas) {
				AreaModel model = new AreaModel();
				model.setAreaId(a.getAreaId());
				model.setName(a.getName());
				model.setPid(a.getPid());
				model.setLevel(a.getLevel());
				Area record = new Area();
				record.setPid(a.getAreaId());
				List<Area> pros = areaMapper.select(record);
				if(pros.size()>0) {
					for(Area c:pros) {
						Provinces pro = new Provinces();
						record.setPid(c.getAreaId());
						List<Area>	cts = areaMapper.select(record);
						for(Area ct:cts) {
							Citys city = new Citys();
//							record.setPid(ct.getAreaId());
//							List<Area>	dis = areaMapper.select(record);
//							for(Area d:dis){
//								DistrictList dil = new DistrictList();
//								dil.setAreaId(d.getAreaId());
//								dil.setName(d.getName());
//								dil.setPid(d.getPid());
//								dil.setLevel(d.getLevel());
//								city.getDistrictList().add(dil);
//							}
							city.setAreaId(ct.getAreaId());
							city.setName(ct.getName());
							city.setPid(ct.getPid());
							city.setLevel(ct.getLevel());
							pro.getCitys().add(city);
						}
						pro.setAreaId(c.getAreaId());
						pro.setName(c.getName());
						pro.setPid(c.getPid());
						pro.setLevel(c.getLevel());
						model.getProvinces().add(pro);
					}
				}
				models.add(model);
			}
		}
		return models;
	}

	@Override
	public Response<List<TypeInfo>> findAreaById(Integer id) {
		List<TypeInfo> list = areaMapper.findAreaById(id);
		return new Response<List<TypeInfo>>(ResultEnum.SUCCESS,list);
	}
	
	
}
