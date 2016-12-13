package com.awifi.toe.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.awifi.toe.base.util.EncryUtil;
import com.awifi.toe.base.util.IOUtil;
import com.awifi.toe.system.util.SysConfigUtil;

/**
 * @Description: api 接口拦截器
 * @Title: ApiInterceptor.java
 * @Package com.awifi.toe.base.interceptor
 * @author kangyanxiang
 * @date 2015年11月30日 下午12:33:48
 * @version V1.0
 */
public class ApiInterceptor extends HandlerInterceptorAdapter {
    
    /** 日志 */
    //private final Log logger = LogFactory.getLog(ApiInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String timestamp = request.getParameter("timestamp");//时间戳（秒）
        if(StringUtils.isBlank(timestamp)){
            IOUtil.responseError(response, "timestamp 不能为空");
            return false;
        }
        if(EncryUtil.isTimeout(timestamp)){
            IOUtil.responseError(response, "token 超时");
            return false;
        }
        String token = request.getParameter("token");
        if(StringUtils.isBlank(token)){
            IOUtil.responseError(response, "token 不能为空");
            return false;
        }
        String secretKey = SysConfigUtil.getParamValue("toe.admin.api.secret.key");//api接口秘钥
        if(StringUtils.isBlank(secretKey)){
            IOUtil.responseError(response, "秘钥 不能为空");
            return false;
        }
        if(!token.equals(EncryUtil.generateToken(secretKey, timestamp))){
            IOUtil.responseError(response, "token 无效");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)throws Exception {
    }

}
