package com.qzdsoft.eshop.service.goods.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qzdsoft.eshop.domain.goods.Accessory;
import com.qzdsoft.eshop.domain.goods.Answer;
import com.qzdsoft.eshop.domain.goods.Goods;
import com.qzdsoft.eshop.domain.goods.GoodsImg;
import com.qzdsoft.eshop.domain.goods.GoodsParams;
import com.qzdsoft.eshop.domain.goods.GoodsPropertiesSpecImg;
import com.qzdsoft.eshop.domain.goods.GoodsSku;
import com.qzdsoft.eshop.domain.goods.Question;
import com.qzdsoft.eshop.domain.goodsclass.GoodsProperties;
import com.qzdsoft.eshop.domain.goodsclass.GoodsPropertiesSpec;
import com.qzdsoft.eshop.mapper.goods.AccessoryMapper;
import com.qzdsoft.eshop.mapper.goods.AnswerMapper;
import com.qzdsoft.eshop.mapper.goods.CommentQueryMapper;
import com.qzdsoft.eshop.mapper.goods.GoodsImgMapper;
import com.qzdsoft.eshop.mapper.goods.GoodsMapper;
import com.qzdsoft.eshop.mapper.goods.GoodsParamMapper;
import com.qzdsoft.eshop.mapper.goods.GoodsPropertiesSpecImgMapper;
import com.qzdsoft.eshop.mapper.goods.GoodsQueryMapper;
import com.qzdsoft.eshop.mapper.goods.GoodsQuestionMapper;
import com.qzdsoft.eshop.mapper.goods.GoodsSkuMapper;
import com.qzdsoft.eshop.mapper.goods.GoodsSkuQueryMapper;
import com.qzdsoft.eshop.mapper.goods.QuestionMapper;
import com.qzdsoft.eshop.mapper.goodsclass.GoodsPropertiesMapper;
import com.qzdsoft.eshop.mapper.goodsclass.GoodsPropertiesSpecMapper;
import com.qzdsoft.eshop.service.goods.GoodsService;
import com.qzdsoft.eshop.vo.goods.GoodsAddDetail;
import com.qzdsoft.eshop.vo.goods.GoodsAddInfo;
import com.qzdsoft.eshop.vo.goods.GoodsDetailInfo;
import com.qzdsoft.eshop.vo.goods.GoodsImgInfo;
import com.qzdsoft.eshop.vo.goods.GoodsInfo;
import com.qzdsoft.eshop.vo.goods.GoodsListInfo;
import com.qzdsoft.eshop.vo.goods.GoodsParamEditInfo;
import com.qzdsoft.eshop.vo.goods.GoodsParamInfo;
import com.qzdsoft.eshop.vo.goods.GoodsPropertiesInfo;
import com.qzdsoft.eshop.vo.goods.GoodsPropertyConfigEditInfo;
import com.qzdsoft.eshop.vo.goods.GoodsQueryInfo;
import com.qzdsoft.eshop.vo.goods.GoodsSkuInfo;
import com.qzdsoft.eshop.vo.goods.pc.GoodsCommentInfo;
import com.qzdsoft.eshop.vo.goods.pc.GoodsQuestionInfo;
import com.qzdsoft.eshop.vo.goods.pc.GoodsSearchInfo;
import com.qzdsoft.eshop.vo.goods.pc.GoodsSearchQueryInfo;
import com.qzdsoft.eshop.vo.goods.pc.GoodsSkuDetail;
import com.qzdsoft.eshop.vo.goods.pc.GoodsSpecInfo;
import com.qzdsoft.eshop.vo.goods.pc.PackageGoodsInfo;
import com.qzdsoft.eshop.vo.goods.pc.StartGoodsInfo;
import com.qzdsoft.vo.DeleteStatus;
import com.qzdsoft.vo.GoodsSkuType;
import com.qzdsoft.vo.GoodsStatus;
import com.qzdsoft.vo.Page;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.TypeInfo;

@Service
public class GoodsServiceImpl implements GoodsService{

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(GoodsServiceImpl.class);
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsSkuMapper goodsSkuMapper;
	@Autowired
	private GoodsImgMapper goodsImgMapper;
	@Autowired
	private GoodsParamMapper goodsParamMapper;
	
	@Autowired
	private GoodsQueryMapper goodsQueryMapper;
	
	@Autowired
	private GoodsPropertiesMapper goodsPropertiesMapper;
	
	@Autowired
	private GoodsPropertiesSpecMapper goodsPropertiesSpecMapper;
	
	@Autowired
	private GoodsSkuQueryMapper goodsSkuQueryMapper;
	
	@Autowired
	private CommentQueryMapper commentQueryMapper;
	
	@Autowired
	private GoodsQuestionMapper goodsQuestionMapper;
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private AnswerMapper AnswerMapper;
	@Autowired
	private AccessoryMapper accessoryMapper;
	
	@Autowired
	private GoodsPropertiesSpecImgMapper goodsPropertiesSpecImgMapper;
//	@Autowired
//	private UserAddressQueryMapper userAddressQueryMapper;
//	
//	@Autowired
//	private DeliveryTemplateQueryMapper deliveryTemplateQueryMapper;
//	@Autowired
//	private DeliveryTemplateMapper deliveryTemplateMapper;
	
	
	@Override
	public List<GoodsInfo> findAllGoods(GoodsQueryInfo info) {
		logger.info("iscommend2={}",info.getIsRecommend());
		PageHelper.startPage(info.getPage(), info.getLimit());
		List<GoodsInfo> list = goodsQueryMapper.findAllGoodsInfo(info);
		return list;
	}

