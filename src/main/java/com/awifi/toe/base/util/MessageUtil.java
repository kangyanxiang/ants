package com.awifi.toe.base.util;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @Description: 消息工具类（国际化）
 * @Title: MessageUtil.java
 * @Package com.awifi.toe.base.util
 * @author 许小满
 * @date 2015年6月29日 上午10:43:54
 * @version V1.0
 */
public class MessageUtil {

    /** 消息源 */
    private static ReloadableResourceBundleMessageSource messageSource;

    /**
     * 获取消息
     * 
     * @param key 消息key值
     * @return 消息值
     * @author 许小满
     * @date 2015年6月29日 上午10:49:35
     */
    public static String getMessage(String key) {
        return getMessage(key, null);
    }

    /**
     * 获取消息
     * 
     * @param key 消息key
     * @param arg 参数
     * @return 消息值
     * @author 许小满
     * @date 2015年6月29日 上午10:49:46
     */
    public static String getMessage(String key, Object arg) {
        Object[] args = new Object[] { arg };
        return getMessageSource().getMessage(key, args, key+" 无法匹配.", null);
    }

    /**
     * 获取消息
     * 
     * @param key 消息key
     * @param args 参数
     * @return 消息值
     * @author 许小满
     * @date 2015年6月29日 上午10:49:46
     */
    public static String getMessage(String key, Object[] args) {
        return getMessageSource().getMessage(key, args, key+" 无法匹配.", null);
    }

    /**
     * 获取消息源
     * @return 消息源
     * @author 许小满  
     * @date 2015年12月7日 下午2:30:07
     */
    private static ReloadableResourceBundleMessageSource getMessageSource() {
        if (messageSource == null) {
            messageSource = (ReloadableResourceBundleMessageSource) BeanUtil.getBean("messageSource");
        }
        return messageSource;
    }

}
