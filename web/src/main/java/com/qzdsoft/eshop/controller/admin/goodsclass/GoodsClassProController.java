/**
 * 
 */
package com.qzdsoft.eshop.controller.admin.goodsclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.controller.admin.BaseController;
import com.qzdsoft.eshop.domain.goodsclass.GoodsProperties;
import com.qzdsoft.eshop.service.goodsclass.GoodsPropertiesService;
import com.qzdsoft.eshop.service.sys.Permission;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassProInfo;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassQuery;
import com.qzdsoft.eshop.vo.goodsclass.GoodsProSpecInfo;
import com.qzdsoft.vo.GoodsClassStatus;
import com.qzdsoft.vo.LayTableResponse;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2018年1月23日
 * @see
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/goodsclasspro")
public class GoodsClassProController extends BaseController {
	
	private final static String BASICPATH = "admin/goodsclasspro";
	
	@Autowired
	private GoodsPropertiesService goodsPropertiesService;
	
	@Permission(validate=false)
	@RequestMapping("/index")
	public ModelAndView properties(Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("classId", id);
		mv.addObject("status", GoodsClassStatus.ALL);
		mv.setViewName(BASICPATH+"/index");
		return mv;
	}
	
	@RequestMapping("/data")
	@ResponseBody
	public LayTableResponse<GoodsClassProInfo> list(GoodsClassQuery info) {
		return new LayTableResponse<GoodsClassProInfo>(goodsPropertiesService.findGoodsClassPro(info));
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Response<String> delete(Integer id){
		return goodsPropertiesService.deletPro(id);
	}
	
	
	@Permission(validate=false)
	@RequestMapping("/edit")
	public ModelAndView edit(Integer id) {
		ModelAndView mv = new ModelAndView();
//		mv.addObject("classList", goodsClassService.findGoodsClass(new GoodsClassQuery()));
		mv.addObject("status", GoodsClassStatus.ALL);
		
		if(id == null) {
			mv.setViewName(BASICPATH+"/add");
		}else {
			mv.addObject("goodsPro",goodsPropertiesService.findByProId(id));
			mv.setViewName(BASICPATH+"/edit");
		}
		return mv;
	}
	
	
	@RequestMapping("/execute_add")
	@ResponseBody
	public Response<String> executeAdd(GoodsProperties info){
		info.setDeleteStatus(Integer.parseInt(GoodsClassStatus.USE_CODE));
		return goodsPropertiesService.saveUpdpro(info);
	}
	
	@RequestMapping("/execute_edit")
	@ResponseBody
	public Response<String> executeEdit(GoodsProperties info){
		return goodsPropertiesService.saveUpdpro(info);
	}
	
	
	@Permission(validate=false)
	@RequestMapping("/properties")
	public ModelAndView provalue(Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("status", GoodsClassStatus.ALL);
		mv.addObject("propertyId", id);
		mv.setViewName(BASICPATH+"/detail");
		return mv;
	}
	
	/**
	 * 分类属性值查询
	 * @param propertyId
	 * @return
	 */
	@Permission(validate=false)
	@RequestMapping("/prospecdetails")
	@ResponseBody
	public ModelAndView proSpecDetails(Integer propertyId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("statusall", GoodsClassStatus.ALL);
		mv.addObject("propertyId", propertyId);
		
		mv.addObject("details", goodsPropertiesService.findGoodsProSpec(propertyId));
		mv.setViewName(BASICPATH+"/detailItem");
		return mv;
	}
	/**
	 * 分类属性值编辑
	 * @param info
	 * @return
	 */
	@ResponseBody
    @RequestMapping("/edit-prospecdetails")
    public Response<String> edit(@RequestBody GoodsProSpecInfo info) {
       
        try
        {
            return goodsPropertiesService.editProSpec(info);
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        	return new Response<String>(ResultEnum.ERROR,ex.getMessage());
        }
    }
	
	
	@ResponseBody
	@RequestMapping("/remove-prospec")
	public Response<String> remove(@RequestBody GoodsProSpecInfo info) {
		
		try
		{
			return goodsPropertiesService.deletSpec(info);
		}
		catch (Exception ex)
		{
			return new Response<String>(ResultEnum.ERROR,ex.getMessage());
		}
	}
	
}
