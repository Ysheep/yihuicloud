package com.qzdsoft.eshop.goodsClassServiceTest;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.qzdsoft.eshop.BaseTest;
import com.qzdsoft.eshop.service.goodsclass.GoodsClassService;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassImage;

public class GoodsClassServiceTest extends BaseTest {
	
	@Autowired
	private GoodsClassService goodsClassService;
	
	@Test
	public void testQueryAllClass(){
		
		List<GoodsClassImage> list = goodsClassService.queryAllClass();
		for(GoodsClassImage i:list) {
			System.out.println(i.getValue());
			for(GoodsClassImage c:i.getChilds()) {
				System.out.println(c.getValue());
			}
		}
		
	}
}
