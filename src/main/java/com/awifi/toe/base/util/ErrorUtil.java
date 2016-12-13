package com.awifi.toe.base.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * @Description: 错误消息处理工具类
 * @Title: ErrorUtil.java
 * @Package com.awifi.toe.base.util
 * @author 许小满
 * @date 2015年6月29日 上午10:50:35
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
public class ErrorUtil {

    /**
     * 获取 错误消息
     * 
     * @param errors
     *            错误消息集合
     * @return 消息内容
     * @author 许小满
     * @date 2015年6月29日 上午10:57:59
     */
    public static String getErrorMessage(Errors errors) {
        List<ObjectError> objectErrorList = errors.getAllErrors();
        return getErrorMessage(objectErrorList);
    }

    /**
     * 获取 错误消息
     * 
     * @param result result
     * @return 消息内容
     * @author 许小满
     * @date 2015年6月29日 上午10:57:59
     */
    public static String getErrorMessage(BindingResult result) {
        List<ObjectError> objectErrorList = result.getAllErrors();
        return getErrorMessage(objectErrorList);
    }

    /**
     * 错误消息
     * @param objectErrorList objectErrorList
     * @return 消息内容
     * @author 许小满
     * @date 2015年6月29日 上午11:54:59
     */
    private static String getErrorMessage(List<ObjectError> objectErrorList) {
        int maxSize = objectErrorList != null ? objectErrorList.size() : 0;
        if (maxSize <= 0) {
            return StringUtils.EMPTY;
        }
        ObjectError objectError = null;
        StringBuffer errorMessage = new StringBuffer();
        for (int i = 0; i < maxSize; i++) {
            objectError = objectErrorList.get(i);
            errorMessage.append("[").append((i + 1)).append("].").append(objectError.getDefaultMessage());
            if (i < maxSize - 1) {
                errorMessage.append("   ");
            }
        }
        return errorMessage.toString();
    }

    /**
     * 打印异常日志
     * 
     * @param e 异常
     * @param logger 日志
     * @author 许小满
     * @date 2015年7月13日 下午3:23:31
     */
    public static void printException(Exception e, Log logger) {
        logger.error(getExceptionStackTrace(e));
    }
    
    /**
     * 打印异常日志
     * @param e 异常
     * @param logger 日志
     * @author 许小满  
     * @date 2016年3月23日 上午8:38:36
     */
    public static void printException(Exception e, org.slf4j.Logger logger) {
        logger.error(getExceptionStackTrace(e));
    }
    
    /**
     * 获取异常日志
     * @param e 日志
     * @return 异常消息
     */
    public static String getExceptionStackTrace(Exception e) {
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer, true));
        return writer.toString();
    }
    
    /**
     * 返回异常信息
     * @param errorMessage 异常信息
     * @return json map
     * @author 许小满  
     * @date 2016年9月29日 上午10:41:29
     */
    public static Map responseErrorMessage(String errorMessage){
        Map<String,String> resultMap = new HashMap<String,String>();
        resultMap.put("result", "FAIL");
        resultMap.put("message", errorMessage);
        return resultMap;
    }
}