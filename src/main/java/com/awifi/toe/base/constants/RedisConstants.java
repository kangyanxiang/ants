package com.awifi.toe.base.constants;
/**   
 * @Description:  redis 常量
 * @Title: RedisConstants.java 
 * @Package com.awifi.toe.base.constants 
 * @author kangyanxiang
 * @date 2016年3月21日 上午11:35:56
 * @version V1.0   
 */
public class RedisConstants {

    /** 平台_系统_模块_ */
    public static final String SYSTEM_CONFIG = "TOE_ADMIN_SYSTEM_CONFIG_";
    
    /** 参数配置有效时间 */
    public static final int SYSTEM_CONFIG_TIME = 86400;//24*60*60 (24小时)
    
    /** 客户 */
    public static final String CUSTOMER = "TOE_CUSTOMER_";
    
    /** 客户 有效时间 */
    public static final int CUSTOMER_TIME = 21600;//6*60*60 (6小时)
    
    /**IVR语音认证*/
    public static final String TOE_IVR = "TOE_IVR_";
    
    /** IVR语音认证存储页面参数 有效时间  */
    public static final int TOE_IVR_TIME = 600;//10分钟有效
    
    /**设备*/
    public static final String DEVICE = "TOE_DEVICE_";
    
    /**设备详情缓存有效时间*/
    public static final int DEVICE_TIME = 21600;//6*60*60(6小时)
    
    /** 站点名称  */
    public static final String SITE_NAME = "TOE_SITE_NAME_";
    
    /** 站点名称  有效时间 */
    public static final int SITE_NAME_TIME = 21600;//6*60*60(6小时)
}
