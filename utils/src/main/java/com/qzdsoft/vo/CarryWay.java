/**
 * 
 */
package com.qzdsoft.vo;

/**
 * simple introduction
 *运送方式
 * <p>detailed comment
 * @author pc-20170422 2017年12月19日
 * @see
 * @since 1.0
 */
public interface CarryWay {
	
	static final String POSTAGE_CODE="0";
	static final String EXPRESS_CODE="1";
	static final String EMS_CODE="2";
	
	static final TypeInfo POSTAGE = new TypeInfo(POSTAGE_CODE,"平邮");
	static final TypeInfo EXPRESS = new TypeInfo(EXPRESS_CODE,"快递");
	static final TypeInfo EMS = new TypeInfo(EMS_CODE,"EMS");
}
