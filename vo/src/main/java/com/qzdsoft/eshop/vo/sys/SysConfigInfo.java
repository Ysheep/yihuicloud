package com.qzdsoft.eshop.vo.sys;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

/**
 * 配置信息获取类
 *
 * <p>detailed comment
 * @author pc-20170420 2017年10月21日
 * @see
 * @since 1.0
 */
public class SysConfigInfo
{
    private static Map<String,String> configs = new HashedMap<String,String>();
    
    /**
     * 通过该方法获取所有配置类
     * @param key
     * @return
     */
    public static String getValue(String key) {
        return configs.get(key);
    }


    public static void setConfigs(Map<String, String> configs)
    {
        SysConfigInfo.configs = configs;
    }

}
