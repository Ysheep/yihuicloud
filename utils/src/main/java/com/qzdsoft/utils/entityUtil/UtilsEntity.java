package com.qzdsoft.utils.entityUtil;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 模块名：Entity工具类
 * <p>
 * 详细描述：将两个Bean之间具有相同属性的值传递
 * 
 * @author DamingChen Sep 28, 2017
 * @see
 * @since 1.0
 */
public class UtilsEntity {
	private static final Logger logger = LoggerFactory.getLogger(UtilsEntity.class);

	public static Map<String, String> compare(Object a, Object b) {
		Map<String, String> result = new HashMap<String, String>();
		List<String> fieldNames = getEntityFieldNames(a.getClass());
		for (String fieldName : fieldNames) {
			Object aVal = getFieldValue(a, fieldName);
			Object bVal = getFieldValue(b, fieldName, true);
			if (aVal == null && bVal == null) {
				continue;
			}
			String strAVal = null;
			String strBVal = null;
			if (aVal == null) {
				strAVal = "NULL";
			} else {
				strAVal = aVal.toString();
			}
			if (bVal == null) {
				strBVal = "NULL";
			} else {
				strBVal = bVal.toString();
			}
			if (aVal instanceof Timestamp || aVal instanceof Date) {
				strAVal = formatTestDate((Date) aVal);
			}
			if (bVal instanceof Timestamp || bVal instanceof Date) {
				strBVal = formatTestDate((Date) bVal);
			}
			if (strAVal.equals("NULL") || strAVal.equals(strBVal)) {
				continue;
			}
			result.put(fieldName, strAVal + ":" + strBVal);
		}
		return result;
	}

	private static String formatTestDate(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * 创建Entity实例，将object中与本Entity相同字段名的值转录到本Entity实例中去。
	 * 
	 * @param object
	 */
	public static void copyEntity(Object fromEn, Object toEn) {
		List<String> fromFieldList = getEntityFieldNames(fromEn.getClass());
		List<String> toFieldList = getEntityFieldNames(toEn.getClass());
		for (String fromField : fromFieldList) {
			if (toFieldList.contains(fromField)) {
				Object fieldValue = getFieldValue(fromEn, fromField);
				setFieldValue(toEn, fromField, fieldValue);
			}
		}
	}

	/**
	 * 将object中与本Entity相同字段名的值转录到本Entity中去。
	 * 
	 * @param object
	 */
	public static void copyEntityValues(Object fromEn, Object toEn) {
		List<String> fromFieldList = getEntityFieldNames(fromEn.getClass());
		List<String> toFieldList = getEntityFieldNames(toEn.getClass());
		for (String fromField : fromFieldList) {
			if (toFieldList.indexOf(fromField) != -1) {
				Object fieldValue = getFieldValue(fromEn, fromField);

				setFieldValue(toEn, fromField, fieldValue);
			}
		}
	}

	public static Object copyEntity(Object fromEn, Class<?> toClazz) {
		if (fromEn == null) {
			return null;
		}
		List<String> fromFieldList = getEntityFieldNames(fromEn.getClass());
		List<String> toFieldList = getEntityFieldNames(toClazz);
		Object obj;
		try {
			obj = toClazz.getConstructor((Class<?>[]) new Class[0]).newInstance(new Object[0]);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		for (String fromField : fromFieldList) {
			if (toFieldList.indexOf(fromField) != -1) {
				Object fieldValue = getFieldValue(fromEn, fromField);
				setFieldValue(obj, fromField, fieldValue);
			}
		}
		return obj;
	}

	public static List<String> getEntityFieldNames(Class<?> entity) {
		List<String> returnValue = new ArrayList<String>();
		for (Field field : entity.getDeclaredFields()) {
			int modifiers = field.getModifiers();
			if (modifiers != 9) {
				if (modifiers != 26) {
					returnValue.add(field.getName());
				}
			}
		}
		return returnValue;
	}

	public static <T extends Serializable> T getFieldValue(Object entity, String fieldName) {
		return getFieldValue(entity, fieldName, false);
	}

	public static <T extends Serializable> T getFieldValue(Object entity, String fieldName,
			boolean noSuchFieldReturnNull) {
		if (entity == null) {
			return null;
		}
		try {
			Class<?> clazz = entity.getClass();
			try {
				Field field = clazz.getDeclaredField(fieldName);
				field.setAccessible(true);
				return (T) field.get(entity);
			} catch (NoSuchFieldException ex) {
				try {
					NoSuchFieldException e = ex;
					if (noSuchFieldReturnNull) {
						return null;
					}
					try {
						throw new RuntimeException(e);
					} catch (IllegalArgumentException e2) {
						throw new RuntimeException(e2);
					}
				} catch (IllegalArgumentException ex2) {
				}
			}
		} catch (SecurityException ex3) {
		} catch (IllegalArgumentException ex4) {
		} catch (IllegalAccessException ex5) {
		}
		return null;
	}

	public static void setFieldValue(Object entity, String fieldName, Object value) {
		if (entity == null) {
			return;
		}
		try {
			Class<?> clazz = entity.getClass();
			try {
				Field field = clazz.getDeclaredField(fieldName);
				field.setAccessible(true);
				field.set(entity, value);
			} catch (NoSuchFieldException e) {
				logger.info(entity.toString());
				logger.error("发生在字段名{},值{}:",fieldName,value);
				throw new RuntimeException(e);
			}
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
			logger.error(entity.toString());
			logger.error("发生在字段名{},值{}:",fieldName,value);
			throw new RuntimeException(e);
		}
	}
}
