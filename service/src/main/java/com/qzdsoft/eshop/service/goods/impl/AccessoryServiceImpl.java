package com.qzdsoft.eshop.service.goods.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qzdsoft.eshop.domain.goods.Accessory;
import com.qzdsoft.eshop.mapper.goods.AccessoryMapper;
import com.qzdsoft.eshop.service.goods.AccessoryService;
import com.qzdsoft.vo.TypeInfo;

@Service
public class AccessoryServiceImpl implements AccessoryService{

	@Autowired
	private AccessoryMapper accessoryMapper;
	
	@Override
	@Transactional
	public TypeInfo saveAccessory(String url, String name) {
		Accessory acces = new Accessory();
		acces.setUrl(url);
		acces.setName(name);
		acces.setCtime(new Date());
		
		accessoryMapper.insert(acces);
		TypeInfo ti = new TypeInfo();
		ti.setCode(acces.getAccessoryId().toString());
		ti.setValue(acces.getUrl());
		
		return ti;
	}

}
