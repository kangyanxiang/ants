package com.awifi.toe.log.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Service;

import com.awifi.toe.log.mapper.sql.ExceptionLogSql;
import com.awifi.toe.log.model.ExceptionLog;

/**
 * 异常日志Mapper
 * @author 苏晨斌
 * 
 */
@Service("exceptionLogMapper")
public interface ExceptionLogMapper {

	/**
	 * 根据时间查找异常
	 * @param createDatetime 创建时间
	 * @return Exception
	 */
    @Select("select id,error_code,module_name,service_name,parameter,error_message,create_datetime from toe_exception_log where create_datetime={#createDatetime}")
    @Results(value = {
            @Result(property = "id", column = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "errorCode", column = "error_code", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "moduleName", column = "module_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "serviceName", column = "service_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "parameter", column = "parameter", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "errorMessage", column = "error_message", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "createDatetime", column = "create_datetime", javaType = String.class, jdbcType = JdbcType.DATE) 
            })
   Exception findException(@Param("createDatetime") Date createDatetime);
	
	
	/**
	 * 添加一条异常
	 * @param log 异常
	 * @return 主键id
	 */
    @Insert("insert into toe_exception_log(error_code,module_name,service_name,parameter,error_message,interface_url,interface_param,interface_return_value,create_date) "
            + "values(#{errorCode},#{moduleName},#{serviceName},#{parameter},#{errorMessage},#{interfaceUrl},#{interfaceParam},#{interfaceReturnValue}, unix_timestamp(now()))")
    int saveExceptionLog(ExceptionLog log);

	/**
	 * 获取记录总数(分页查询)
	 * @param startDate 创建时间
	 * @param endDate 结束时间
	 * @param moduleName 模块名称
     * @param serviceName service 名称
     * @param errorMessage 异常信息
     * @param interfaceUrl 接口地址
     * @param interfaceParam 接口参数
     * @param interfaceReturnValue 接口返回值
	 * @return 记录总数
	 * @author 苏晨斌   
	 * @date 2015年8月18日 下午4:44:48
	 */
    @SelectProvider(type=ExceptionLogSql.class, method="pageCount")
    int pageCount(@Param("startDate")String startDate, @Param("endDate")String endDate, 
            @Param("moduleName")String moduleName, @Param("serviceName")String serviceName, @Param("errorMessage")String errorMessage,
            @Param("interfaceUrl")String interfaceUrl, @Param("interfaceParam")String interfaceParam, @Param("interfaceReturnValue")String interfaceReturnValue);
	
	/**
	 * 获取记录列表(分页查询)
	 * @param begin 开始行
	 * @param pageSize 每页记录数
	 * @param startDate 创建时间
     * @param endDate 结束时间
	 * @param moduleName 模块名称
     * @param serviceName service 名称
     * @param errorMessage 异常信息
     * @param interfaceUrl 接口地址
     * @param interfaceParam 接口参数
     * @param interfaceReturnValue 接口返回值
	 * @return 记录集合
	 * @author 苏晨斌  
	 * @date 2015年8月18日 下午4:44:57
	 */
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "moduleName", column = "module_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "serviceName", column = "service_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "createDatetime", column = "create_date", javaType = String.class, jdbcType = JdbcType.VARCHAR)
            })
    @SelectProvider(type=ExceptionLogSql.class, method="pageQuery")
    List<ExceptionLog> pageQuery(@Param("begin")Integer begin, @Param("pageSize")Integer pageSize, 
            @Param("startDate")String startDate, @Param("endDate")String endDate,
            @Param("moduleName")String moduleName, @Param("serviceName")String serviceName, @Param("errorMessage")String errorMessage,
            @Param("interfaceUrl")String interfaceUrl, @Param("interfaceParam")String interfaceParam, @Param("interfaceReturnValue")String interfaceReturnValue);

	/**
	 * 通过主键id获取异常信息
	 * @param id  主键id
	 * @return  异常对象
	 * @author 苏晨斌  
	 * @date 2015年8月19日 下午3:26:49
	 */
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "errorCode", column = "error_code", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "moduleName", column = "module_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "serviceName", column = "service_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "parameter", column = "parameter", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "errorMessage", column = "error_message", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "interfaceUrl", column = "interface_url", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "interfaceParam", column = "interface_param", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "interfaceReturnValue", column = "interface_return_value", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "createDatetime", column = "create_date", javaType = String.class, jdbcType = JdbcType.VARCHAR)
            })
    @Select("select error_code,module_name,service_name,parameter,error_message,interface_url,interface_param,interface_return_value,FROM_UNIXTIME(create_date, '%Y-%m-%d %H:%i:%S' ) as create_date from toe_exception_log where pk_id=#{id}")
    ExceptionLog findById(@Param("id") Long id);
}