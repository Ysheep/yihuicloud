package com.qzdsoft.vo;

import java.util.Arrays;
import java.util.List;

/**
 * 发货时间
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年12月19日
 * @see
 * @since 1.0
 */
public interface DeliveryTime {
	  
      static final String  HALF_DAY="12";
      static final String  ONE_DAY="24";
      static final String  TWO_DAY="48";
      static final String  THREE_DAY="72";
      static final String  FIVE_DAY="120";
      static final String  ONE_WEEK="120";
      
      static final TypeInfo HALF = new TypeInfo(HALF_DAY,"12小时内");
      static final TypeInfo ONE = new TypeInfo(ONE_DAY,"24小时内");
      static final TypeInfo TWO = new TypeInfo(TWO_DAY,"48小时内");
      static final TypeInfo THREE = new TypeInfo(THREE_DAY,"72小时内");
      static final TypeInfo FIVE = new TypeInfo(THREE_DAY,"5天内");
      static final TypeInfo WEEK = new TypeInfo(ONE_WEEK,"7天内");
      
      static final List<TypeInfo> ALL = Arrays.asList(HALF,ONE,TWO,THREE,FIVE,WEEK);
}
