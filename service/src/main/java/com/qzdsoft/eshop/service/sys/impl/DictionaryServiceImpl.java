package com.qzdsoft.eshop.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qzdsoft.eshop.domain.DictionaryInfo;
import com.qzdsoft.eshop.mapper.sys.DictionaryMapper;
import com.qzdsoft.eshop.service.sys.DictionaryService;
import com.qzdsoft.eshop.vo.sys.DictionaryEditInfo;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.TypeInfo;

@Service
public class DictionaryServiceImpl implements DictionaryService {

	@Autowired
	private DictionaryMapper dictionaryMapper;
	@Override
	public List<TypeInfo> getAllType() {
		return dictionaryMapper.getAllType();
	}

	@Override
	public List<DictionaryInfo> getDictionaryInfo(String type) {
		return dictionaryMapper.getDictionaryInfo(type);
	}

	@Override
	@Transactional
	public Response<String> save(DictionaryEditInfo info) {
		List<DictionaryInfo> dictlist = info.getDetails();
		List<DictionaryInfo> addlist = new ArrayList<DictionaryInfo>();
		List<DictionaryInfo> editlist = new ArrayList<DictionaryInfo>();
		for(DictionaryInfo di : dictlist) {
			di.setTypecode(info.getTypecode());
			di.setTypevalue(info.getTypevalue());
			if(di.getId()==null&&!di.getTypecode().equals("2")) {//若为分润不予新增
				addlist.add(di);
			}else {
				editlist.add(di);
			}
		}
		if(addlist.size()>0) {
			dictionaryMapper.insertList(addlist);
		}
		if(editlist.size()>0) {
			for(DictionaryInfo ei : editlist) {
				dictionaryMapper.updateByPrimaryKeySelective(ei);
			}
		}
		return new Response<String>(ResultEnum.SUCCESS);
	}

	@Override
	@Transactional
	public Response<String> del(DictionaryEditInfo info) {
		List<Integer> list = info.getIds();
		for(Integer li:list) {
			dictionaryMapper.deleteByPrimaryKey(li);
		}
		return new Response<String>(ResultEnum.SUCCESS);
	}

}
