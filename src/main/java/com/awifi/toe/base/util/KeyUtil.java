package com.awifi.toe.base.util;

import java.util.UUID;

/**   
 * @Description:  生成唯一标识工具类
 * @Title: KeyUtil.java 
 * @Package com.awifi.toe.base.util 
 * @author 许小满 
 * @date 2015年12月15日 下午3:30:50
 * @version V1.0   
 */
public class KeyUtil {

    /** 前缀 */
    private static final String PREFIX = "_";
    
    /**
     * 生成唯一标识
     * @return 唯一标识
     * @author 许小满  
     * @date 2015年12月15日 下午3:35:04
     */
    public static String generateKey(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return PREFIX + uuid;
    }
    
    /**
     * 测试方法
     * @param args 参数
     * @author 许小满  
     * @date 2015年12月15日 下午3:33:17
     */
    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        for(int i=0; i< 10000; i++){
            System.out.println(KeyUtil.generateKey());
            //System.out.println(KeyUtil.generateKey().length());
        }
        System.out.println("共花费了 " + (System.currentTimeMillis() - beginTime) + " ms.");
        
    }
    
}
