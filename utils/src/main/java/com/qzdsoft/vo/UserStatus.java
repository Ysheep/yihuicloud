package com.qzdsoft.vo;

import java.util.Arrays;
import java.util.List;

/**
 * 审核状态
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170420 2017年8月30日
 * @see
 * @since 1.0
 */
public interface UserStatus
{
   //状态：0 正常  1冻结
    static final String NORMAL_CODE = "0";
    static final String FREE_CODE = "1";
    
    static final TypeInfo FREE = new TypeInfo(FREE_CODE,"冻结");
    static final TypeInfo NORMAL = new TypeInfo(NORMAL_CODE,"正常");
    
    static final List<TypeInfo> ALL = Arrays.asList(NORMAL,FREE);
}
