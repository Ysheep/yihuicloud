/**
 * 
 */
package com.qzdsoft.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * simple introduction
 *计价方式
 * <p>detailed comment
 * @author pc-20170422 2017年12月19日
 * @see
 * @since 1.0
 */
public interface PricingMethod {
	//计价方式(1:按件 2:按重量 3:按体积)
	static final String PIECE_CODE = "1";
	static final String WEIGHT_CODE = "2";
	static final String VOLUME_CODE = "3";
	
	static final TypeInfo PIECE = new TypeInfo(PIECE_CODE,"按件数");
	static final TypeInfo WEIGHT = new TypeInfo(WEIGHT_CODE,"按重量");
	
	 static final List<TypeInfo> ALL = Arrays.asList(PIECE,WEIGHT);
}
