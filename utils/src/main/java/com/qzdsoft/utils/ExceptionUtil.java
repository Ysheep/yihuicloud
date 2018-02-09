package com.qzdsoft.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 
 * 异常信息获取
 *
 * <p>detailed comment
 * @author pc-20170420 2017年9月1日
 * @see
 * @since 1.0
 */
public class ExceptionUtil
{
    /**
     * 获取异常堆栈信息
     * @param e
     * @return
     */
    public static String getStackTrace(Exception e) {  
        StringWriter writer = new StringWriter();  
        e.printStackTrace(new PrintWriter(writer, true));  
        return writer.toString();  
    }
}
