/**
 * 
 */
package com.qzdsoft.vo;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170420 2017年11月24日
 * @see
 * @since 1.0
 */
public interface PayStatus
{
    String NOT_PAY_CODE = "0";
    String HAS_PAY_CODE = "1";
    TypeInfo NOT_PAY = new TypeInfo(NOT_PAY_CODE,"未支付");
    TypeInfo HAS_PAY = new TypeInfo(HAS_PAY_CODE,"已支付");
}