	@Override
	@Transactional
	public Response<String> saveadd(GoodsAddInfo info) {
		Goods goods = new Goods();
		goods.setName(info.getName());
		goods.setDescript(info.getDescript());
		goods.setDecode(info.getDecode());
		goods.setVedio(info.getVedio());
		goods.setSystemId(info.getSystemId());
		goods.setClassId(Integer.parseInt(info.getClassId()));
		goods.setIsPackage(info.getIsPackage());
		goods.setPackageIndexShow(0);
		goods.setCtime(new Date());
		goods.setDeleteStatus(Short.valueOf(GoodsStatus.USE_CODE));
		goods.setPrice(new BigDecimal(info.getInitiprice()));
		goods.setTemplateId(info.getTemplateId());
		goods.setStartTime(info.getStartDate());
		goods.setDetail(info.getDetail());
		if(info.getEndDate() == null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				goods.setEndTime(sdf.parse("9999-12-31 23:59:59"));
			} catch (ParseException e) {
				logger.error("商品新增保存时间转换错误{}"+e.getMessage());
			}
		}else {
			goods.setEndTime(info.getEndDate());
		}
		
		goodsMapper.insert(goods);
		if(info.getIds().size()>0) {
			List<GoodsImg> goodsImglist= new ArrayList<GoodsImg>();
			for(Integer i : info.getIds()) {
				GoodsImg gds = new GoodsImg();
				gds.setAccessoryId(i);
				gds.setGoodsId(goods.getGoodsId());
				if(i.equals(info.getPictureMainId())) {
					gds.setIsMasterImg(Short.valueOf("1"));
				}else {
					gds.setIsMasterImg(Short.valueOf("0"));
				}
				goodsImglist.add(gds);
			}
			int number = goodsImgMapper.insertList(goodsImglist);
			if(number != info.getIds().size()) {
				throw new RuntimeException("保存失败");
			}
		}
		if(info.getItems().size()>0) {
			List<GoodsSku> gslist= new ArrayList<GoodsSku>();
			for(GoodsAddDetail gad : info.getItems()) {
				GoodsSku gdsku = new GoodsSku();
				if(gad.getPicturePath()!=null && !"".equals(gad.getPicturePath())) {
					gdsku.setAccessoryId(Integer.parseInt(gad.getPicturePath()));
				}
				gdsku.setGoodsId(goods.getGoodsId());
				gdsku.setName(goods.getName());
				if(gad.getPrice()==null || "".equals(gad.getPrice().toString())) {
					continue;
				}
				gdsku.setPrice(gad.getPrice());
				gdsku.setDeleteStatus(0);
				gdsku.setType(0);
				String[] spcId = gad.getSpecIds().split(",");
				StringBuffer specIds2 = new StringBuffer();
				HashMap<Integer,String> map = new HashMap<Integer,String>();
				for(int i=0;i<spcId.length;i++) {
					StringBuffer specIds = new StringBuffer();
					Integer proper = goodsPropertiesSpecMapper.selectByPrimaryKey(Integer.parseInt(spcId[i])).getPropertyId();
					specIds.append(proper+":"+spcId[i]);
					map.put(proper, specIds.toString());
					
				}
				List<Map.Entry<Integer,String>> listmap = new ArrayList<Map.Entry<Integer,String>>(map.entrySet());
		        Collections.sort(listmap,new Comparator<Map.Entry<Integer,String>>() {
		            //升序排序
		            public int compare(Entry<Integer, String> o1,
		                    Entry<Integer, String> o2) {
		                return o1.getValue().compareTo(o2.getValue());
		            }
		            
		        });
		        Iterator<Entry<Integer, String>> iter = listmap.iterator();
		        while(iter.hasNext()){
		        	Entry<Integer, String> ent = iter.next();
		        	specIds2.append(ent.getValue()+",");
		        	
		        }
				
				gdsku.setSpecIds(specIds2.toString().substring(0, specIds2.toString().length()-1));
				gslist.add(gdsku);
			}
			int num = 0;
			num = goodsSkuMapper.insertList(gslist);	
			if(num != info.getItems().size()) {
				throw new RuntimeException("保存失败");
			}
		}else {
			GoodsSku gsu = new GoodsSku();
			gsu.setGoodsId(goods.getGoodsId());
			gsu.setPrice(goods.getPrice());
			gsu.setName(goods.getName());
			gsu.setDeleteStatus(0);
			gsu.setType(1);
			goodsSkuMapper.insert(gsu);
		}
		return new Response<String>(ResultEnum.SUCCESS);
	}
	
	@Override
	@Transactional
	public Response<String> saveUpd(GoodsAddInfo info) {
		
		Goods goods = goodsMapper.selectByPrimaryKey(info.getGoodsId());
		goods.setName(info.getName());
		goods.setDescript(info.getDescript());
		goods.setDecode(info.getDecode());
		goods.setVedio(info.getVedio());
		goods.setSystemId(info.getSystemId());
		goods.setIsPackage(info.getIsPackage());
		GoodsSku gsk = new GoodsSku();
		gsk.setGoodsId(info.getGoodsId());
//		gsk.setType(0);
		List<GoodsSku> list = goodsSkuMapper.select(gsk);
		if(!info.getClassId().equals(goods.getClassId().toString())) { //分类更改，sku删除
			int numb =0;
			for(GoodsSku sk : list) {
				sk.setDeleteStatus(1);
				numb += goodsSkuMapper.updateByPrimaryKeySelective(sk);
			}
			if(numb != list.size()) {
				throw new RuntimeException("保存失败");
			}
		}
		//更新商品信息
		goods.setClassId(Integer.parseInt(info.getClassId()));
		goods.setCtime(new Date());
//		goods.setDeleteStatus(Short.valueOf(GoodsStatus.USE_CODE));
		goods.setTemplateId(info.getTemplateId());
		goods.setStartTime(info.getStartDate());
		goods.setEndTime(info.getEndDate());
		goods.setPrice(new BigDecimal(info.getInitiprice()));
		goods.setDetail(info.getDetail());
		int gcount = goodsMapper.updateByPrimaryKeySelective(goods);
		if(gcount!=1) {
			logger.error("更新商品信息错误，更新条目不正确，count={}"+gcount);
			throw new RuntimeException("更新失败");
		}
		
		
		//更新主图
		if(info.getIds().size()>0) {
			GoodsImg gdelet = new GoodsImg();
			gdelet.setGoodsId(info.getGoodsId());
			goodsImgMapper.delete(gdelet);
			List<GoodsImg> goodsImglist= new ArrayList<GoodsImg>();
			for(Integer i : info.getIds()) {
				GoodsImg gds = new GoodsImg();
				gds.setAccessoryId(i);
				gds.setGoodsId(goods.getGoodsId());
				if(i.equals(info.getPictureMainId())) {
					gds.setIsMasterImg(Short.valueOf("1"));
				}else {
					gds.setIsMasterImg(Short.valueOf("0"));
				}
				goodsImglist.add(gds);
			}
			int number = goodsImgMapper.insertList(goodsImglist);
			if(number != info.getIds().size()) {
				throw new RuntimeException("保存失败");
			}
		}
		//更新sku
		List<GoodsSku> gslist= new ArrayList<GoodsSku>();//修改
		List<GoodsSku> gslistadd= new ArrayList<GoodsSku>();//新增
		List<GoodsSku> gslistremove= new ArrayList<GoodsSku>();//删除
		List<Integer> removeIds= new ArrayList<Integer>();
		if(info.getItems().size()>0) {
			for(GoodsAddDetail gad : info.getItems()) {
				GoodsSku gdsku = new GoodsSku();
				if(gad.getPicturePath()!=null && !"".equals(gad.getPicturePath())) {
					gdsku.setAccessoryId(Integer.parseInt(gad.getPicturePath()));
				}
				gdsku.setGoodsId(goods.getGoodsId());
				gdsku.setName(goods.getName());
				if(gad.getPrice()==null|| "".equals(gad.getPrice().toString())) {
					if(gad.getSkuId()!=null) {
						GoodsSku go = new GoodsSku();
						go.setSkuId(gad.getSkuId());
						go.setDeleteStatus(1);
						gslistremove.add(go);
					}
					continue;
				}
				gdsku.setPrice(gad.getPrice());
				String[] spcId = gad.getSpecIds().split(",");
				StringBuffer specIds2 = new StringBuffer();
				HashMap<Integer,String> map = new HashMap<Integer,String>();
				for(int i=0;i<spcId.length;i++) {
					StringBuffer specIds = new StringBuffer();
					Integer proper = goodsPropertiesSpecMapper.selectByPrimaryKey(Integer.parseInt(spcId[i])).getPropertyId();
					specIds.append(proper+":"+spcId[i]);
					map.put(proper, specIds.toString());
					
				}
				List<Map.Entry<Integer,String>> listmap = new ArrayList<Map.Entry<Integer,String>>(map.entrySet());
		        Collections.sort(listmap,new Comparator<Map.Entry<Integer,String>>() {
		            //升序排序
		            public int compare(Entry<Integer, String> o1,
		                    Entry<Integer, String> o2) {
		                return o1.getValue().compareTo(o2.getValue());
		            }
		            
		        });
		        Iterator<Entry<Integer, String>> iter = listmap.iterator();
		        while(iter.hasNext()){
		        	Entry<Integer, String> ent = iter.next();
		        	specIds2.append(ent.getValue()+",");
		        	
		        }
				gdsku.setSpecIds(specIds2.toString().substring(0, specIds2.toString().length()-1));
				if(gad.getSkuId()!=null) {
					removeIds.add(gad.getSkuId());
					gdsku.setSkuId(gad.getSkuId());
					gslist.add(gdsku);
				}else {
					gdsku.setDeleteStatus(0);
					gdsku.setType(0);
					gslistadd.add(gdsku);
				}
			}
			for(GoodsSku is : list) {
				boolean rm = true;
				for(Integer i : removeIds) {
					if(i.equals(is.getSkuId())) {
						rm=false;break;
					}
				}
				if(rm) {
					GoodsSku go = new GoodsSku();
					go.setSkuId(is.getSkuId());
					go.setDeleteStatus(1);
					gslistremove.add(go);
				}
			}
			int num = 0;
			if(gslistadd.size()>0) {
				num = goodsSkuMapper.insertList(gslistadd);	
				if(num != gslistadd.size()) {
					throw new RuntimeException("保存失败");
				}
			}
			
			num =0;
			if(gslist.size()>0) {
				for(GoodsSku record : gslist) {
					num += goodsSkuMapper.updateByPrimaryKeySelective(record);
				}
				if(num != gslist.size()) {
					throw new RuntimeException("保存失败");
				}
			}
			num =0;
			if(gslistremove.size()>0) {
				for(GoodsSku record : gslistremove) {
					num += goodsSkuMapper.updateByPrimaryKeySelective(record);
				}
				if(num != gslistremove.size()) {
					throw new RuntimeException("保存失败");
				}
			}
		}else {
			//没有属性 更新非默认sku为删除状态
			GoodsSku delrecord = new GoodsSku();
			delrecord.setGoodsId(info.getGoodsId());
			delrecord.setType(0);
			List<GoodsSku> dellist = goodsSkuMapper.select(delrecord);
			int delnumb = 0;
			if(dellist.size()>0) {
				for(GoodsSku sk : dellist) {
					sk.setDeleteStatus(1);
					delnumb += goodsSkuMapper.updateByPrimaryKeySelective(sk);
				}
				if(delnumb != dellist.size()) {
					logger.error("属性为空，更新非默认sku为删除状态，删除条目不正确，delnumb={}",dellist.size());
					throw new RuntimeException("保存失败");
				}
			}
			
			
			
			//更新默认的商品sku 并启用默认
			GoodsSku gsu = new GoodsSku();
			gsu.setGoodsId(goods.getGoodsId());
			gsu.setType(1);
			GoodsSku gsu2 = goodsSkuMapper.selectOne(gsu);
			if(gsu2!=null) {
				gsu2.setPrice(goods.getPrice());
				gsu2.setDeleteStatus(Integer.parseInt(GoodsStatus.USE_CODE));
				gsu.setName(info.getName());
				goodsSkuMapper.updateByPrimaryKeySelective(gsu2);
			}else{
				GoodsSku defaultSku = new GoodsSku();
				defaultSku.setGoodsId(goods.getGoodsId());
				defaultSku.setName(goods.getName());
				defaultSku.setType(1);
				defaultSku.setPrice(goods.getPrice());
				defaultSku.setDeleteStatus(Integer.parseInt(GoodsStatus.USE_CODE));
				goodsSkuMapper.insertSelective(defaultSku);
			}
		}
		
		return new Response<String>(ResultEnum.SUCCESS);
	}

	@Override
	@Transactional
	public Response<String> delet(Integer id) {
		Goods goods = goodsMapper.selectByPrimaryKey(id);
		goods.setDeleteStatus(Short.valueOf("1"));
		goodsMapper.updateByPrimaryKeySelective(goods);
		GoodsSku gs = new GoodsSku();
		gs.setGoodsId(id);
		gs.setDeleteStatus(0);
		List<GoodsSku> list = goodsSkuMapper.select(gs);
		int num=0;
		for(GoodsSku gds: list) {
			gds.setDeleteStatus(1);
			num += goodsSkuMapper.updateByPrimaryKeySelective(gds);
		}
		if(num != list.size()) {
			throw new RuntimeException("删除失败");
		}
		return new Response<String>(ResultEnum.SUCCESS);
	}

	@Override
	public Goods findByGoodsId(Integer goodsId) {
		return goodsMapper.selectByPrimaryKey(goodsId);
	}



    @Override
    public GoodsDetailInfo findById(Integer goodsid)
    {
        Goods goods = goodsMapper.selectByPrimaryKey(goodsid);
        
        GoodsSku skua = new GoodsSku();
        skua.setGoodsId(goods.getGoodsId());
        skua.setDeleteStatus(DeleteStatus.USEING);
        skua.setType(GoodsSkuType.DEFAULT);
        GoodsSku goodsskua = goodsSkuMapper.selectOne(skua);
        
        
        GoodsDetailInfo info = new GoodsDetailInfo();
        info.setId(goods.getGoodsId());
        if(goodsskua!=null) {
        	info.setSkuId(goodsskua.getSkuId());
        }
        info.setId(info.getId());
        info.setName(goods.getName());
        info.setPrice(goods.getPrice());
        info.setPurchaseNum(goods.getPurchaseNum());
        info.setDecode(goods.getDecode());
        info.setDescript(goods.getDescript());
        info.setImages(goodsQueryMapper.findGoodsImages(goodsid));
        info.setDetail(goods.getDetail());
        
        List<GoodsPropertiesInfo> goodsPropertiesInfos = new ArrayList<>();
        GoodsSku skuRecord = new GoodsSku();
        skuRecord.setGoodsId(goods.getGoodsId());
        skuRecord.setDeleteStatus(DeleteStatus.USEING);
        skuRecord.setType(GoodsSkuType.NO_DEFAULT);
        List<GoodsSku> goodsSkus = goodsSkuMapper.select(skuRecord);
        if(!CollectionUtils.isEmpty(goodsSkus)) {
            Map<String, Set<String>> prosMap = new HashedMap<>();
            for (GoodsSku g : goodsSkus)
            {
                String specIds = g.getSpecIds();
                String specsStr[] = specIds.split(",");
                for (String str : specsStr)
                {
                    String specs[] = str.split(":");
                    String pro = specs[0];
                    String spec = specs[1];
                    Set<String> specList = prosMap.get(pro);
                    logger.debug("pro:{},spec:{},List:{}", pro, spec, specList);
                    if (CollectionUtils.isEmpty(specList))
                    {
                        Set<String> sps = new HashSet<>();
                        sps.add(spec);
                        prosMap.put(pro, sps);
                    }
                    else
                    {
                        specList.add(spec);
                    }
    
                }
            }
            logger.debug("propsMap:{}", prosMap);
            for (String pro : prosMap.keySet())
            {
                GoodsProperties properties = goodsPropertiesMapper
                        .selectByPrimaryKey(Integer.parseInt(pro));
                GoodsPropertiesInfo goodsPropertiesInfo = new GoodsPropertiesInfo();
                goodsPropertiesInfo.setProp(new TypeInfo(
                        properties.getPropertyId() + "", properties.getName()));
                goodsPropertiesInfos.add(goodsPropertiesInfo);
                Set<String> specs = prosMap.get(pro);
                List<GoodsSpecInfo> specsTypes = new ArrayList<>();
                for (String s : specs)
                {
                    GoodsPropertiesSpec spec = goodsPropertiesSpecMapper
                            .selectByPrimaryKey(Integer.parseInt(s));
                    GoodsSpecInfo goodsspec 
                     =	goodsPropertiesSpecMapper.selectBy(goodsid, Integer.parseInt(s));
                    if(goodsspec == null) {
                    	goodsspec = new GoodsSpecInfo();
                    }
                    goodsspec.setSpecId(spec.getSpecId());
                    goodsspec.setSpecName(spec.getName());
                    specsTypes.add(goodsspec);
                }
                goodsPropertiesInfo.setSpecs(specsTypes);
            }
        }
        if(goods.getVedio()!=null) {
        	Accessory  accessory = accessoryMapper.selectByPrimaryKey(goods.getVedio());
        	if(accessory!=null) {
        		info.setVideoUrl(accessory.getUrl());
        	}
        }
        
        info.setProps(goodsPropertiesInfos);
        return info;
    }

	@Override
	public List<GoodsImgInfo> findImgById(Integer goodsId) {
		return goodsQueryMapper.findImg(goodsId);
	}

	@Override
	public List<GoodsSkuInfo> findGoodsSkuById(Integer goodsId) {
//		GoodsSku gds = new GoodsSku();
//		gds.setGoodsId(goodsId);
//		gds.setDeleteStatus(0); //正常
//		gds.setType(0);
		return goodsSkuMapper.selectById(goodsId);
	   
	}

    /**
     * @see com.qzdsoft.eshop.service.goods.GoodsService#findSkuInfo(java.lang.Integer)
     */
    @Override
    public GoodsSkuDetail findSkuInfo(Integer goodsId,String specsIds)
    {
     
        String specs[] = specsIds.split(",");
        SortedMap<String, String> specMap = new TreeMap<>();
        
        for(String s:specs) {
            String val[] = s.split(":");
            specMap.put(val[0], val[1]);
        }
        StringBuffer buff = new StringBuffer();
        int i=0;
        for(String key:specMap.keySet()) {
            i++;
            if(i==1) {
                buff.append(key+":"+specMap.get(key));
            }else {
                buff.append(","+key+":"+specMap.get(key));
            }
            
        }
        specsIds = buff.toString();
//        GoodsSku record = new GoodsSku();
//        record.setGoodsId(goodsId);
//        record.setSpecIds(specsIds);
//        List<GoodsSku> goodsSku = goodsSkuMapper.select(record);
        return goodsSkuQueryMapper.findSkuInfo(goodsId, specsIds);
    }

	@Override
	@Transactional
	public Response<String> undercart(Integer goodsId, Integer type,String start,String end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
		if(type == 0) {
			//商品上架
			try {
				goods.setStartTime(sdf.parse(start));
				goods.setEndTime(sdf.parse(end));
			} catch (ParseException e) {
				e.printStackTrace();
				return Response.error("请填写正确的时间");
			}
		}else {
			//商品下架
			goods.setEndTime(date);
		}
		goodsMapper.updateByPrimaryKeySelective(goods);
		return new Response<String>(ResultEnum.SUCCESS);
	}

