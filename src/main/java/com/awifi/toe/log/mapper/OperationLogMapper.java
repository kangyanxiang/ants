package com.awifi.toe.log.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Service;

import com.awifi.toe.log.mapper.sql.OperationLogSql;
import com.awifi.toe.log.model.OperationLog;

/**   
 * @Description:  
 * @Title: OperationLogMapper.java 
 * @Package com.awifi.toe.log.mapper 
 * @author ZhouYing 
 * @date 2016年7月7日 下午3:49:10
 * @version V1.0   
 */
@Service("operationLogMapper")
public interface OperationLogMapper {

    /**
     * 操作日志总数
     * @param keywords 模块关键字
     * @param userName 账号名称
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 总数
     * @author ZhouYing 
     * @date 2016年7月7日 下午4:12:30
     */
    @SelectProvider(type=OperationLogSql.class, method="pageCount")
    int pageCount(@Param("keywords") String keywords,@Param("userName") String userName,@Param("startDate") String startDate,@Param("endDate") String endDate);

    /**
     * 操作日志列表
     * @param keywords 模块关键字
     * @param userName 账号名称
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param begin 开始
     * @param pageSize 页面大小
     * @return 列表
     * @author ZhouYing 
     * @date 2016年7月7日 下午4:38:48
     */
    @SelectProvider(type=OperationLogSql.class, method="pageQuery")
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "moduleName", column = "module_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "content", column = "content", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "createDate", column = "create_date", javaType = String.class, jdbcType = JdbcType.DATE) 
            })
    List<OperationLog> pageQuery(@Param("keywords") String keywords,@Param("userName") String userName,@Param("startDate") String startDate,
                                 @Param("endDate") String endDate,@Param("begin") Integer begin,@Param("pageSize") Integer pageSize);

    /**
     * 操作日志详情
     * @param id 主键id
     * @return 详情
     * @author ZhouYing 
     * @date 2016年7月7日 下午5:08:28
     */
    @Select("select pk_id,module_name,request_ip,request_port,user_name,content,from_unixtime(create_date,'%Y-%m-%d %H:%i:%S') as create_date from toe_operation_log where pk_id=#{id}")
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "moduleName", column = "module_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "requestIp", column = "request_ip", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "requestPort", column = "request_port", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "content", column = "content", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "createDate", column = "create_date", javaType = String.class, jdbcType = JdbcType.DATE) 
            })
    OperationLog show(Long id);
    
    /**
     * 插入一条数据
     * @param operationLog 操作日志
     * @author 许小满  
     * @date 2016年7月12日 下午3:56:52
     */
    @Insert("insert into toe_operation_log(module_name,service_name,request_ip,request_port,user_name,content,fk_user_id,fk_customer_id,cascade_label,create_date) "
            + "values(#{moduleName},#{serviceName},#{requestIp},#{requestPort},#{userName},#{content},#{userId},#{customerId},#{cascadeLabel},unix_timestamp(now()))")
    void insert(OperationLog operationLog);
}