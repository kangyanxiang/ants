package com.awifi.toe.log.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Service;

import com.awifi.toe.log.mapper.sql.RequestLogSql;
import com.awifi.toe.log.model.RequestLog;

/**   
 * @Description:  
 * @Title: RequestLogMapper.java 
 * @Package com.awifi.toe.log.mapper 
 * @author 苏晨斌 
 * @date 2016年4月21日 上午11:17:27
 * @version V1.0   
 */
@Service("requestLogMapper")
public interface RequestLogMapper {
    
    /**
     * 插入一条请求日志
     * @param requestlog 请求日志
     * @author 苏晨斌  
     * @date 2016年4月21日 下午2:33:41
     */
    @Insert("insert into toe_request_log (module_name,request_type,request_ip,request_port,request_url, request_param,request_return_value,create_date) "
            + "values(#{moduleName,jdbcType=VARCHAR},#{requestType,jdbcType=VARCHAR},#{requestIp,jdbcType=VARCHAR},#{requestPort,jdbcType=VARCHAR},#{requestUrl,jdbcType=VARCHAR},#{requestParam,jdbcType=VARCHAR},#{requestReturnValue,jdbcType=LONGVARCHAR},unix_timestamp(now()))")
     void insertRequestLog(RequestLog requestlog);

    /**
     * 获取记录总数(分页查询)
     * @param keywords 关键字(查询条件)
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 记录总数
     * @author 苏晨斌  
     * @date 2016年4月21日 下午2:34:44
     */
    @SelectProvider(type=RequestLogSql.class, method="pageCount")
    int pageCount(@Param("keywords") String keywords,@Param("startDate") String startDate,@Param("endDate") String endDate);
   
    /**
     * 获取记录列表(分页查询)
     * @param begin 开始行
     * @param pageSize 每页记录数
     * @param keywords  关键字(查询条件)
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 记录集合
     * @author 苏晨斌  
     * @date 2016年4月21日 下午2:35:14
     */
    @SelectProvider(type=RequestLogSql.class, method="pageQuery")
    @Results(value = { 
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT), 
            @Result(property = "moduleName", column = "module_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "requestUrl", column = "request_url", javaType = String.class, jdbcType = JdbcType.LONGVARCHAR),
            @Result(property = "createDatetime", column = "create_date", javaType = String.class, jdbcType = JdbcType.VARCHAR)
            })
    List<RequestLog> pageQuery(@Param("begin")Integer begin, @Param("pageSize")Integer pageSize, @Param("keywords")String keywords,@Param("startDate") String startDate,@Param("endDate") String endDate);
   
    /**
     * 通过主键id获取请求信息
     * @param id 主键id
     * @return  请求信息
     * @author 苏晨斌  
     * @date 2015年8月19日 下午3:53:08
     */
    @Select("select pk_id,module_name,request_type,request_ip,request_port,request_url, request_param,request_return_value,FROM_UNIXTIME(create_date, '%Y-%m-%d %H:%i:%S' ) as create_date from toe_request_log where pk_id=#{id}")
    @Results(value = {  
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),  
            @Result(property = "moduleName", column = "module_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "requestType", column = "request_type", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "requestIp", column = "request_ip", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "requestPort", column = "request_port", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "requestUrl", column = "request_url", javaType = String.class, jdbcType = JdbcType.LONGVARCHAR),
            @Result(property = "requestParam", column = "request_param", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "requestReturnValue", column = "request_return_value", javaType = String.class, jdbcType = JdbcType.LONGVARCHAR),
            @Result(property = "createDatetime", column = "create_date", javaType = String.class, jdbcType = JdbcType.VARCHAR)
            })
    RequestLog findById(@Param("id") Long id);

    /**
     *  删除请求日志
     * @param time 时间
     * @author 苏晨斌  
     * @date 2016年4月27日 下午2:38:37
     */
    @Delete("delete from toe_request_log where create_date < #{time}")
    void delete(@Param("time")Long time);
}
