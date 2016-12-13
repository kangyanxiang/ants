package com.awifi.toe.base.util;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JsonUtil {
    
    /** */
    private static final Gson gson = new Gson();
    
    /**
     * 将对象 转为 json字符串
     * @param obj 对象
     * @return json字符串
     * @author 许小满  
     * @date 2015年7月7日 上午9:45:02
     */
    public static String toJson(Object obj){
    	return gson.toJson(obj);
        //return JSON.toJSONString(obj);
    }
    
    /**
     * 将json字符串 转为 对象
     * @param json json
     * @param typeOfT 类型
     * @param <T> type
     * @return <T> specified type of object
     * @throws JsonSyntaxException 异常
     * @author 许小满  
     * @date 2015年7月7日 上午9:47:00
     */
    public static <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException{
    	return gson.fromJson(json, typeOfT);
    }
    
    /**
     * list 转成String并“，”分割
     * @param list  
     * @return String
     * @author kangyanxiang 
     * @date Oct 27, 2016 2:34:07 PM
     */
    public static String listToString(List<String> list){
        StringBuilder sb = new StringBuilder();  
        if (list != null && list.size() > 0) {  
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {  
                    sb.append(list.get(i) + ",");  
                } else {  
                    sb.append(list.get(i));  
                }  
            }  
        }  
        return sb.toString();
    }
}