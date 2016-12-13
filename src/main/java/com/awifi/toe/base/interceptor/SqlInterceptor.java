package com.awifi.toe.base.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.awifi.toe.base.util.IOUtil;

/**
 * 防止sql注入
 * @author kangyanxiang
 * 2015年12月3日 下午4:37:37
 */
public class SqlInterceptor extends HandlerInterceptorAdapter {
    
    /** 日志 */
    //private final Log logger = LogFactory.getLog(SqlInterceptor.class);
    
    @SuppressWarnings("rawtypes")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获得所有请求参数名
        Enumeration params = request.getParameterNames();
        String paramName = null;//参数名
        String paramValue = null;//参数值
        StringBuffer values = new StringBuffer();
        while (params.hasMoreElements()) {
            //得到参数名
            paramName = params.nextElement().toString();
            //System.out.println("name===========================" + name + "--");
            //得到参数对应值
            String[] paramValues = request.getParameterValues(paramName);
            int valueLength = paramValues != null ? paramValues.length : 0;
            for (int i = 0; i < valueLength; i++) {
                paramValue = paramValues[i];
                //System.out.println("提示：" + paramName + " ： paramValue["+i+"]= " + paramValue);
                if(StringUtils.isBlank(paramValue)){
                    continue;
                }
                //if(paramValue.length() > 2000){
                    //IOUtil.responseError(response, "您发送请求中的参数长度过长");
                    //return false;
                //}
                values.append(paramValue);
            }
            //System.out.println("--------------------------------------------------------------------------");
        }
        if (sqlValidate(values.toString())) {
            IOUtil.responseError(response, "您发送请求中的参数中含有非法字符");
            return false;
        } 
        return true;
    }
    
    /**
     * 校验参数
     * @param str 用户输入的字符串
     * @return true 存在非法字符、false 符合要求
     * @author 许小满  
     * @date 2015年12月3日 下午4:50:38
     */
    private static boolean sqlValidate(String str) {
        str = str.toLowerCase();//统一转为小写
//        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
//                "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
//                "table|from|grant|use|group_concat|column_name|" +
//                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
//                "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";
        //过滤掉的sql关键字，可以手动添加
        String[] badStrs = {"'"};
        int maxLength = badStrs.length;
        for (int i = 0; i < maxLength; i++) {
            if (str.indexOf(badStrs[i]) >= 0) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)throws Exception {
    }

}
