package com.awifi.toe.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Service;

import com.awifi.toe.system.mapper.sql.ThemeStrategySql;
import com.awifi.toe.system.model.ThemeStrategy;

/**   
 * @Description: 皮肤策略接口类 
 * @Title: ThemeStrategyMapper.java 
 * @Package com.awifi.toe.system.mapper 
 * @author 牛华凤
 * @date 2016年4月21日 上午10:23:59
 * @version V1.0   
 */
@Service("themeStrategyMapper")
public interface ThemeStrategyMapper {

    /**
     * 皮肤策略列表(分页) -- 迭代8
     * @param begin 起始页
     * @param pageSize 每页大小
     * @param customerId 客户id
     * @param themeId 皮肤id
     * @param themeType 类别(1、域名   2、编号)
     * @param content 内容(域名或编号)
     * @return 列表
     * @author 牛华凤  
     * @date 2016年4月21日 上午10:32:09
     */
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "themeType", column = "theme_type", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "content", column = "content", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "createDate", column = "create_date", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "updateDate", column = "update_date", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "customerId", column = "fk_customer_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "cascadeLabel", column = "cascade_label", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "themeId", column = "fk_theme_id", javaType = Long.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "theme.name", column = "name", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
    @SelectProvider(type=ThemeStrategySql.class, method="getPageQuery")
    List<ThemeStrategy> list(@Param("begin")Integer begin, @Param("pageSize")Integer pageSize, @Param("customerId")String customerId, 
            @Param("themeId")String themeId, @Param("themeType")String themeType, @Param("content")String content);

    /**
     * 皮肤策略总条数 -- 迭代8
     * @param customerId 客户id
     * @param themeId 皮肤id
     * @param themeType 类别(1、域名   2、编号)
     * @param content 内容(域名或编号)
     * @return 总条数
     * @author 牛华凤  
     * @date 2016年4月21日 下午4:40:40
     */
    @SelectProvider(type=ThemeStrategySql.class, method="getPageCount")
    int count(@Param("customerId")String customerId, @Param("themeId")String themeId, @Param("themeType")String themeType, @Param("content")String content);

    /**
     * 根据id删除皮肤策略(逻辑删除) -- 迭代8
     * @param id 皮肤策略id
     * @author 牛华凤  
     * @date 2016年4月21日 上午10:44:05
     */
    @Update("update toe_theme_strategy set delete_flag = -1 where pk_id = #{id}")
    void delete(Long id);

    /**
     * 根据皮肤id删除皮肤策略(逻辑删除)
     * @param themeId 皮肤id
     * @author 许小满  
     * @date 2016年4月27日 下午11:41:15
     */
    @Update("update toe_theme_strategy set delete_flag = -1 where fk_theme_id = #{themeId}")
    void deleteByThemeId(Long themeId);

    /**
     * 新增皮肤策略记录
     * @param themeStrategy 皮肤策略对象
     * @author 许小满  
     * @date 2016年4月25日 下午5:07:23
     */
    //@SelectKey(before=false,keyProperty="id",resultType=Long.class,statementType=StatementType.STATEMENT,statement="SELECT LAST_INSERT_ID() AS id")
    @InsertProvider(type=ThemeStrategySql.class, method="getInsertSql")
    void insert(ThemeStrategy themeStrategy);
    
    /**
     * 新增皮肤策略记录
     * @param themeStrategy 皮肤策略
     * @author 许小满  
     * @date 2016年4月25日 下午5:12:57
     */
    @UpdateProvider(type=ThemeStrategySql.class, method="getUpdateSql")
    void update(ThemeStrategy themeStrategy);
    
    /**
     * 通过id获取皮肤策略信息
     * @param id 皮肤策略表主键Id
     * @return 皮肤策略
     * @author 许小满  
     * @date 2016年4月25日 下午6:20:22
     */
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "themeType", column = "theme_type", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
            @Result(property = "content", column = "content", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "customerId", column = "fk_customer_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "cascadeLabel", column = "cascade_label", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "themeId", column = "fk_theme_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "createDate", column = "create_date", javaType = String.class, jdbcType = JdbcType.VARCHAR)
            })
    @Select("select pk_id,theme_type,content,remark,fk_customer_id,cascade_label,fk_theme_id,create_date from toe_theme_strategy where pk_id=#{id}")
    ThemeStrategy findById(Long id);
    
    /**
     * 校验策略内容是否已经存在
     * @param id 皮肤策略表主键id
     * @param content 内容
     * @return 相同的记录数
     * @author 许小满  
     * @date 2016年4月27日 下午11:24:33
     */
    @SelectProvider(type=ThemeStrategySql.class, method="isContentExist")
    int isContentExist(@Param("id")Long id, @Param("content")String content);
    
    /**
     * 校验客户是否已经存在
     * @param id 皮肤策略表主键id
     * @param customerId 客户id
     * @return 相同的记录数
     * @author 许小满  
     * @date 2016年4月28日 下午2:30:27
     */
    @SelectProvider(type=ThemeStrategySql.class, method="isCustomerExist")
    int isCustomerExist(@Param("id")Long id, @Param("customerId")Long customerId);
    
}
