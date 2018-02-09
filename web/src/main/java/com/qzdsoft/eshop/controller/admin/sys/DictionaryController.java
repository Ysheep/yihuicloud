package com.qzdsoft.eshop.controller.admin.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.service.sys.DictionaryService;
import com.qzdsoft.eshop.service.sys.Permission;
import com.qzdsoft.eshop.vo.sys.DictionaryEditInfo;
import com.qzdsoft.vo.Response;

@Controller
@RequestMapping("/admin/dictionary")
public class DictionaryController {
	final static Logger logger = LoggerFactory.getLogger(DictionaryController.class);
	final static String BASEPATH = "admin/dictionary";
	
	@Autowired
	private DictionaryService DictionaryService;
	
	@Permission(validate=false)
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("menus", DictionaryService.getAllType());
		mv.setViewName(BASEPATH+"/index");
		return mv;
	}
	
	@Permission(validate=false)
	@RequestMapping("/detail")
	public ModelAndView detail(String type) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("items", DictionaryService.getDictionaryInfo(type));
		mv.setViewName(BASEPATH+"/detailItem");
		return mv;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Response<String> save(@RequestBody DictionaryEditInfo info){
		return DictionaryService.save(info);
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Response<String> del(@RequestBody DictionaryEditInfo info){
		return DictionaryService.del(info);
	}
}