//	@Override
//	public GoodsCartInfo selectByMap(Map<String, Integer> map) {
//		Set<Entry<String, Integer>> entry = map.entrySet();
//		List<Integer> ids = new ArrayList<Integer>();
//		for(Entry<String, Integer> en:entry) {
//			ids.add(Integer.parseInt(en.getKey()));
//		}
//		logger.debug("ids={}",ids);
//		GoodsCartInfo info = new GoodsCartInfo();
//		BigDecimal total = new BigDecimal(0);
//		List<GoodsOrderInfo> goods = goodsSkuMapper.selectBySkuid(ids);
//		for(GoodsOrderInfo g:goods) {
//			Integer num = map.get(g.getSkuId()+"");
//			if(num==null || num == 0) {
//				g.setNum(1);
//			}else{
//				g.setNum(num);
//			}
//			total=total.add(g.getPrice().multiply(new BigDecimal(g.getNum())));
//			if(g.getSpecs()!=null && !"".equals(g.getSpecs())) {
//				String[] specId = g.getSpecs().split(",");
//				StringBuffer sb = new StringBuffer();
//				int i = 0;
//				for(String s : specId) {
//					String name = goodsPropertiesSpecMapper.selectByPrimaryKey(Integer.parseInt(s.split(":")[1])).getName();
//					if(i ==0) {
//						sb.append(name);
//						i++;
//					}else {
//						sb.append(","+name);
//					}
//				}
//				g.setSpecs(sb.toString());
//			}
//		}
//		info.setTotalAmount(total);
//		info.getGoods().addAll(goods);
//		List<TypeInfo> carryWay = deliveryTemplateQueryMapper.getCarryWayBySkuId(ids);
//		info.getCarryWay().addAll(carryWay);
//		return info;
//	}

	@Override
	public GoodsDetailInfo findBySkuid(Integer skuId,String counterNo) {
		GoodsDetailInfo info = goodsMapper.selectBySkuid(skuId,counterNo);
		info.setImages(goodsQueryMapper.findGoodsImages(info.getId()));
		return info;
	}
	/**
	 * 
	 * @param 
	 * @param 
	 * @return
	 */
