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

import com.awifi.toe.system.mapper.sql.ThemeSql;
import com.awifi.toe.system.model.Theme;

/**   
 * @Description: 皮肤接口类 
 * @Title: ThemeMapper.java 
 * @Package com.awifi.toe.system.mapper 
 * @author 牛华凤
 * @date 2016年4月22日 上午9:45:03
 * @version V1.0   
 */
@Service("themeMapper")
public interface ThemeMapper {

    /**
     * 根据皮肤id查询皮肤名字
     * @param themeId 皮肤id
     * @return 皮肤名
     * @author 牛华凤  
     * @date 2016年4月22日 上午9:48:10
     */
    @Select("select name from toe_theme where delete_flag=1 and pk_id=#{themeId}")
    String findNameById(Long themeId);
    
    /**
     * 获取列表记录总数
     * @param keywords 关键字
     * @return 记录总数
     * @author 许小满  
     * @date 2016年4月22日 下午7:48:54
     */
    @SelectProvider(type=ThemeSql.class, method="getPageCount")
    int pageCount(@Param("keywords") String keywords);

    /**
     * 获取分页数据
     * @param keywords 关键字
     * @param begin 开始行数
     * @param pageSize 每页记录数
     * @return 皮肤集合
     * @author 许小满  
     * @date 2016年4月22日 下午7:51:45
     */
    @SelectProvider(type=ThemeSql.class, method="getPageQuery")
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "name", column = "name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "version", column = "version", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "thumb", column = "thumb", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "themePath", column = "theme_path", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "remark", column = "remark", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "createDate", column = "create_date", javaType = String.class, jdbcType = JdbcType.VARCHAR)
            })
    List<Theme> queryList(@Param("keywords") String keywords,@Param("begin") Integer begin,@Param("pageSize") Integer pageSize);

    /**
     * 通过编号获取记录数量
     * @param code 编号
     * @return 记录数量
     * @author 许小满  
     * @date 2016年4月24日 下午11:51:15
     */
    @Select("select count(pk_id) from toe_theme where code=#{code} and delete_flag=1")
    int findNumByCode(String code);
    
    /**
     * 新增皮肤记录
     * @param theme 皮肤对象
     * @author 许小满  
     * @date 2016年4月25日 上午12:06:34
     */
    //@SelectKey(before=false,keyProperty="id",resultType=Long.class,statementType=StatementType.STATEMENT,statement="SELECT LAST_INSERT_ID() AS id")
    @InsertProvider(type=ThemeSql.class, method="getInsertSql")
    void insert(Theme theme);
    
    /**
     * 新增皮肤记录
     * @param theme 组件对象
     * @author 许小满  
     * @date 2016年4月25日 上午12:07:06
     */
    @UpdateProvider(type=ThemeSql.class, method="getUpdateSql")
    void update(Theme theme);
    
    /**
     * 通过皮肤名称获取皮肤集合
     * @param name 皮肤名称
     * @return 皮肤集合
     * @author 许小满  
     * @date 2016年4月25日 上午8:42:34
     */
    @SelectProvider(type=ThemeSql.class, method="findByNameSql")
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "name", column = "name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            })
    List<Theme> findByName(@Param("name")String name);
    
    /**
     * 通过id获取皮肤信息
     * @param id 皮肤表主键Id
     * @return 皮肤
     * @author 许小满  
     * @date 2016年4月25日 上午9:25:54
     */
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "code", column = "code", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "name", column = "name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "version", column = "version", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "thumb", column = "thumb", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "themePath", column = "theme_path", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "remark", column = "remark", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "createDate", column = "create_date", javaType = String.class, jdbcType = JdbcType.VARCHAR)
            })
    @Select("select pk_id,code,name,version,thumb,theme_path,remark,create_date from toe_theme where pk_id=#{id}")
    Theme findById(Long id);
    
    /**
     * 系统配置--换肤配置--删除
     * @param id 主键id
     * @author 苏晨斌  
     * @date 2016年4月25日 上午9:08:00
     */
    @Update("update toe_theme set delete_flag = -1 where pk_id = #{id}")
    void delete(Long id);
    
    /**
     * 校验皮肤名称是否已经存在
     * @param id 皮肤表主键id
     * @param name 皮肤名称
     * @return 记录数量
     * @author 许小满  
     * @date 2016年4月28日 下午6:01:10
     */
    @SelectProvider(type=ThemeSql.class, method="isNameExist")
    int isNameExist(@Param("id")Long id, @Param("name")String name);
    
    /**
     * 根据域名、编码获取皮肤路径
     * @param domain 域名
     * @param themeCode 皮肤编号
     * @return 皮肤路径
     * @author 许小满  
     * @date 2016年5月4日 下午3:00:14
     */
    @SelectProvider(type=ThemeSql.class, method="findPathByDomainAndCode")
    String findPathByDomainAndCode(@Param("domain")String domain, @Param("themeCode")String themeCode);
    
    /**
     * 根据客户信息获取皮肤路径
     * @param customerIds 客户ids
     * @param cascadeLevel 客户层级
     * @return 皮肤路径
     * @author 许小满  
     * @date 2016年5月9日 上午9:44:24
     */
    @SelectProvider(type=ThemeSql.class, method="findPathByCustomerInfo")
    String findPathByCustomerInfo(@Param("customerIds")String customerIds, @Param("cascadeLevel")Integer cascadeLevel);
    
}