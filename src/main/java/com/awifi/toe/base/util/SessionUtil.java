package com.awifi.toe.base.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.awifi.toe.auth.model.SessionUser;

/**   
 * @Description:  session操作工具类
 * @Title: SessionUtil.java 
 * @Package com.awifi.toe.base.util 
 * @author 许小满 
 * @date 2015年12月13日 下午9:37:21
 * @version V1.0   
 */
public class SessionUtil {

    /** 日志 */
    private static final Log logger = LogFactory.getLog(SessionUtil.class);
    
    /**
     * 获取当前用户id
     * @return 用户id
     * @author 许小满
     * @date 2015年8月12日 上午10:26:14
     */
    public static Long getCurUserId(){
        SessionUser sessionUser = getCurSessionUser();
        if(sessionUser == null){
            return null;
        }
        return sessionUser.getId();
    }
    
    /**
     * 获取当前用户名
     * @return 用户名
     * @author 许小满
     * @date 2015年8月12日 上午10:26:14
     */
    public static String getCurUserName(){
        SessionUser sessionUser = getCurSessionUser();
        if(sessionUser == null){
            return StringUtils.EMPTY;
        }
        return sessionUser.getUsername();
    }
    
    /**
     * 获取当前用户名
     * @return 用户名
     * @author 许小满
     * @date 2015年8月12日 上午10:26:14
     */
    public static SessionUser getCurSessionUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if(principal == null || ! (principal instanceof UserDetails) ){
            logger.debug("提示：  principal= " + principal);
            return null;
        }
        SessionUser sessionUser = (SessionUser)principal;
        return sessionUser;
    }
    
}
