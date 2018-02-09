package com.qzdsoft.eshop.service.sys;

import java.util.List;

import com.qzdsoft.eshop.domain.DictionaryInfo;
import com.qzdsoft.eshop.vo.sys.DictionaryEditInfo;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.TypeInfo;

public interface DictionaryService {

	List<TypeInfo> getAllType(); 
	
	List<DictionaryInfo> getDictionaryInfo(String type);
	
	Response<String> save(DictionaryEditInfo info);
	
	Response<String> del(DictionaryEditInfo info);
}