//	@Override
//	public BigDecimal countCarryPrice(CarryPriceInfo info){
//		List<TemplateSkuInfo> templates = deliveryTemplateQueryMapper.selectBySkuid(info);
//		
//		BigDecimal bigFirst = new BigDecimal("0");
//		CarryWayInfo carryway = info.getCityId()!=null?getregion(info.getCityId(),templates):null;
//		logger.debug("carryway="+carryway);
//		if(carryway==null) {
//			//找出最大首费
//			for(TemplateSkuInfo tem:templates) {
//				List<CarryWayInfo> carrys = tem.getCarryWays();
//				for(CarryWayInfo carry:carrys) {
//					if(bigFirst.compareTo((carry.getFirstAmount())) == -1 && carry.getIsDefault() == 1) {
//						bigFirst = carry.getFirstAmount();
//						carryway = carry;
//					}
//				}
//			}
//		}else{
//			bigFirst = carryway.getFirstAmount();
//		}
//		logger.debug("carryway1="+carryway);
//		//计算各个商品的增费
//		BigDecimal secondTotal = new BigDecimal("0");
//		if(carryway!=null) {
//			List<GoodsOrderInfo> goods = info.getGoods();
//			DeliveryTemplate tem = deliveryTemplateMapper.selectByPrimaryKey(carryway.getTemplateId());
//			for(GoodsOrderInfo g:goods) {
//				BigDecimal secondPrice = new BigDecimal("0");
//				BigDecimal num = new BigDecimal(g.getNum());//商品数量
//				switch (tem.getPricingMethod()) {//计价方式
//				case 1://按件数
//					if(num.compareTo(new BigDecimal(carryway.getFirstPiece())) == 1 ) {
//						Double duo = Math.ceil((num.subtract(new BigDecimal(carryway.getFirstPiece())).doubleValue() )/carryway.getSecondPiece());
//						secondPrice = carryway.getSecondAmount().multiply(new BigDecimal(duo));
//						g.setPrice(secondPrice);
//					}else{
//						g.setPrice(secondPrice);
//					}
//					break;
//				case 2://按重量
//					if(num.compareTo(carryway.getFirstWeight()) == 1 ) {
//						Double duo = Math.ceil((num.subtract(carryway.getFirstWeight()).doubleValue())/carryway.getSecondPiece());
//						secondPrice = carryway.getSecondAmount().multiply(new BigDecimal(duo));
//						g.setPrice(secondPrice);
//					}else{
//						g.setPrice(secondPrice);
//					}
//					break;
//				}
//				secondTotal = secondTotal.add(secondPrice);
//			}
//		}
//		return bigFirst.add(secondTotal);
//	}
	/**
	 * 判断用户地址是否有所属配送
	 * @param cityId
	 * @param templates
	 * @return
	 */
