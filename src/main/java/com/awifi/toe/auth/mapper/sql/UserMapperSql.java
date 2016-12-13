package com.awifi.toe.auth.mapper.sql;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.awifi.toe.base.util.SqlUtil;

/**   
 * @Description:  
 * @Title: UserMapperSql.java 
 * @Package com.awifi.toe.auth.mapper.sql 
 * @author kangyanxiang 
 * @date 2016年6月24日 上午11:31:09
 * @version V1.0   
 */
public class UserMapperSql {

    /**
     * 账号列表总条数sql
     * @param params 参数
     * @return sql
     * @author kangyanxiang 
     * @date 2016年6月24日 下午2:35:40
     */
    public String pageCount(Map<String, Object> params){
        //参数
        StringBuffer sql = new StringBuffer();
        String keywords=(String)params.get("keywords");
        String roleId =(String)params.get("roleId");
        String provinceId=(String)params.get("provinceId");
        String cityId=(String)params.get("cityId");
        String areaId=(String)params.get("areaId");
        String cascadeLabel=(String)params.get("cascadeLabel");
        String cascadeLevel=(String)params.get("cascadeLevel");
        
        //组装sql
        sql.append("select count(pk_id) from toe_user where delete_flag=1 and pk_id !=1 ");
        if(StringUtils.isNotBlank(roleId)){
            sql.append("and user_type=#{roleId} ");
        }
        if(StringUtils.isNotBlank(provinceId)){
            sql.append("and province_id=#{provinceId} ");
        }
        if(StringUtils.isNotBlank(cityId)){
            sql.append("and city_id=#{cityId} ");
        }
        if(StringUtils.isNotBlank(areaId)){
            sql.append("and area_id=#{areaId} ");
        }
        if(StringUtils.isNotBlank(cascadeLabel)){
            sql.append("and cascade_label like concat(#{cascadeLabel},'%') ");
        }
        if(StringUtils.isNotBlank(cascadeLevel)){
            sql.append("and cascade_level>#{cascadeLevel} ");
        }
        if(StringUtils.isNotBlank(keywords)){
            sql.append("and user_name like concat('%',#{keywords},'%')  ");
        }
        return sql.toString();
    }
    
