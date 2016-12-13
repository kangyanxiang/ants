package com.awifi.toe.base.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**   
 * @Description:  类型转化工具类
 * @Title: CastUtil.java 
 * @Package com.awifi.toe.base.util 
 * @author 许小满 
 * @date 2016年8月25日 上午8:13:11
 * @version V1.0   
 */
public class CastUtil {

    /**
     * 将String数组转化为Long数组
     * @param strs String数组
     * @return Long数组
     * @author 许小满  
     * @date 2016年8月25日 上午8:14:18
     */
    public static Long[] toLongArray(String[] strs){
        int maxLength = strs != null ? strs.length : 0;
        if(maxLength <= 0){
            return null;
        }
        String str = null;
        Long[] longArray = new Long[maxLength];
        for(int i=0; i<maxLength; i++){
            str = strs[i];
            if(StringUtils.isBlank(str)){
                continue;
            }
            longArray[i] = Long.parseLong(str);
        }
        return longArray;
    }
    
    /**
     * 将集合转化为字符串
     * @param list 集合
     * @return 字符串
     * @author 许小满  
     * @date 2016年8月25日 上午8:18:36
     */
    public static String toString(List<String> list){
        int maxSize = list != null ? list.size() : 0;
        if(maxSize <= 0){
            return StringUtils.EMPTY;
        }
        StringBuilder strs = new StringBuilder();
        String str = null;
        for(int i=0; i<maxSize; i++){
            str = list.get(i);
            if(StringUtils.isBlank(str)){
                continue;
            }
            strs.append(str);
            if(i < (maxSize -1)){
                strs.append(",");
            }
        }
        return strs.toString();
    }
    
}