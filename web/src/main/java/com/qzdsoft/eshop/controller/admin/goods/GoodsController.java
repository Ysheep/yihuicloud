package com.qzdsoft.eshop.controller.admin.goods;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.domain.goods.Goods;
import com.qzdsoft.eshop.domain.goods.GoodsSku;
import com.qzdsoft.eshop.service.deliverytemplate.DeliveryTemplateService;
import com.qzdsoft.eshop.service.goods.AccessoryService;
import com.qzdsoft.eshop.service.goods.GoodsService;
import com.qzdsoft.eshop.service.goodsclass.GoodsClassService;
import com.qzdsoft.eshop.service.sys.Permission;
import com.qzdsoft.eshop.vo.goods.GoodsAddInfo;
import com.qzdsoft.eshop.vo.goods.GoodsInfo;
import com.qzdsoft.eshop.vo.goods.GoodsParamInfo;
import com.qzdsoft.eshop.vo.goods.GoodsPropertiesInfo;
import com.qzdsoft.eshop.vo.goods.GoodsPropertiesSpecInfo;
import com.qzdsoft.eshop.vo.goods.GoodsPropertyConfigEditInfo;
import com.qzdsoft.eshop.vo.goods.GoodsQueryInfo;
import com.qzdsoft.eshop.vo.goods.GoodsSkuInfo;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassQuery;
import com.qzdsoft.eshop.vo.sys.SysConfigInfo;
import com.qzdsoft.utils.ExceptionUtil;
import com.qzdsoft.utils.file.FileUtil;
import com.qzdsoft.vo.GoodsStatus;
import com.qzdsoft.vo.LayTableResponse;
import com.qzdsoft.vo.LayeditResponse;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.SysConfigCode;
import com.qzdsoft.vo.TypeInfo;

