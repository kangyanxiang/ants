package com.awifi.toe.base.util;

import com.awifi.toe.base.service.RedisService;

/**   
 * @Description:  
 * @Title: RedisUtil.java 
 * @Package com.awifi.toe.system.util 
 * @author 亢燕翔 
 * @date 2016年3月21日 下午2:47:15
 * @version V1.0   
 */
public class RedisUtil {

    /** redis Bean  */
    private static RedisService redisService;
    
    /**
     * @return bean
     * @author 亢燕翔 
     * @date 2016年3月23日 上午11:28:10
     */
    public static RedisService getRedisService() {
        if (redisService == null) {
            redisService = (RedisService) BeanUtil.getBean("redisService");
        }
        return redisService;
    }
    
    /**
     * set 操作
     * @param key 键
     * @param value 值
     * @return value
     * @author 亢燕翔 
     * @date 2016年3月16日 下午5:27:09
    public static String set(String key,String value){
        return getRedisService().set(key, value);
    }
    */
    
    /**
     * 设置值以及生存时间
     * @param key 键
     * @param value 值
     * @param seconds 单位（秒）
     * @return value
     * @author 亢燕翔 
     * @date 2016年3月16日 下午5:27:09
//     */
//    public static String set(String key, String value, Integer seconds){
//        return getRedisService().set(key, value, seconds);
//    }
//    
//    /**
//     * get 操作
//     * @param key 键
//     * @return value
//     * @author 亢燕翔 
//     * @date 2016年3月16日 下午5:29:53
//     */
//    public static String get(String key){
//        return getRedisService().get(key);
//    }
//    
//    /**
//     * Hash键值设置操作
//     * @param key 键
//     * @param map 存储MAP
//     * @param seconds 单位（秒）
//     * @return 结果
//     * @author 许小满  
//     * @date 2016年6月16日 下午3:45:28
//     */
//    public static String hmset(String key,Map<String, String> map, Integer seconds){
//        return getRedisService().hmset(key, map, seconds);
//    }
//    
//    /**
//     * Hash键值 字段 get操作
//     * @param key 键
//     * @param fields 字段(可多个)
//     * @return 结果
//     * @author 许小满  
//     * @date 2016年6月16日 下午3:45:28
//     */
//    public static List<String> hmget(String key,String... fields){
//        return getRedisService().hmget(key, fields);
//    }
//    
//    /**
//     * 删除 操作
//     * @param key 键
//     * @return value
//     * @author 亢燕翔 
//     * @date 2016年3月16日 下午5:31:08
//     */
//    public static Long del(String key){
//        return getRedisService().del(key);
//    }
    
    /**
     * 单独设置时间
     * @param key 键
     * @param seconds 单位（秒）
     * @return value
     * @author 亢燕翔 
     * @date 2016年3月16日 下午5:27:09
    public static Long setSeconds(String key, Integer seconds){
        return getRedisService().setSeconds(key, seconds);
    }
    */
    
}