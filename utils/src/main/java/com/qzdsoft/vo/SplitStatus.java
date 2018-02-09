/**
 * 
 */
package com.qzdsoft.vo;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170420 2017年11月27日
 * @see
 * @since 1.0
 */
public interface SplitStatus
{
    String NOT_SPLIT_CODE = "0";
    String HAS_SPLIT_CODE = "1";
    TypeInfo NOT_SPLIT = new TypeInfo(NOT_SPLIT_CODE,"未分润");
    TypeInfo HAS_SPLIT = new TypeInfo(HAS_SPLIT_CODE,"已分润");
}
