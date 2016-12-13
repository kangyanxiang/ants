package com.awifi.toe.system.util;

import org.apache.commons.lang3.StringUtils;

import com.awifi.toe.base.util.BeanUtil;
import com.awifi.toe.base.util.EncryUtil;
import com.awifi.toe.system.service.SysConfigService;

/**
 * @Description:
 * @Title: SysConfigUtil.java
 * @Package com.awifi.toe.system.util
 * @author ZhouYing
 * @date 2015年6月30日 下午5:22:39
 * @version V1.0
 */
public class SysConfigUtil {

    /** 系统参数业务层 */
    private static SysConfigService sysConfigService;

    /**
     * 通过参数键获得参数值
     * 
     * @param paramKey 参数key
     * @return 参数value
     * @author ZhouYing
     * @date 2015年6月30日 下午4:57:03
     */
    public static String getParamValue(String paramKey) {
        return getSysConfigService().getParamValue(paramKey);
    }

    /**
     * 通过参数键设置参数值
     * 
     * @param paramKey 参数key
     * @param paramValue 参数value
     * @return
     * @author 许小满
     * @date 2015年7月2日 下午7:43:16
     */
    public static void setParamValue(String paramKey, String paramValue) {
        getSysConfigService().setParamValue(paramKey, paramValue);
    }

    /**
     * 获取商户id (awifi)
     * 
     * @return 商户id
     * @author 许小满
     * @date 2015年7月7日 上午10:01:46
     */
    public static String getAwifiMerchantId() {
        String paramKey = "awifi.merchant.id";
        return getParamValue(paramKey);
    }

    /**
     * 获取系统默认密码(加密后)
     * 
     * @return 默认密码
     * @author 许小满
     * @date 2015年9月2日 上午10:24:46
     */
    public static String getDefaultPassword() {
        String paramKey = "system.default.password";
        String password = getParamValue(paramKey);
        if (StringUtils.isBlank(password)){
            return StringUtils.EMPTY;
        }
        return EncryUtil.getMd5Str(password);
    }

    /**
     * 获取 sysConfigService bean
     * @return sysConfigService bean
     * @author 许小满  
     * @date 2015年11月19日 上午9:26:50
     */
    public static SysConfigService getSysConfigService() {
        if (sysConfigService == null) {
            sysConfigService = (SysConfigService) BeanUtil.getBean("sysConfigService");
        }
        return sysConfigService;
    }

}
