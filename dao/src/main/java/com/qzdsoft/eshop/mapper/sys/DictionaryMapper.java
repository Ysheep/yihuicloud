package com.qzdsoft.eshop.mapper.sys;

import java.util.List;

import com.qzdsoft.eshop.domain.DictionaryInfo;
import com.qzdsoft.eshop.util.MyMapper;
import com.qzdsoft.vo.TypeInfo;

public interface DictionaryMapper extends MyMapper<DictionaryInfo>{
	
	List<TypeInfo> getAllType(); 
	
	List<DictionaryInfo> getDictionaryInfo(String type);
}
