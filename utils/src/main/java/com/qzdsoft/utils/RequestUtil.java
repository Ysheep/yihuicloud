package com.qzdsoft.utils;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Admin
 */
public final class RequestUtil {


    public static Integer toInt(HttpServletRequest request, String key) {
        String s = request.getParameter(key);
        return StringUtils.isNotBlank(s) ? Integer.parseInt(s) : null;
    }

    public static Float toFloat(HttpServletRequest request, String key) {
        String s = request.getParameter(key);
        return StringUtils.isNotBlank(s) ? Float.parseFloat(s) : null;
    }

}
