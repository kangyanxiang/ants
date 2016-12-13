package com.awifi.toe.system.mapper.sql;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**   
 * @Description:  
 * @Title: SysConfigSql.java 
 * @Package com.awifi.toe.system.mapper.sql
 * @author ZhouYing 
 * @date 2016年7月21日 上午10:01:09
 * @version V1.0   
 */
public class SysConfigSql {

    /**
     * 系统参数配置总条数sql
     * @param params 参数
     * @return sql
     * @author ZhouYing 
     * @date 2016年7月21日 上午10:02:09
     */
    public String pageCount(Map<String, Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append("select count(pk_id) from toe_system_config where 1=1 ");
        String keywords=(String)params.get("keywords");
        if(StringUtils.isNotBlank(keywords)){
            sql.append("and concat(ifnull(alias_name,''),ifnull(param_key,''),ifnull(param_value,''),ifnull(remark,'')) like concat('%',#{keywords},'%') ");
        }
        return sql.toString();
    }
    
    /**
     * 系统参参数配置列表
     * @param params 参数
     * @return sql
     * @author ZhouYing 
     * @date 2016年7月21日 上午9:53:53
     */
    public String pageQuery(Map<String, Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append("select pk_id,alias_name,param_key,param_value,remark,order_no,FROM_UNIXTIME(create_date, '%Y-%m-%d %H:%i:%S' ) as create_date,FROM_UNIXTIME(update_date, '%Y-%m-%d %H:%i:%S' ) as update_date ");
        sql.append("from toe_system_config  where 1=1 ");
        String keywords=(String)params.get("keywords");
        if(StringUtils.isNotBlank(keywords)){
            sql.append("and concat(ifnull(alias_name,''),ifnull(param_key,''),ifnull(param_value,''),ifnull(remark,'')) like concat('%',#{keywords},'%') ");
        }
        sql.append("order by order_no limit #{begin},#{pageSize}");
        return sql.toString();
    }
}