package com.qzdsoft.eshop.controller.pc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.controller.admin.BaseController;
import com.qzdsoft.eshop.domain.user.User;
import com.qzdsoft.eshop.service.advertisement.AdvertisementService;
import com.qzdsoft.eshop.service.deliverytemplate.AreaService;
import com.qzdsoft.eshop.service.goods.GoodsService;
import com.qzdsoft.eshop.service.goodsclass.GoodsClassService;
import com.qzdsoft.eshop.service.sys.LoginService;
import com.qzdsoft.eshop.vo.advertisement.AdvertisementInfo;
import com.qzdsoft.eshop.vo.goods.pc.GoodsSearchInfo;
import com.qzdsoft.eshop.vo.goods.pc.GoodsSearchQueryInfo;
import com.qzdsoft.eshop.vo.goods.pc.PackageGoodsInfo;
import com.qzdsoft.eshop.vo.goods.pc.StartGoodsInfo;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassImage;
import com.qzdsoft.eshop.vo.goodsclass.pc.ShowIndexClassInfo;
import com.qzdsoft.eshop.vo.sys.LoginUserInfo;
import com.qzdsoft.utils.Constant;
import com.qzdsoft.vo.Page;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;

/**
 * 
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2018年1月24日
 * @see
 * @since 1.0
 */
@Controller("pcIndex")
@RequestMapping("/")
public class IndexController extends BaseController{
	 private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IndexController.class);
	 private static final String BASEPATH = "pc";
	
	 @Autowired
	 private AdvertisementService advertisementService;
	 @Autowired
	 private GoodsClassService goodsClassService;
	 @Autowired
	 private GoodsService goodsService;
	 @Autowired
	 private LoginService loginService;
	 @Autowired
	 private AreaService areaService;
   /**
    *
    * @param request
    * @param model
    * @return
    */
   @RequestMapping("/")
   public ModelAndView index() {
       logger.debug("进入首页。。。。。");
       ModelAndView mav = new ModelAndView();
       LoginUserInfo user = getUser();
       String islogin = "";
       if(user != null) {
    	   islogin = user.getName();
       }
	   List<AdvertisementInfo> ads = advertisementService.list();//首页轮播广告
	   List<GoodsClassImage> gcs = goodsClassService.queryAllClass();//所有分类
	   List<ShowIndexClassInfo> scs = goodsClassService.queryShowIndexClass();//首页显示的分类
	   List<StartGoodsInfo> startgoods = goodsService.queryStartGoods();//明星产品
	   List<PackageGoodsInfo> packageGoods = goodsService.queryShowIndexPackageGoods();//首页显示的套餐
	   mav.addObject("ads", ads);
	   mav.addObject("logstatus", islogin);
	   mav.addObject("gcs", gcs);
	   mav.addObject("scs", scs);
	   mav.addObject("sg", startgoods);
	   mav.addObject("pgs", packageGoods);
	   mav.setViewName(BASEPATH+"/index");
       return mav;
   }
   
   @RequestMapping("/onlogin")
   public ModelAndView onlogin(String code,String state) {
       logger.debug("第三方登入首页。。。。。");
       User user1 = loginService.weichatlogin(code, state);
       
       ModelAndView mv = new ModelAndView();
       if(user1.getUserId()!=null) {
    	   LoginUserInfo userInfo = new LoginUserInfo();
    	   userInfo.setLoginId(user1.getUserId().toString());
    	   userInfo.setName(user1.getUserName());
    	   request.getSession().setAttribute(Constant.SESSION_USER, userInfo);
    	   mv.addObject("logstatus", userInfo.getName());
    	   //处理address
    	   if(user1.getAddress().length()>0) {
    		   String[] addr = user1.getAddress().split(",");
    		   String address = areaService.selectById(Integer.parseInt(addr[0])).getName()+areaService.selectById(Integer.parseInt(addr[1])).getName()+areaService.selectById(Integer.parseInt(addr[2])).getName();
    		   user1.setAddress(address);
    		   mv.addObject("proviceId",addr[0]);
    		   mv.addObject("cityId",addr[1]);
    		   mv.addObject("citys",  areaService.findAreaById(Integer.parseInt(addr[0])));
    		   if(addr.length>1) {
    			   mv.addObject("townId",addr[2]);
    			   mv.addObject("towns",  areaService.findAreaById(Integer.parseInt(addr[1])));
    		   }
    	   }
    	   mv.addObject("user", user1);
    	   mv.addObject("area1", areaService.findAreaById(0));
    	   mv.setViewName("pc/user/pinfo");
       }else {
    	   logger.debug("进入首页。。。。。");
           LoginUserInfo user = getUser();
           String islogin = "";
           if(user != null) {
        	   islogin = user.getName();
           }
    	   List<AdvertisementInfo> ads = advertisementService.list();//首页轮播广告
    	   List<GoodsClassImage> gcs = goodsClassService.queryAllClass();//所有分类
    	   List<ShowIndexClassInfo> scs = goodsClassService.queryShowIndexClass();//首页显示的分类
    	   List<StartGoodsInfo> startgoods = goodsService.queryStartGoods();//明星产品
    	   List<PackageGoodsInfo> packageGoods = goodsService.queryShowIndexPackageGoods();//首页显示的套餐
    	   mv.addObject("ads", ads);
    	   mv.addObject("logstatus", islogin);
    	   mv.addObject("gcs", gcs);
    	   mv.addObject("scs", scs);
    	   mv.addObject("sg", startgoods);
    	   mv.addObject("pgs", packageGoods);
    	   mv.setViewName(BASEPATH+"/index");
           return mv;
       }
       return mv;
   }
   
   /**
    * 商品搜索
    * @param keyword
    * @return
    */
   @RequestMapping("/goods_search")
   public ModelAndView goodsSearch(GoodsSearchQueryInfo info) {
	   ModelAndView mav = new ModelAndView();
	   String keyWord = info.getKeyWord()!=null?info.getKeyWord():"";
	   String page = "/search";
	   if(info.isFirst()) {
		   info.setPage(0);
	   }
	   Page<GoodsSearchInfo> goodsList = goodsService.goodsSearchByQuery(info);
	   if(goodsList.getTotalRecordNum() == 0) {
		   page = "/search_false";
	   }
	   mav.addObject("data", goodsList);
	   mav.addObject("keyWord", keyWord);
	   mav.setViewName(BASEPATH+page);
       return mav;
   }
   
   @RequestMapping("/checklogin")
   @ResponseBody
   public Response<Map<String,Object>> checkLogin() {
	   LoginUserInfo user = getUser();
	   Map<String,Object> result = new HashMap<String,Object>();
	   result.put("flag", false);
	   if(user!=null) {
		   result.put("flag", true);
		   result.put("account", user.getPhone());
	   }
	   return new Response<>(ResultEnum.SUCCESS,result);
   }
   
}
