package com.qzdsoft.eshop.service.orders;

import java.util.Map;

import com.qzdsoft.vo.Response;

public interface AliPayService {
	
	Response<String> aliPayNotify(Map<String,String> param);
}
