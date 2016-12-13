package com.awifi.toe.project.mapper.sql;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**   
 * @Description:  项目模块sql组装
 * @Title: ProjectSql.java 
 * @Package com.awifi.toe.project.mapper.sql 
 * @author kangyanxiang 
 * @date 2016年5月16日 上午11:18:57
 * @version V1.0   
 */
public class ProjectSql {

    /**
     * 项目下拉列表
     * @param params 参数
     * @return sql
     * @author kangyanxiang 
     * @date Oct 31, 2016 10:14:05 AM
     */
    public String projectList(Map<String, Object> params){
        StringBuilder sql = new StringBuilder();
        String provinceId = (String) params.get("provinceId");
        String cityId = (String) params.get("cityId");
        String areaId = (String) params.get("areaId");
        String projectIds = (String) params.get("projectIds");
        sql.append("select pk_id,project_name from toe_project where delete_flag=1 ");
        if(StringUtils.isNotBlank(provinceId)){
            sql.append("and province_id=#{provinceId} ");
        }
        if(StringUtils.isNotBlank(cityId)){
            sql.append("and city_id=#{cityId} ");
        }
        if(StringUtils.isNotBlank(areaId)){
            sql.append("and area_id=#{areaId} ");
        }
        if(StringUtils.isNotBlank(projectIds)){
            sql.append("and pk_id in (").append(projectIds).append(") ");
        }
        return sql.toString();
    }
    
    /**
     * 查询项目总条数
     * @param params 参数
     * @return sql
     * @author kangyanxiang 
     * @date 2016年5月24日 上午9:46:33
     */
    public String pageCount(Map<String, Object> params){
        //参数
        StringBuffer sql = new StringBuffer();
        String keywords = (String) params.get("keywords");
        String provinceId = (String) params.get("provinceId");
        String cityId = (String) params.get("cityId");
        String areaId = (String) params.get("areaId");
        String projectIds = (String) params.get("projectIds");
        
        //组装sql
        sql.append("select count(pk_id) from toe_project where delete_flag=1 ");
        if(StringUtils.isNotBlank(keywords)){
            sql.append("and project_name like concat('%',#{keywords},'%') ");
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
        if(StringUtils.isNotBlank(projectIds)){
            sql.append("and pk_id in (").append(projectIds).append(") ");
        }
        return sql.toString();
    }
    
    /**
     * 查询项目分页（超级管理员／集团管理员）
     * @param params 参数
     * @return sql
     * @author kangyanxiang 
     * @date 2016年5月24日 上午9:48:45
     */
    public String pageQuery(Map<String, Object> params){
        //参数
        StringBuffer sql = new StringBuffer();
        String keywords = (String) params.get("keywords");
        String provinceId = (String) params.get("provinceId");
        String cityId = (String) params.get("cityId");
        String areaId = (String) params.get("areaId");
        String projectIds = (String) params.get("projectIds");
        
        //组装sql
        sql.append("select pk_id,project_name,province_id,city_id,area_id,contact_person,contact_way,remark,from_unixtime(create_date, '%Y-%m-%d %H:%i:%S') as create_date ")
            .append("from toe_project where delete_flag=1 ");
        if(StringUtils.isNotBlank(keywords)){
            sql.append("and project_name like concat('%',#{keywords},'%') ");
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
        if(StringUtils.isNotBlank(projectIds)){
            sql.append("and pk_id in (").append(projectIds).append(") ");
        }
        sql.append("order by pk_id desc limit #{begin},#{pageSize}");
        return sql.toString();
    }
    
    /**
     * 查询项目ID
     * @param params 参数
     * @return sql
     * @author kangyanxiang 
     * @date 2016年5月16日 上午11:20:59
     */
    public String findProjectId(Map<String, Object> params){
        
        //参数
        StringBuffer sql = new StringBuffer();
        String projectName = (String)params.get("projectName");
        String platformId = (String)params.get("platformId");
        
        //组装sql
        sql.append("select pk_id from toe_project where delete_flag=1 ");
        if(StringUtils.isNotBlank(projectName)){
            sql.append("and project_name like concat('%',(#{projectName}),'%') ");
        }
        if(StringUtils.isNotBlank(platformId)){
            sql.append("and fk_platform_id = #{platformId}");
        }
        return sql.toString();
    }
    
    /**
     * 模糊查询项目名称
     * @param params 参数
     * @return sql
     * @author kangyanxiang 
     * @date 2016年5月24日 上午9:43:24
     */
    public String show(Map<String, Object> params){
        //参数
        StringBuffer sql = new StringBuffer();
        String keywords = (String) params.get("keywords");

        //组装sql
        sql.append("select pk_id,project_name from toe_project where ");
        if(StringUtils.isNotBlank(keywords)){
            sql.append("project_name like concat('%',(#{keywords}),'%') and ");
        }
        sql.append("delete_flag=1 order by pk_id desc limit 50");
        return sql.toString();
    }
    /**
     * 通过项目IDs查找项目名称
     * @param params 参数
     * @return sql
     * @author 许小满  
     * @date 2016年8月24日 下午7:21:49
     */
    public String getProjectNamesByIds(Map<String, Object> params){
        StringBuilder sql = new StringBuilder();
        sql.append("select project_name ")
            .append("from toe_project where delete_flag=1 ");
        Long[] projectIds = (Long[])params.get("projectIds");
        int maxLength = projectIds != null ? projectIds.length : 0;
        sql.append("and pk_id in(");
        for(int i=0; i<maxLength; i++){
            sql.append("#{projectIds[").append(i).append("]}");
            if(i < (maxLength - 1)){
                sql.append(",");
            }
        }
        sql.append(")");
        return sql.toString();
    }
}
