package com.awifi.toe.base.util;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Description: Bean 工具类，作用：从 spring上下文 获取bean
 * @Title: BeanUtil.java
 * @Package com.awifi.toe.base.util
 * @author 许小满
 * @date 2015年6月29日 上午10:44:35
 * @version V1.0
 */
public class BeanUtil {

    /** 应用上下文 */
    private static WebApplicationContext webApplicationContext;

    /**
     * 获取bean对象
     * @param name bean名称
     * @return bean对象
     * @author 许小满
     * @date 2015年6月29日 上午10:48:40
     */
    public static Object getBean(String name) {
        return getContext().getBean(name);
    }

    /**
     * 获取 应用上下文
     * @return 应用上下文
     * @author 许小满  
     * @date 2015年12月7日 下午3:08:19
     */
    private static WebApplicationContext getContext() {
        if (webApplicationContext == null) {
            webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        }
        return webApplicationContext;
    }
}