package com.qzdsoft.eshop.mapper.advertisement;

import java.util.List;

import com.qzdsoft.eshop.domain.advertisement.Advertisement;
import com.qzdsoft.eshop.util.MyMapper;
import com.qzdsoft.eshop.vo.advertisement.AdvertisementInfo;
import com.qzdsoft.eshop.vo.advertisement.AdvertisementQueryInfo;

public interface AdvertisementMapper extends MyMapper<Advertisement> {
	
	List<AdvertisementInfo> getAllAdvertisements(AdvertisementQueryInfo info);
	
	AdvertisementInfo selectById(Integer advertisementId);
	
	List<AdvertisementInfo> list();
}