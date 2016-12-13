package com.awifi.toe.system.mapper.sql;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**   
 * @Description: 皮肤策略--动态封装sql类 
 * @Title: ThemeStrategySql.java 
 * @Package com.awifi.toe.system.mapper.sql 
 * @author 牛华凤
 * @date 2016年4月25日 下午6:00:23
 * @version V1.0   
 */
public class ThemeStrategySql {

    /**
     * 分页sql查询(皮肤策略列表(分页) -- 迭代8)
     * @param params 参数
     * @return sql
     * @author 牛华凤  
     * @date 2016年4月25日 下午6:02:21
     */
    public String getPageQuery(Map<String, Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append("select ts.pk_id,ts.theme_type,ts.content,from_unixtime(ts.create_date,'%Y-%m-%d %H:%i:%S') as create_date,ts.fk_customer_id,ts.fk_theme_id,t.pk_id,t.name ")
            .append("from toe_theme_strategy ts left join toe_theme t on ts.fk_theme_id=t.pk_id where ts.delete_flag = 1 ");
        String customerId = (String) params.get("customerId");// 客户id
        if(StringUtils.isNotBlank(customerId)){
            sql.append("and fk_customer_id=#{customerId} ");
        }
        String themeId = (String) params.get("themeId");// 皮肤id
        if(StringUtils.isNotBlank(themeId)){
            sql.append("and fk_theme_id= #{themeId} ");
        }
        String themeType = (String) params.get("themeType");// 类型
        if(StringUtils.isNotBlank(themeType)){
            sql.append("and theme_type=#{themeType} ");
        }
        String content = (String) params.get("content");//内容(关键字)
        if(StringUtils.isNotBlank(content)){
            sql.append("and content like concat('%',#{content},'%') ");
        }
        sql.append("order by create_date desc limit #{begin},#{pageSize}");
        return sql.toString();
    }

    /**
     * 皮肤策略总条数 -- 迭代8
     * @param params 参数
     * @return sql
     * @author 牛华凤  
     * @date 2016年4月25日 下午6:14:09
     */
    public String getPageCount(Map<String, Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append("select count(ts.pk_id) from toe_theme_strategy ts left join toe_theme t on ts.fk_theme_id=t.pk_id where ts.delete_flag = 1 ");
        String customerId = (String) params.get("customerId");// 客户id
        if(StringUtils.isNotBlank(customerId)){
            sql.append("and fk_customer_id=#{customerId} ");
        }
        String themeId = (String) params.get("themeId");// 皮肤id
        if(StringUtils.isNotBlank(themeId)){
            sql.append("and fk_theme_id= #{themeId} ");
        }
        String themeType = (String) params.get("themeType");// 类型
        if(StringUtils.isNotBlank(themeType)){
            sql.append("and theme_type=#{themeType} ");
        }
        String content = (String) params.get("content");//内容(关键字)
        if(StringUtils.isNotBlank(content)){
            sql.append("and content like concat('%',#{content},'%') ");
        }
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
        sql.append("insert into toe_theme_strategy(theme_type,content,remark,fk_customer_id,cascade_label,cascade_level,fk_theme_id,create_date,update_date) ")
            .append("values(#{themeType},#{content},#{remark},#{customerId},#{cascadeLabel},#{cascadeLevel},#{themeId},unix_timestamp(now()),unix_timestamp(now()))");
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
        sql.append("update toe_theme_strategy set theme_type=#{themeType},content=#{content},remark=#{remark},fk_customer_id=#{customerId},cascade_label=#{cascadeLabel},cascade_level=#{cascadeLevel},fk_theme_id=#{themeId},update_date=unix_timestamp(now())");
        sql.append(" where pk_id=#{id}");
        UPDATE_SQL = sql.toString();
        return UPDATE_SQL;
    }
    
    /**
     * 校验策略内容是否已经存在
     * @param paramMap 参数
     * @return sql
     * @author 许小满  
     * @date 2016年4月27日 下午11:22:22
     */
    public String isContentExist(Map<String,Object> paramMap){
        StringBuffer sql = new StringBuffer();
        sql.append("select count(pk_id) from toe_theme_strategy where delete_flag=1 and content=#{content} ");
        if(paramMap.get("id") != null){
            sql.append("and pk_id!=#{id}");
        }
        return sql.toString();
    }
    
    /**
     * 校验客户是否已经存在
     * @param paramMap 参数
     * @return sql
     * @author 许小满  
     * @date 2016年4月28日 下午12:20:03
     */
    public String isCustomerExist(Map<String,Object> paramMap){
        StringBuffer sql = new StringBuffer();
        sql.append("select count(pk_id) from toe_theme_strategy where delete_flag=1 and fk_customer_id=#{customerId} ");
        if(paramMap.get("id") != null){
            sql.append("and pk_id!=#{id}");
        }
        return sql.toString();
    }
    
}
