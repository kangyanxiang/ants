package com.awifi.toe.log.mapper.sql;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**   
 * @Description:  
 * @Title: ExceptionLogSql.java 
 * @Package com.awifi.toe.log.mapper.sql 
 * @author 苏晨斌 
 * @date 2016年5月4日 下午7:10:19
 * @version V1.0   
 */
public class ExceptionLogSql {
    
    /**
     * 获得记录总数
     * @param params 参数
     * @return 总数
     * @author 苏晨斌  
     * @date 2016年4月28日 下午2:50:35
     */
    public String pageCount(Map<String, Object> params) {
        StringBuffer sql = new StringBuffer(150);
        sql.append("select count(pk_id) from toe_exception_log where ");
        this.pageCondition(sql, params);//分页查询条件
        return sql.toString();
    }
    
    /**
     * 获得记录集合
     * @param params 参数
     * @return 记录
     * @author 苏晨斌  
     * @date 2016年4月28日 下午3:05:53
     */
    public String pageQuery(Map<String, Object> params){
        StringBuffer sql = new StringBuffer(270);
        sql.append("select pk_id,module_name,service_name,from_unixtime(create_date,'%Y-%m-%d %H:%i:%S') as create_date from toe_exception_log where ");
        this.pageCondition(sql, params);//分页查询条件
        sql.append("order by pk_id desc limit #{begin},#{pageSize}");
        return sql.toString();
    }
    
    /**
     * 分页查询条件
     * @param sql sql
     * @param params 参数
     * @author 许小满  
     * @date 2016年10月11日 上午9:31:56
     */
    private void pageCondition(StringBuffer sql, Map<String, Object> params){
        String startDate = (String)params.get("startDate");//开始时间
        if(StringUtils.isNotBlank(startDate)){
            sql.append("create_date>=unix_timestamp(#{startDate}) ");
        }
        String endDate = (String)params.get("endDate");//结束时间
        if(StringUtils.isNotBlank(endDate)){
            sql.append("and create_date<=unix_timestamp(#{endDate}) ");
        }
        String moduleName = (String)params.get("moduleName");//模块名称
        if(StringUtils.isNotBlank(moduleName)){
            sql.append("and module_name like concat('%',#{moduleName},'%') ");
        }
        String serviceName = (String)params.get("serviceName");//service 名称
        if(StringUtils.isNotBlank(serviceName)){
            sql.append("and service_name like concat('%',#{serviceName},'%') ");
        }
        String errorMessage = (String)params.get("errorMessage");//异常信息
        if(StringUtils.isNotBlank(errorMessage)){
            sql.append("and error_message like concat('%',#{errorMessage},'%') ");
        }
        String interfaceUrl = (String)params.get("interfaceUrl");//接口地址
        if(StringUtils.isNotBlank(interfaceUrl)){
            sql.append("and interface_url like concat('%',#{interfaceUrl},'%') ");
        }
        String interfaceParam = (String)params.get("interfaceParam");//接口参数
        if(StringUtils.isNotBlank(interfaceParam)){
            sql.append("and interface_param like concat('%',#{interfaceParam},'%') ");
        }
        String interfaceReturnValue = (String)params.get("interfaceReturnValue");//接口返回值
        if(StringUtils.isNotBlank(interfaceReturnValue)){
            sql.append("and interface_return_value like concat('%',#{interfaceReturnValue},'%') ");
        }
    }

}