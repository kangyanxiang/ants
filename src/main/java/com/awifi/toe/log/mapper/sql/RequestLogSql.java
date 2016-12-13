package com.awifi.toe.log.mapper.sql;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**   
 * @Description:  
 * @Title: RequestLogSql.java 
 * @Package com.awifi.toe.log.mapper.sql 
 * @author 苏晨斌 
 * @date 2016年5月4日 下午7:14:45
 * @version V1.0   
 */
public class RequestLogSql {
    
    /**
     * 获取记录总数（请求日志）
     * @param params 参数
     * @return 总数
     * @author 苏晨斌  
     * @date 2016年4月21日 下午3:09:53
     */
    public String pageCount(Map<String, Object> params) {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(pk_id) from toe_request_log where 1=1 ");
        String keywords = (String)params.get("keywords");//关键字
        String startDate = (String)params.get("startDate");//开始时间
        String endDate = (String)params.get("endDate");//结束时间
        if(StringUtils.isNotBlank(keywords)){
            sql.append("and concat(ifnull(request_url, ''),ifnull(request_param, ''),ifnull(request_return_value, '')) like concat('%', ifnull(#{keywords}, ''), '%') ");
        }
        if(StringUtils.isNotBlank(startDate)){
            sql.append("and from_unixtime(create_date, '%Y-%m-%d %H:%i:%S') >= #{startDate} ");
        }
        if(StringUtils.isNotBlank(endDate)){
            sql.append("and from_unixtime(create_date, '%Y-%m-%d %H:%i:%S') <= #{endDate}");
        }
        return sql.toString();
    }
    
    /**
     * 获得记录集合（请求日志）
     * @param params 参数
     * @return 记录
     * @author 苏晨斌  
     * @date 2016年4月21日 下午3:24:43
     */
    public String pageQuery(Map<String, Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append("select pk_id,module_name,request_url,from_unixtime(create_date, '%Y-%m-%d %H:%i:%S') as create_date from toe_request_log where 1=1 ");
        String keywords = (String)params.get("keywords");//关键字
        String startDate = (String)params.get("startDate");//开始时间
        String endDate = (String)params.get("endDate");//结束时间
        if(StringUtils.isNotBlank(keywords)){
            sql.append("and concat(ifnull(request_url, ''),ifnull(request_param, ''),ifnull(request_return_value, '')) like concat('%', ifnull(#{keywords}, ''), '%') ");
        }
        if(StringUtils.isNotBlank(startDate)){
            sql.append("and from_unixtime(create_date, '%Y-%m-%d %H:%i:%S') >= #{startDate} ");
        }
        if(StringUtils.isNotBlank(endDate)){
            sql.append("and from_unixtime(create_date, '%Y-%m-%d %H:%i:%S') <= #{endDate} ");
        }
        sql.append("order by create_date desc limit #{begin},#{pageSize}");
        return sql.toString();
    }

}
