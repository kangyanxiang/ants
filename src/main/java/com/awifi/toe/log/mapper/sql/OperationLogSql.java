package com.awifi.toe.log.mapper.sql;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**   
 * @Description:  
 * @Title: OperationLogSql.java 
 * @Package com.awifi.toe.log.mapper.sql 
 * @author ZhouYing 
 * @date 2016年7月7日 下午3:49:43
 * @version V1.0   
 */
public class OperationLogSql {
   
    /**
     * 操作日志总数
     * @param params 参数
     * @return sql
     * @author ZhouYing 
     * @date 2016年7月7日 下午4:21:04
     */
    public String pageCount(Map<String, Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append("select count(pk_id) from toe_operation_log where ");
        sql.append("create_date>=unix_timestamp(#{startDate}) and create_date<=unix_timestamp(#{endDate}) ");
        String keywords = (String)params.get("keywords");//模块
        if(StringUtils.isNotBlank(keywords)){
            sql.append("and module_name like concat('%',#{keywords},'%') ");
        }
        String userName = (String)params.get("userName");//账号
        if(StringUtils.isNotBlank(userName)){
            sql.append("and user_name like concat('%',#{userName},'%') ");
        }
        return sql.toString();
    }
    
    /**
     * 操作日志列表
     * @param params 参数
     * @return sql
     * @author ZhouYing 
     * @date 2016年7月7日 下午4:30:56
     */
    public String pageQuery(Map<String, Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append("select pk_id,module_name,user_name,content,from_unixtime(create_date,'%Y-%m-%d %H:%i:%S') as create_date from toe_operation_log where ");
        sql.append("create_date>=unix_timestamp(#{startDate}) and create_date<=unix_timestamp(#{endDate}) ");
        String keywords = (String)params.get("keywords");//模块
        if(StringUtils.isNotBlank(keywords)){
            sql.append("and concat(ifnull(module_name,'')) like concat('%',#{keywords},'%') ");
        }
        String userName = (String)params.get("userName");//账号
        if(StringUtils.isNotBlank(userName)){
            sql.append("and concat(ifnull(user_name,'')) like concat('%',#{keywords},'%') ");
        }
        sql.append("order by create_date desc limit #{begin},#{pageSize}");
        return sql.toString();
    }
}