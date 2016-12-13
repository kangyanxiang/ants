package com.awifi.toe.base.util;
/**   
 * @Description:  sql工具类
 * @Title: SqlUtil.java 
 * @Package com.awifi.toe.base.util 
 * @author 许小满 
 * @date 2016年9月30日 下午5:24:02
 * @version V1.0   
 */
public class SqlUtil {

    /**
     * in 查询 sql拼接
     * @param columnName 数据库对应的字段名称
     * @param paramName 参数名
     * @param columnValueArray 参数值数据
     * @param sql sql
     * @author 许小满  
     * @date 2016年9月30日 下午5:28:19
     */
    public static void in(String columnName, String paramName, Long[] columnValueArray, StringBuffer sql){
        int maxLength = columnValueArray != null ? columnValueArray.length : 0;
        if(maxLength <= 0){
            return;
        }
        sql.append("and ").append(columnName).append(" in(");
        for(int i=0; i<maxLength; i++){
            sql.append("#{").append(paramName).append("[").append(i).append("]}");
            if(i < (maxLength - 1)){
                sql.append(",");
            }
        }
        sql.append(") ");
    }
    
}
