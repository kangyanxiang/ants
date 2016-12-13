package com.awifi.toe.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Service;

import com.awifi.toe.system.mapper.sql.SysConfigSql;
import com.awifi.toe.system.model.SysConfig;

/**   
 * @Description: 系统参数接口类 
 * @Title: SysConfigMapper.java 
 * @Package com.awifi.toe.system.mapper 
 * @author ZhouYing
 * @date 2015年6月26日 下午2:21:19
 * @version V1.0   
 */
@Service("sysConfigMapper")
public interface SysConfigMapper {
	
	/**
	 * 增加一条系统参数
	 * @param sysconfig 系统参数
	 * @author ZhouYing
	 * @date 2015年6月26日 下午2:25:18
	 */
    @Insert("insert into toe_system_config (alias_name,param_key,param_value,remark,order_no,create_date,update_date)"
            + "values(#{aliasName,jdbcType=VARCHAR},#{paramKey,jdbcType=VARCHAR},#{paramValue,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR},#{orderNo,jdbcType=INTEGER},unix_timestamp(now()),unix_timestamp(now()))")
    void insertSysConfig(SysConfig sysconfig);

	 /**
	  * 编辑系统参数
	  * @param sysconfig 系统参数
	  * @author ZhouYing 
	  * @date 2015年6月26日 下午5:44:44
	  */
    @Update("update toe_system_config set alias_name=#{aliasName},param_key=#{paramKey},param_value=#{paramValue},remark=#{remark},order_no=#{orderNo},update_date=unix_timestamp(now()) where pk_id=#{id}")
    void updateSysConfig(SysConfig sysconfig);
	
	/**
	 * 判断参数键是否存在
	 * @param id          主键
	 * @param paramKey    参数键
	 * @return 总数
	 * @author ZhouYing 
	 * @date 2015年6月30日 下午4:49:10
	 */
    @Select("select count(pk_id) from toe_system_config where param_key=#{paramKey} and pk_id!=#{id}")
    int findNumByIdAndParamKey(@Param("id") Long id, @Param("paramKey") String paramKey);
    
	/**
	 * 通过参数键获取参数值
	 * @param    paramKey      参数键
	 * @return 参数值
	 * @author ZhouYing 
	 * @date 2015年6月30日 下午4:50:00
	 */
    @Select("select param_value from toe_system_config where param_key=#{paramKey}")
    String getParamValue(@Param("paramKey") String paramKey);
	
	/**
	 * 通过参数键设置参数值
	 * @param paramKey 参数键
	 * @param paramValue 参数值
	 * @return 记录数
	 * @author 许小满  
	 * @date 2015年7月2日 下午7:40:22
	 */
    @Update("update toe_system_config set param_value=#{paramValue},update_date=unix_timestamp(now()) where param_key=#{paramKey}")
    int setParamValue(@Param("paramKey") String paramKey, @Param("paramValue") String paramValue);

	/**
	 * 通过主键查看系统参数配置详情
	 * @param id 主键
	 * @return 系统参数
	 * @author ZhouYing 
	 * @date 2015年8月17日 下午4:41:03
	 */
    @Select("select pk_id,alias_name,param_key,param_value,remark,order_no,FROM_UNIXTIME(create_date, '%Y-%m-%d %H:%i:%S' ) as create_date,FROM_UNIXTIME(update_date, '%Y-%m-%d %H:%i:%S' ) as update_date from toe_system_config where pk_id=#{id}")
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "aliasName", column = "alias_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "paramKey", column = "param_key", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "paramValue", column = "param_value", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "remark", column = "remark", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "orderNo", column = "order_no", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "createDatetime", column = "create_date", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "updateDatetime", column = "update_date", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
    SysConfig getSysConfig(@Param("id") String id);

	/**
	 * 统计系统参数配置记录总数（用于分页）
	 * @param keywords 关键字
	 * @return 总素
	 * @author ZhouYing 
	 * @date 2015年8月17日 下午4:55:52
	 */
    //@Select("select count(pk_id) from toe_system_config where concat(ifnull(alias_name,''),ifnull(param_key,''),ifnull(param_value,''),ifnull(remark,'')) like concat('%',ifnull(#{keywords},''),'%')")
    @SelectProvider(type=SysConfigSql.class, method="pageCount")
    int pageCount(@Param("keywords") String keywords);

	/**
	 * 获得系统配置参数列表（分页）
	 * @param begin      起始页
	 * @param pageSize   页面大小
	 * @param keywords  关键字
	 * @return 集合
	 * @author ZhouYing 
	 * @date 2015年8月17日 下午4:56:46
	 */
    @SelectProvider(type=SysConfigSql.class, method="pageQuery")
    //@Select("select pk_id,alias_name,param_key,param_value,remark,order_no,FROM_UNIXTIME(create_date, '%Y-%m-%d %H:%i:%S' ) as create_date,FROM_UNIXTIME(update_date, '%Y-%m-%d %H:%i:%S' ) as update_date from toe_system_config "
    //        + "where concat(ifnull(alias_name,''),ifnull(param_key,''),ifnull(param_value,''),ifnull(remark,'')) like concat('%',ifnull(#{keywords},''),'%') order by order_no limit #{begin},#{pageSize}")
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "aliasName", column = "alias_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "paramKey", column = "param_key", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "paramValue", column = "param_value", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "remark", column = "remark", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "orderNo", column = "order_no", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "createDatetime", column = "create_date", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "updateDatetime", column = "update_date", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
    List<SysConfig> pageQuery(@Param("begin") Integer begin, @Param("pageSize") Integer pageSize,
            @Param("keywords") String keywords);

	/**
	 * 删除系统参数配置
	 * @param id  主键
	 * @author ZhouYing 
	 * @date 2015年8月27日 上午9:54:39
	 */
    @Delete("delete from toe_system_config where pk_id=#{id}")
    void delete(@Param("id") Long id);
}