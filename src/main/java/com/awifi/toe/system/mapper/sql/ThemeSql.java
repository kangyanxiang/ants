package com.awifi.toe.system.mapper.sql;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**   
 * @Description:  皮肤部分复杂sql
 * @Title: ThemeSql.java 
 * @Package com.awifi.toe.system.mapper.sql 
 * @author 许小满 
 * @date 2016年4月22日 下午7:29:10
 * @version V1.0   
 */
public class ThemeSql {

    /**
     * 记录总数sql
     * @param params 参数
     * @return sql
     * @author 许小满  
     * @date 2016年4月22日 下午7:40:00
     */
    public String getPageCount(Map<String, Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append("select count(pk_id) from toe_theme where delete_flag=1 ");
        String keywords = (String)params.get("keywords");
        if(StringUtils.isNotBlank(keywords)){
            sql.append("and name like concat('%',#{keywords},'%')");
        }
        return sql.toString();
    }
    
    /**
     * 记录内容sql
     * @param params 参数
     * @return sql
     * @author 许小满  
     * @date 2016年4月22日 下午7:47:01
     */
    public String getPageQuery(Map<String, Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append("select pk_id,name,version,thumb,theme_path,remark,from_unixtime(create_date,'%Y-%m-%d') as create_date ")
            .append("from toe_theme where delete_flag=1 ");
        String keywords = (String)params.get("keywords");
        if(StringUtils.isNotBlank(keywords)){
            sql.append("and name like concat('%',#{keywords},'%')");
        }
        sql.append("order by pk_id desc limit #{begin},#{pageSize}");
        return sql.toString();
    }
    
    /** 新增记录sql */
    private static String INSERT_SQL = null;
    /**
     * 新增记录sql
     * @return sql
     * @author 许小满  
     * @date 2016年4月25日 上午12:10:37
     */
    public String getInsertSql() {
        if (INSERT_SQL != null) {
            return INSERT_SQL;
        }
        StringBuffer sql = new StringBuffer(200);
        sql.append("insert into toe_theme(code,name,version,thumb,theme_path,remark,create_date,update_date) ")
            .append("values(#{code},#{name},#{version},#{thumb},#{themePath},#{remark},unix_timestamp(now()),unix_timestamp(now()))");
        INSERT_SQL = sql.toString();
        return INSERT_SQL;
    }
    
    /** 更新记录sql */
    private static String UPDATE_SQL = null;
    /**
     * 更新sql
     * @return sql
     * @author 许小满  
     * @date 2015年12月21日 下午7:41:34
     */
    public String getUpdateSql() {
        if (UPDATE_SQL != null) {
            return UPDATE_SQL;
        }
        StringBuffer sql = new StringBuffer(160);
        sql.append("update toe_theme set name=#{name},version=#{version},thumb=#{thumb},theme_path=#{themePath},remark=#{remark},update_date=unix_timestamp(now())");
        sql.append(" where pk_id=#{id}");
        UPDATE_SQL = sql.toString();
        return UPDATE_SQL;
    }
    
    /**
     * 通过皮肤名称获取皮肤集合对应的sql语句
     * @param paramMap 参数
     * @return sql
     * @author 许小满  
     * @date 2016年4月25日 上午8:50:28
     */
    public String findByNameSql(Map<String,Object> paramMap) {
        StringBuffer sql = new StringBuffer();
        sql.append("select pk_id,name from toe_theme where delete_flag=1 ");
        String name = (String)paramMap.get("name");
        if(StringUtils.isNotBlank(name)){
            sql.append("and name like CONCAT('%',#{name},'%') ");
        }
        sql.append("order by pk_id desc");
        return sql.toString();
    }
    
    /**
     * 校验策略内容是否已经存在
     * @param paramMap 参数
     * @return sql
     * @author 许小满  
     * @date 2016年4月28日 下午5:58:56
     */
    public String isNameExist(Map<String,Object> paramMap){
        StringBuffer sql = new StringBuffer();
        sql.append("select count(pk_id) from toe_theme where delete_flag=1 and name=#{name} ");
        if(paramMap.get("id") != null){
            sql.append("and pk_id!=#{id}");
        }
        return sql.toString();
    }
    
    /**
     * 根据域名、编码获取皮肤路径
     * @param paramMap 参数
     * @return sql
     * @author 许小满  
     * @date 2016年5月4日 下午2:56:36
     */
    public String findPathByDomainAndCode(Map<String,Object> paramMap){
        StringBuffer sql = new StringBuffer();
        sql.append("select t.theme_path from toe_theme t where t.delete_flag=1 ")
            .append("and exists(")
            .append("select ts.pk_id from toe_theme_strategy ts where t.pk_id=ts.fk_theme_id and ts.delete_flag=1 ")
            .append("and ( ")
                //按域名匹配
                .append("(ts.theme_type=1 and ts.content=#{domain}) ");
        //按编号匹配
        String themeCode = (String)paramMap.get("themeCode");//皮肤编号
        if(StringUtils.isNotBlank(themeCode)){
            sql.append("or (ts.theme_type=2 and ts.content=#{themeCode})");
        }
        sql.append(")) order by t.pk_id desc limit 1");
        return sql.toString();
    }
    
    /**
     * 根据客户信息获取皮肤路径
     * @param paramMap 参数
     * @return sql
     * @author 许小满  
     * @date 2016年5月9日 上午9:44:24
     */
    public String findPathByCustomerInfo(Map<String,Object> paramMap){
        StringBuffer sql = new StringBuffer();
        sql.append("select t.theme_path from toe_theme t inner join toe_theme_strategy ts on t.pk_id=ts.fk_theme_id ")
            .append("where t.delete_flag=1 and ts.delete_flag=1 ");
        String customerIds = (String)paramMap.get("customerIds");
        if(StringUtils.isNotBlank(customerIds)){
            sql.append("and ts.fk_customer_id in (").append(customerIds).append(") ");
        }
        if(paramMap.get("cascadeLevel") != null){
            sql.append("and ts.cascade_level<=#{cascadeLevel} ");
        }
        sql.append("order by ts.cascade_level desc limit 1");
        return sql.toString();
    }
    
}