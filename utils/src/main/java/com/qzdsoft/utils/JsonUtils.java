package com.qzdsoft.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Create By:DamingChen
 * @version $id:JsonUtils.java,v 0.1 Aug 4, 2017
 * 描述：erpcloud自定义响应结构
 */
public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    
    
    /**
     * 将pojo转换成Map<String,String>
     * @param jsonString
     * @return
     */
	@SuppressWarnings("unchecked")
	public static Map<String,String> pojoToMap(Object data) {
		try {
			String string = MAPPER.writeValueAsString(data);
			return new ObjectMapper().readValue(string, Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return null;
	}
    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 将json结果集转化为对象
     * 
     * @param jsonData json数据
     * @param clazz 对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
	/**
	 * 解析jsonString字符串为map
	 *
	 * @param jsonString
	 * @return
	 */
	public static Map<String,Object> jsonToMap(String jsonString) {
		if (null == jsonString) {
			return null;
		}

		try {
			return new ObjectMapper().readValue(jsonString, Map.class);
		} catch (JsonParseException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
		return null;
	}
    /**
     * json字符串添加值 清除value为null的key
     * @param jsonData
     * @param map
     * @return
     */
	public static String jsonStringAdd(String jsonData, Map<String, Object> map) {
		if(!StringUtil.isEmpty(jsonData) && !map.isEmpty()){
			@SuppressWarnings("unchecked")
			Map<String, String> oldMap = jsonToPojo(jsonData, Map.class);
			Map<String, String> delKey = new HashMap<String,String>(); 
			
			//清除value为null的key
			for(String oldkey:oldMap.keySet()){
				if(StringUtil.isEmpty(String.valueOf(oldMap.get(oldkey)))){
					delKey.put(oldkey, oldkey);
				}
			}
			for(String dkey:delKey.keySet()){
				oldMap.remove(dkey);
			}
			
			//添加map值 
			for (String key : map.keySet()) {  
				oldMap.put(key, String.valueOf(map.get(key)));
			}
			return objectToJson(oldMap);
		}
		return jsonData;
	}
    
}