    /**
     * 账号列表
     * @param params 参数
     * @return sql
     * @author kangyanxiang 
     * @date 2016年6月24日 下午2:51:43
     */
    public String pageQuery(Map<String, Object> params){
        //参数
        StringBuffer sql = new StringBuffer();
        String keywords=(String)params.get("keywords");
        String roleId =(String)params.get("roleId");
        String provinceId=(String)params.get("provinceId");
        String cityId=(String)params.get("cityId");
        String areaId=(String)params.get("areaId");
        String cascadeLabel=(String)params.get("cascadeLabel");
        String cascadeLevel=(String)params.get("cascadeLevel");
        
        //组装sql
        sql.append("select a.pk_id,a.user_name,a.user_type,a.parent_id,a.province_id,a.city_id,a.area_id,a.contact_person,a.contact_way,a.remark,"
                + "from_unixtime(a.create_date, '%Y-%m-%d %H:%i:%S') as create_date,a.fk_project_id,a.cascade_label,a.cascade_level,p.project_name,b.user_name as parentName "
                + "from toe_user a left join toe_user b on a.parent_id=b.pk_id left join toe_project p on a.fk_project_id = p.pk_id where a.delete_flag=1 and a.pk_id !=1 ");
        if(StringUtils.isNotBlank(roleId)){
            sql.append("and a.user_type=#{roleId} ");
        }
        if(StringUtils.isNotBlank(provinceId)){
            sql.append("and a.province_id=#{provinceId} ");
        }
        if(StringUtils.isNotBlank(cityId)){
            sql.append("and a.city_id=#{cityId} ");
        }
        if(StringUtils.isNotBlank(areaId)){
            sql.append("and a.area_id=#{areaId} ");
        }
        if(StringUtils.isNotBlank(cascadeLabel)){
            sql.append("and a.cascade_label like concat(#{cascadeLabel},'%') ");
        }
        if(StringUtils.isNotBlank(cascadeLevel)){
            sql.append("and a.cascade_level>#{cascadeLevel} ");
        }
        if(StringUtils.isNotBlank(keywords)){
            sql.append("and a.user_name like concat('%',#{keywords},'%')  ");
        }
        sql.append("order by a.pk_id desc limit #{begin},#{pageSize}");
        return sql.toString();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 电信侧账号列表总数
     * @param params 参数
     * @return 总数
     * @author kangyanxiang 
     * @date 2016年7月19日 上午11:25:52
     */
    public String telecomCount(Map<String, Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append("select count(u.pk_id) from toe_user u inner join toe_user_role ur on u.pk_id=ur.user_id inner join toe_role r on ur.role_id=r.pk_id where u.delete_flag=1 ");
        String roleId=(String)params.get("roleId");
        if(StringUtils.isNotBlank(roleId)){
            sql.append("and r.pk_id=#{roleId} and u.pk_id!=#{accountId} ");
        }
        String provinceId=(String)params.get("provinceId");
        if(StringUtils.isNotBlank(provinceId)){
            sql.append("and u.province_id=#{provinceId} ");
        }
        String cityId=(String)params.get("cityId");
        if(StringUtils.isNotBlank(cityId)){
            sql.append("and u.city_id=#{cityId} ");
        }
        String areaId=(String)params.get("areaId");
        if(StringUtils.isNotBlank(areaId)){
            sql.append("and u.area_id=#{areaId} ");
        }
        String keywords=(String)params.get("keywords");
        if(StringUtils.isNotBlank(keywords)){
            sql.append("and u.user_name like concat('%',#{keywords},'%') ");
        }
        sql.append("and u.user_type in(2,3,4,6)");
        return sql.toString();
    }
    
    /**
     * 电信侧账号列表
     * @param params 参数
     * @return 列表
     * @author kangyanxiang 
     * @date 2016年7月19日 上午11:30:53
     */
    public String telecomList(Map<String, Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append("select u.pk_id,u.user_name,u.user_type,u.contact_person,u.contact_way,u.province_id,u.city_id,u.area_id,from_unixtime(u.create_date, '%Y-%m-%d %H:%i:%S') as create_date ");
        sql.append("from toe_user u inner join toe_user_role ur on u.pk_id=ur.user_id inner join toe_role r on ur.role_id=r.pk_id where u.delete_flag=1 ");
        String roleId=(String)params.get("roleId");
        if(StringUtils.isNotBlank(roleId)){
            sql.append("and r.pk_id=#{roleId} and u.pk_id!=#{accountId} ");
        }
        String provinceId=(String)params.get("provinceId");
        if(StringUtils.isNotBlank(provinceId)){
            sql.append("and u.province_id=#{provinceId} ");
        }
        String cityId=(String)params.get("cityId");
        if(StringUtils.isNotBlank(cityId)){
            sql.append("and u.city_id=#{cityId} ");
        }
        String areaId=(String)params.get("areaId");
        if(StringUtils.isNotBlank(areaId)){
            sql.append("and u.area_id=#{areaId} ");
        }
        String keywords=(String)params.get("keywords");
        if(StringUtils.isNotBlank(keywords)){
            sql.append("and u.user_name like concat('%',#{keywords},'%') ");
        }
        sql.append("and u.user_type in(2,3,4,6) order by u.create_date desc limit #{begin},#{pageSize}");
        return sql.toString();
    }
    /**
     * 通过客户ID获取用户名、用户密码
     * @param params 参数
     * @return sql
     * @author 许小满  
     * @date 2016年9月30日 下午5:49:34
     */
    public String getUserByCustomerIds(Map<String, Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append("select u.user_name,u.password,c.customer_id ");
        sql.append("from toe_user_customer c inner join toe_user u on c.user_id = u.pk_id ");
        sql.append("where u.delete_flag=1 ");
        /* customerIds in 查询优化  */
        Long[] customerIds = (Long[])params.get("customerIds");
        SqlUtil.in("c.customer_id", "customerIds", customerIds, sql);
        return sql.toString();
    }
}