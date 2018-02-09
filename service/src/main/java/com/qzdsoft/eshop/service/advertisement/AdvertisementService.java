package com.qzdsoft.eshop.service.advertisement;

import java.util.List;

import com.qzdsoft.eshop.domain.advertisement.Advertisement;
import com.qzdsoft.eshop.vo.advertisement.AdvertisementInfo;
import com.qzdsoft.eshop.vo.advertisement.AdvertisementQueryInfo;
import com.qzdsoft.vo.LayTableResponse;
import com.qzdsoft.vo.Response;

public interface AdvertisementService {
	
	LayTableResponse<AdvertisementInfo> getAllAdvertisements(AdvertisementQueryInfo info);
	
	AdvertisementInfo selctById(Integer advertisementId);
	
	Response<String> createOrUpdate(Advertisement info);
	
	Response<String> del(Integer adverId);
	
	List<AdvertisementInfo> list();
}