@Controller("adminGoodsController")
@RequestMapping("/admin/goods")
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsClassService goodsClassService;
	@Autowired
	private AccessoryService accessoryService;
	@Autowired
	private DeliveryTemplateService deliveryTemplateService;
	
	private final static String BASCPATH = "admin/goods";
	private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
	
	@Permission(validate=false)
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName(BASCPATH+"/index");
		return mv;
	}
	
	@RequestMapping("/data")
	@ResponseBody
	public LayTableResponse<GoodsInfo> list(GoodsQueryInfo info) {
		logger.info("isrecomend={}",info.getIsRecommend());
		return new LayTableResponse<GoodsInfo>(goodsService.findAllGoods(info));
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Response<String> delete(Integer id){
		try {
			return goodsService.delet(id);
		} catch (RuntimeException e) {
			return Response.error(e.getMessage());
		} catch (Exception ex) {
			return Response.error("删除失败");
		}
	}
	
	@Permission(validate=false)
	@RequestMapping("/edit")
	public ModelAndView edit(Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("classList", goodsClassService.findGoodsClass(new GoodsClassQuery()));
		mv.addObject("status", GoodsStatus.ALL);
		mv.addObject("template", deliveryTemplateService.getAllDeliveryTemplates());
		mv.addObject("systemList",goodsService.getAllSystem());
		if(id == null) {
			mv.setViewName(BASCPATH+"/add");
		}else {
			Goods goods = goodsService.findByGoodsId(id);
			mv.addObject("goods",goods);
			mv.addObject("properties", goodsClassService.getproperties(goods.getClassId()));
			mv.addObject("goodsImg", goodsService.findImgById(id));
			List<GoodsSkuInfo> gsku = goodsService.findGoodsSkuById(id);
			StringBuffer sb = new StringBuffer();
			for(GoodsSku s : gsku) {
				String[] spec = s.getSpecIds().split(",");
				StringBuffer sbf = new StringBuffer();
				for(int i=0;i<spec.length;i++) {
					sbf.append(spec[i].split(":")[1]);
					if(i != spec.length-1) {
						sbf.append(",");
					}
				}
				sb.append(sbf.toString()+",");
				s.setSpecIds(sbf.toString());
			}
			mv.addObject("specs", sb);
			mv.addObject("goodsSku", gsku);
			mv.setViewName(BASCPATH+"/edit");
		}
		return mv;
	}
	
	@RequestMapping("/execute_add")
	@ResponseBody
	public Response<String> executeAdd(@RequestBody GoodsAddInfo info){
		try {
			return goodsService.saveadd(info);
		} catch (RuntimeException e) {
			logger.error("商品新增异常:{}",e.getMessage());
			return Response.error(e.getMessage());
		}catch (Exception ex) {
			logger.error("商品新增异常:{}",ex.getMessage());
			return new Response<String>(ResultEnum.ERROR);
		}
	}
	@RequestMapping("/execute_edit")
	@ResponseBody
	public Response<String> executeEdit(@RequestBody GoodsAddInfo info){
		try {
			return goodsService.saveUpd(info);
		} catch (RuntimeException e) {
			logger.error("商品修改异常:{}",e.getMessage());
			return Response.error(e.getMessage());
		}catch (Exception ex) {
			logger.error("商品修改异常:{}",ex.getMessage());
			return new Response<String>(ResultEnum.ERROR);
		}
	}
	
	@Permission(validate=false)
	@RequestMapping("/getproperties")
	@ResponseBody
	public List<GoodsPropertiesSpecInfo> getproperties(Integer classId){
		return goodsClassService.getproperties(classId);
	}
	
   //文件上传相关代码
	@Permission(validate=false)
    @RequestMapping("/upload")
    @ResponseBody
    public Response<TypeInfo> upload(MultipartFile file) {
        if (file.isEmpty()) {
            return new Response<TypeInfo>(ResultEnum.ERROR);
        }
        // 文件上传后的路径
        String uploadPath = FileUtil.createUserFolder(SysConfigInfo.getValue(SysConfigCode.IMAGEUPLOAD), "goods");
        //图片访问路径
        String urlPath = FileUtil.createUserFolderURL( SysConfigInfo.getValue(SysConfigCode.IMAGELOAD), "goods");
        logger.info("上传的文件名为：" + file.getOriginalFilename());
        // 获取文件的后缀名
        String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        String fileName = UUID.randomUUID() + suffixName;
        try {
        	File dest= FileUtil.createFolder(uploadPath,fileName);
        	logger.debug("上传路径{}",dest.getAbsolutePath());
            file.transferTo(dest);
            String url = urlPath+File.separator+fileName;
            return new Response<TypeInfo>(ResultEnum.SUCCESS,accessoryService.saveAccessory(url, fileName));
        } catch (IllegalStateException e) {
            e.printStackTrace();
            logger.error("图片上传失败,错误信息:{}",ExceptionUtil.getStackTrace(e));
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("图片上传失败,错误信息:{}",ExceptionUtil.getStackTrace(ex));
        }
        return new Response<TypeInfo>(ResultEnum.ERROR);
    }
		@Permission(validate=false)
	 	@RequestMapping("/sjgoods")
	 	public ModelAndView sjgoods(Integer goodsId) {
	 		ModelAndView mv = new ModelAndView();
	 		mv.addObject("goodId", goodsId);
	 		mv.setViewName(BASCPATH+"/goodsUp");
	 		return mv;
	 	}
	    /**
	     * 商品上下架
	     * @param info
	     * @return
	     */
	    @RequestMapping("/sxjgoods")
		@ResponseBody
		public Response<String> sxgoods(Integer goodsId,Integer type,String start,String end){
			try {
				return goodsService.undercart(goodsId, type,start,end);
			}catch(Exception e) {
				logger.error(e.getMessage());
				return new Response<String>(ResultEnum.ERROR);
			}
		}
	    
	    @Permission(validate=false)
	    @RequestMapping("/pictureUpload")
	    @ResponseBody
	    public LayeditResponse<Map<String,String>> pictureUpload(MultipartFile file) {
	    	 if (file.isEmpty()) {
		            return new LayeditResponse<Map<String,String>>(1,"没有上传文件");
		        }
		        // 文件上传后的路径
		        String uploadPath = FileUtil.createUserFolder(SysConfigInfo.getValue(SysConfigCode.IMAGEUPLOAD), "goodsDetail");
		        //图片访问路径
		        String urlPath = FileUtil.createUserFolderURL( SysConfigInfo.getValue(SysConfigCode.IMAGELOAD), "goodsDetail");
		        logger.info("上传的文件名为：" + file.getOriginalFilename());
		        // 获取文件的后缀名
		        String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		        logger.info("上传的后缀名为：" + suffixName);
		        String fileName = UUID.randomUUID() + suffixName;
		        File dest= FileUtil.createFolder(uploadPath,fileName);
		        try {
		            file.transferTo(dest);
		            String url = urlPath+File.separator+fileName;
		            Map<String,String> result = new HashMap<String,String>();
		            TypeInfo info = accessoryService.saveAccessory(url, fileName);
		            result.put("src", url);
		            return new LayeditResponse<Map<String,String>>(0,"上传成功",result);
		        } catch (IllegalStateException e) {
		            e.printStackTrace();
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		        return new LayeditResponse<Map<String,String>>(1,"上传失败");
	    }
	    @Permission(validate=false)
	    @RequestMapping("/commentUpload")
	    @ResponseBody
	    public Response<TypeInfo> pictureCommentUpload(MultipartFile file) {
	    	if (file.isEmpty()) {
	    		return new Response<TypeInfo>(ResultEnum.ERROR);
	    	}
	    	// 文件上传后的路径
	    	String uploadPath = FileUtil.createUserFolder(SysConfigInfo.getValue(SysConfigCode.IMAGEUPLOAD), "Comment");
	    	//图片访问路径
	    	String urlPath = FileUtil.createUserFolderURL( SysConfigInfo.getValue(SysConfigCode.IMAGELOAD), "Comment");
	    	logger.info("上传的文件名为：" + file.getOriginalFilename());
	    	// 获取文件的后缀名
	    	String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
	    	logger.info("上传的后缀名为：" + suffixName);
	    	String fileName = UUID.randomUUID() + suffixName;
	    	File dest= FileUtil.createFolder(uploadPath,fileName);
	    	try {
	    		file.transferTo(dest);
	    		String url = urlPath+File.separator+fileName;
	    		TypeInfo info = accessoryService.saveAccessory(url, fileName);
	    		return new Response<TypeInfo>(ResultEnum.SUCCESS,info);
	    	} catch (IllegalStateException e) {
	    		e.printStackTrace();
	    	} catch (Exception ex) {
	    		ex.printStackTrace();
	    	}
	    	return new Response<TypeInfo>(ResultEnum.ERROR);
	    }
	    
	    
	    @Permission(validate=false)
	    @RequestMapping("/isrecommend")
	    @ResponseBody
	    public Response<String> setRecommend(Integer goodsId,Integer isRecommend) {
	    	try {
	    		return goodsService.setRecommend(goodsId, isRecommend);
			} catch (Exception e) {
				logger.error("更新商品明星产品状态,错误信息:{}",e.getMessage());
			}
	    	return Response.error("失败");
	    }
	    /**
	     * 商品套餐首页展示
	     * @param info
	     * @return
	     */
	    @RequestMapping("/isPackageShow")
		@ResponseBody
		public Response<String> goodsIsPackage(Integer goodsId,Integer type){
			try {
				return goodsService.isPackage(goodsId, type);
			}catch(Exception e) {
				logger.error(e.getMessage());
				return new Response<String>(ResultEnum.ERROR);
			}
		}
	    //商品参数设置start
	    
	    @Permission(validate=false)
		@RequestMapping("/properties")
		public ModelAndView provalue(Integer id) {
			ModelAndView mv = new ModelAndView();
			mv.addObject("goodsId", id);
			mv.setViewName(BASCPATH+"/detail");
			return mv;
		}
	    
	    /**
		 * 参数值查询
		 * @param propertyId
		 * @return
		 */
		@Permission(validate=false)
		@RequestMapping("/prospecdetails")
		@ResponseBody
		public ModelAndView proSpecDetails(Integer goodsId) {
			ModelAndView mv = new ModelAndView();
			mv.addObject("goodsId", goodsId);
			
			mv.addObject("details", goodsService.findParamsById(goodsId));
			mv.setViewName(BASCPATH+"/detailItem");
			return mv;
		}
		
		/**
		 * 分类属性值编辑
		 * @param info
		 * @return
		 */
		@ResponseBody
	    @RequestMapping("/edit-prospecdetails")
	    public Response<String> edit(@RequestBody GoodsParamInfo info) {
	       
	        try
	        {
	            return goodsService.editParams(info);
	        }
	        catch (Exception ex)
	        {
	        	ex.printStackTrace();
	        	return new Response<String>(ResultEnum.ERROR,ex.getMessage());
	        }
	    }
		
		
		@ResponseBody
		@RequestMapping("/remove-prospec")
		public Response<String> remove(@RequestBody GoodsParamInfo info) {
			
			
			try
			{
				return goodsService.deletParam(info);
			}
			catch (Exception ex)
			{
				return new Response<String>(ResultEnum.ERROR,ex.getMessage());
			}
		}
		/**
		 * 属性设置
		 * @param goodsId
		 * @return
		 */
		@Permission(validate=false)
		@RequestMapping("/pro_config")
		public ModelAndView proConfig(Integer goodsId){
			ModelAndView mav = new ModelAndView();
			mav.addObject("goodsId",goodsId);
			mav.addObject("pros", goodsService.findGoodsProById(goodsId));
			mav.setViewName(BASCPATH+"/propertiesconfig");
			return mav;
			
		}
		/**
		 * 属性设置保存
		 * @return
		 */
		@RequestMapping("pros_save")
		@ResponseBody
		public Response<String> saveGoodsPros(@RequestBody GoodsPropertyConfigEditInfo info) {
			try {
				return goodsService.saveGoodsPros(info);
			} catch (Exception e) {
				logger.error("商品属性设置错误，异常信息:{}",ExceptionUtil.getStackTrace(e));
			}
			
			return Response.error("失败");
		}
		
		@Permission(validate=false)
		@RequestMapping("/findpros")
		@ResponseBody
		public List<GoodsPropertiesInfo> findpros(Integer goodsId){
			return  goodsService.findGoodsProById(goodsId);
		}
		
}
