package com.qzdsoft.eshop.controller.pc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.domain.goods.GoodsSku;
import com.qzdsoft.eshop.domain.goodsclass.GoodsClass;
import com.qzdsoft.eshop.service.goods.GoodsService;
import com.qzdsoft.eshop.service.goodsclass.GoodsClassService;
import com.qzdsoft.eshop.vo.goods.pc.GoodsQuestionInfo;
import com.qzdsoft.eshop.vo.goods.pc.GoodsSearchInfo;
import com.qzdsoft.eshop.vo.goods.pc.GoodsSearchQueryInfo;
import com.qzdsoft.eshop.vo.goods.pc.GoodsSkuDetail;
import com.qzdsoft.eshop.vo.sys.LoginUserInfo;
import com.qzdsoft.utils.Constant;
import com.qzdsoft.vo.Page;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.TypeInfo;
@Controller
@RequestMapping("/goods")
public class GoodsController{
	
	 private static final Logger logger = org.slf4j.LoggerFactory.getLogger(GoodsController.class);
	 private static final String BASEPATH = "pc";
	 
	 @Autowired
	 private GoodsService goodsService;
	 @Autowired
	 private GoodsClassService goodsClassService;
	 
	 @RequestMapping("/list")
    public ModelAndView list(GoodsSearchQueryInfo info) {
        ModelAndView mv = new ModelAndView();
        String page = "/list";
        Page<GoodsSearchInfo> goodsList = goodsService.goodsList(info);
        mv.addObject("goods",goodsList);
        mv.addObject("syscode", goodsService.getAllSystem());
        mv.addObject("gc", goodsClassService.queryGoodsClassForTypeInfo());
        mv.addObject("classId", info.getClassId()!=null?info.getClassId():"");
        mv.addObject("systemId", info.getSystemId()!=null?info.getSystemId():"");
        mv.addObject("isPackage", info.getIsPackage()!=null?info.getIsPackage():"");
        String classNmae = "";
        if(info.getClassId()!=null) {
        	GoodsClass  goodsclass = goodsClassService.findById(info.getClassId());
        	classNmae = goodsclass.getName();
        }
        String systemName = "";
        if(info.getSystemId()!=null) {
        	for(TypeInfo sys:goodsService.getAllSystem()) {
        		if(Integer.parseInt(sys.getCode()) == info.getSystemId()) {
        			systemName = sys.getValue();
        			break;
        		}
        	}
        }
        String isPackage = "";
        if(info.getIsPackage()!=null) {
        	isPackage = "产品套餐"; 
        }
        mv.addObject("classNmae", classNmae);
        mv.addObject("systemName", systemName);
        mv.addObject("isPackagename", isPackage);
        mv.setViewName(BASEPATH+page);
        return mv;
    }
	 
	 @RequestMapping("/detail")
	 public ModelAndView goodDetail(Integer goodsId) {
		 ModelAndView mv = new ModelAndView();
		 if(goodsId==null) {
			 mv.setViewName(BASEPATH+"/goods/detailNo");
		 }else {
			 String page = "/goods/detail";
			 mv.addObject("detail", goodsService.findById(goodsId));
//		 mv.addObject("goodsParam", goodsService.findGoodsParams(goodsId));
			 mv.addObject("goodsComment",goodsService.findGoodsCommentInfo(goodsId));
//		 mv.addObject("goodsQuestion", goodsService.findGoodsQuestionInfo(goodsId));
			 mv.setViewName(BASEPATH+page);
		 }
	     return mv;
		 
	 }
	 
	 @RequestMapping("/param")
	 @ResponseBody
	 public Response<List<TypeInfo>> findGoodsParam(Integer goodsId){
		 List<TypeInfo> info = goodsService.findGoodsParams(goodsId);
		 return new Response<>(ResultEnum.SUCCESS,info);
	 }
	 @RequestMapping("/question")
	 @ResponseBody
	 public Response<List<GoodsQuestionInfo>> findGoodsQuestion(Integer goodsId){
		 List<GoodsQuestionInfo> info = goodsService.findGoodsQuestionInfo(goodsId);
		 return new Response<>(ResultEnum.SUCCESS,info);
	 }
	 /**
	  * 回答
	  * @param request
	  * @param id
	  * @param content
	  * @return
	  */
	 @RequestMapping("/saveanswer")
	 @ResponseBody
	 public Response<String> saveanswer(HttpServletRequest request,Integer id,String content){
		 LoginUserInfo user = (LoginUserInfo)request.getSession().getAttribute(Constant.SESSION_USER);
		 if(user==null) {
			 return Response.error("请先登录！");
		 }
		 if(content==null||"".equals(content)) {
			 return Response.error("输入回答！");
		 }
		 Integer userId = Integer.parseInt(user.getLoginId());
		 return goodsService.saveanswer(id, content, userId);
	 }
	 @RequestMapping("/addQuestion")
	 @ResponseBody
	 public Response<String> addGoodsQuestion(HttpServletRequest request,Integer goodsId,String content){
		 LoginUserInfo user = (LoginUserInfo)request.getSession().getAttribute(Constant.SESSION_USER);
		 if(user==null) {
			 return Response.error("请先登录！");
		 }
		 if(content==null||"".equals(content)) {
			 return Response.error("输入问提！");
		 }
		 Integer userId = Integer.parseInt(user.getLoginId());
		 return goodsService.addGoodsQuestion(goodsId,content,userId);
	 }
	 
	 @RequestMapping("/skuGoods")
	 @ResponseBody
	 public Response<GoodsSkuDetail> skuInfo(Integer goodsId,String specsIds){
		 GoodsSku sku = new GoodsSku(); 
		 return new Response<>(ResultEnum.SUCCESS,goodsService.findSkuInfo(goodsId, specsIds));
	 }
}