//	public CarryWayInfo getregion(Integer cityId,List<TemplateSkuInfo> templates) {
//		CarryWayInfo info = null;
//		Boolean flag = true;
//		for(TemplateSkuInfo t:templates) {
//			if(flag) {
//				for(CarryWayInfo c:t.getCarryWays()) {
//					if(c.getRegion().indexOf(cityId.toString())!=-1){  
//						info = c;
//						flag = false;
//						break;
//					}
//				} 
//			}
//		}
//		return info;
//	}

	@Override
	@Transactional
	public Response<String> setRecommend(Integer goodsId, Integer isRecommend) {
		Goods record = goodsMapper.selectByPrimaryKey(goodsId);
		if(record==null) {
			logger.error("根据goodsId查询商品信息为空,goodsId={}",goodsId);
			return Response.error("推荐失败");
		}
		record.setIsRecommend(isRecommend);
		int upcount = goodsMapper.updateByPrimaryKeySelective(record);
		if(upcount!=1) {
			logger.error("更新商品的推荐状态失败，更新条目不正确,upcount={}",upcount);
			throw new RuntimeException("商品推荐失败，更新条目不正确");
		}
		return Response.success("成功");
	}

	@Override
	public List<TypeInfo> getGoods(GoodsQueryInfo info) {
		PageHelper.startPage(info.getPage(), info.getLimit());
		return goodsQueryMapper.getOnlineGoods(info);
	}

	@Override
	public List<GoodsListInfo> getRecommentGoods(Integer page,Integer limit) {
		int startpage = (page-1)*limit;
		return goodsQueryMapper.getRecommentGoods(startpage,limit);
	}

	@Override
	public List<TypeInfo> getAllSystem() {
		return goodsQueryMapper.getAllSystemId();
	}

	@Override
	public Page<GoodsSearchInfo> goodsSearchByQuery(GoodsSearchQueryInfo info) {
		PageHelper.startPage(info.getPage(), Page.PAGESIZE);
		List<GoodsSearchInfo> result = goodsQueryMapper.goodsSearchByQuery(info);
		PageInfo<GoodsSearchInfo> pageInfo = new PageInfo<GoodsSearchInfo>(result);
		logger.info("curnum={}",pageInfo.getPageNum());
		logger.info("num={}",pageInfo.getPages());
		Page<GoodsSearchInfo> page = new Page<GoodsSearchInfo>(Page.PAGESIZE, pageInfo.getPageNum(), pageInfo.getPages(), pageInfo.getTotal(), result);
		return page;
	}

	@Override
	public List<GoodsParams> findParamsById(Integer goodsId) {
		GoodsParams record = new GoodsParams();
		record.setGoodsId(goodsId);
		
		return goodsParamMapper.select(record);
	}

	@Override
	public Response<String> editParams(GoodsParamInfo info) {
		List<GoodsParams> addlist = new ArrayList<GoodsParams>();
		List<GoodsParams> editlist = new ArrayList<GoodsParams>();
		Integer goodsId = info.getGoodsId();
		
		for(GoodsParamEditInfo gedit: info.getItems()) {
			if(gedit.getName()!=null && !"".equals(gedit.getName())) {
				GoodsParams gps = new GoodsParams();
				gps.setGoodsId(goodsId);
				gps.setName(gedit.getName());
				gps.setValue(gedit.getValue());
				
				if(gedit.getParamId()==null) {
					gps.setDeleteStatus(0);
					addlist.add(gps);
				}else {
					gps.setParamId(gedit.getParamId());
					editlist.add(gps);
				}
			}
		}
		int num=0;
		if(addlist.size()!=0) {
			num = goodsParamMapper.insertList(addlist);
		}
		if(num != addlist.size()) {
			throw new RuntimeException("保存失败");
		}
		num=0;
		if(editlist.size()!=0) {
			for(GoodsParams record : editlist) {
				num += goodsParamMapper.updateByPrimaryKeySelective(record);
			}
			if(num != editlist.size()) {
				throw new RuntimeException("保存失败");
			}
		}
		
		return new Response<String>(ResultEnum.SUCCESS);
	}

	@Override
	public Response<String> deletParam(GoodsParamInfo info) {
		int num=0;
		for(Integer paramId: info.getIds()) {
			GoodsParams spec = goodsParamMapper.selectByPrimaryKey(paramId);
			num += goodsParamMapper.deleteByPrimaryKey(paramId);
			if(spec == null) {
				logger.error("删除参数失败，根据参数id查询数据为空，id={}",paramId);
				throw new RuntimeException("删除失败");
			}
		}
		if(num != info.getIds().size()) {
			throw new RuntimeException("删除失败");
		}
		return new Response<String>(ResultEnum.SUCCESS);
	}

	@Override
	public Response<String> isPackage(Integer goodsId, Integer type) {
		Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
		if(type == 1) {
			//商品套餐首页展示
			goods.setPackageIndexShow(1);
		}else {
			//商品套餐首页不展示
			goods.setPackageIndexShow(0);
		}
		goodsMapper.updateByPrimaryKeySelective(goods);
		return new Response<String>(ResultEnum.SUCCESS);
	}

	@Override
	public List<StartGoodsInfo> queryStartGoods() {
		
		return goodsQueryMapper.queryStartGoods(new Date());
	}

	@Override
	public  Page<GoodsSearchInfo> goodsList(GoodsSearchQueryInfo info) {
		PageHelper.startPage(info.getPage(), Page.PAGESIZE);
		List<GoodsSearchInfo> result = goodsQueryMapper.goodsSearchByQuery(info);
		PageInfo<GoodsSearchInfo> pageInfo = new PageInfo<GoodsSearchInfo>(result);
		logger.info("curnum={}",pageInfo.getPageNum());
		logger.info("num={}",pageInfo.getPages());
		Page<GoodsSearchInfo> page = new Page<GoodsSearchInfo>(Page.PAGESIZE, pageInfo.getPageNum(), pageInfo.getPages(), pageInfo.getTotal(), result);
		return page;
	}

	@Override
	public List<PackageGoodsInfo> queryShowIndexPackageGoods() {
		
		return goodsQueryMapper.queryPackageGoods(new Date());
	}

	@Override
	public List<TypeInfo> findGoodsParams(Integer goodsId) {
		// TODO Auto-generated method stub
		return goodsQueryMapper.findGoodsParams(goodsId);
	}

	@Override
	public List<GoodsCommentInfo> findGoodsCommentInfo(Integer goodsId) {
		// TODO Auto-generated method stub
		return commentQueryMapper.findGoodComment(goodsId);
	}

	@Override
	public List<GoodsPropertiesInfo> findGoodsProById(Integer goodsId) {
		Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
		List<GoodsPropertiesInfo> goodsPropertiesInfos = new ArrayList<>();
        GoodsSku skuRecord = new GoodsSku();
        skuRecord.setGoodsId(goods.getGoodsId());
        skuRecord.setDeleteStatus(DeleteStatus.USEING);
        skuRecord.setType(GoodsSkuType.NO_DEFAULT);
        List<GoodsSku> goodsSkus = goodsSkuMapper.select(skuRecord);
        if(!CollectionUtils.isEmpty(goodsSkus)) {
            Map<String, Set<String>> prosMap = new HashedMap<>();
            for (GoodsSku g : goodsSkus)
            {
                String specIds = g.getSpecIds();
                String specsStr[] = specIds.split(",");
                for (String str : specsStr)
                {
                    String specs[] = str.split(":");
                    String pro = specs[0];
                    String spec = specs[1];
                    Set<String> specList = prosMap.get(pro);
                    logger.debug("pro:{},spec:{},List:{}", pro, spec, specList);
                    if (CollectionUtils.isEmpty(specList))
                    {
                        Set<String> sps = new HashSet<>();
                        sps.add(spec);
                        prosMap.put(pro, sps);
                    }
                    else
                    {
                        specList.add(spec);
                    }
    
                }
            }
            logger.debug("propsMap:{}", prosMap);
            for (String pro : prosMap.keySet())
            {
                GoodsProperties properties = goodsPropertiesMapper
                        .selectByPrimaryKey(Integer.parseInt(pro));
                GoodsPropertiesInfo goodsPropertiesInfo = new GoodsPropertiesInfo();
                goodsPropertiesInfo.setProp(new TypeInfo(
                        properties.getPropertyId() + "", properties.getName()));
                goodsPropertiesInfos.add(goodsPropertiesInfo);
                Set<String> specs = prosMap.get(pro);
                List<GoodsSpecInfo> specsTypes = new ArrayList<>();
                for (String s : specs)
                {
                    GoodsPropertiesSpec spec = goodsPropertiesSpecMapper
                            .selectByPrimaryKey(Integer.parseInt(s));
                    GoodsSpecInfo goodsspec 
                     =	goodsPropertiesSpecMapper.selectBy(goods.getGoodsId(), Integer.parseInt(s));
                    if(goodsspec == null) {
                    	goodsspec = new GoodsSpecInfo();
                    }
                    goodsspec.setSpecId(spec.getSpecId());
                    goodsspec.setSpecName(spec.getName());
                    specsTypes.add(goodsspec);
                }
                goodsPropertiesInfo.setSpecs(specsTypes);
            }
        }
		return goodsPropertiesInfos;
	}

	@Override
	public List<GoodsQuestionInfo> findGoodsQuestionInfo(Integer goodsId) {
		return goodsQuestionMapper.findGoodsQuestion(goodsId);
	}

	@Override
	public Response<String> addGoodsQuestion(Integer goodsId,String content,Integer userId) {
		Question question = new Question();
		question.setGoodsId(goodsId);
		question.setContent(content);
		question.setCtime(new Date());
		question.setUserId(userId);
		questionMapper.insert(question);
		return new Response<String>(ResultEnum.SUCCESS);
	}

	@Override
	public Response<String> saveanswer(Integer id, String content, Integer userId) {
		Answer ans = new Answer();
		ans.setContent(content);
		ans.setCtime(new Date());
		ans.setUserId(userId);
		ans.setQuestionId(id);
		AnswerMapper.insert(ans);
		return new Response<String>(ResultEnum.SUCCESS);
	}

	@Override
	@Transactional
	public Response<String> saveGoodsPros(GoodsPropertyConfigEditInfo info) {
		GoodsPropertiesSpecImg record = new GoodsPropertiesSpecImg();
		record.setGoodsId(info.getGoodsId());
		goodsPropertiesSpecImgMapper.delete(record);
		List<GoodsPropertiesSpecImg> newSpecImg = new ArrayList<>();
		for(GoodsSpecInfo spec:info.getSpecs() ) {
			GoodsPropertiesSpecImg specImg = new GoodsPropertiesSpecImg();
			specImg.setGoodsId(info.getGoodsId());
			specImg.setSpecId(spec.getSpecId());
			specImg.setAccessoryId(spec.getAccessoryId());
			newSpecImg.add(specImg);
		}
		int count = goodsPropertiesSpecImgMapper.insertList(newSpecImg);
		if(count!=info.getSpecs().size()) {
			logger.error("商品属性设置保存失败，goodsId={}",info.getGoodsId());
			throw new RuntimeException("商品属性设置保存失败");
		}
		return Response.success("成功");
	}

	
	
}
