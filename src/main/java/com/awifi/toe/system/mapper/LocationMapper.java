package com.awifi.toe.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Service;

import com.awifi.toe.system.model.Location;

@Service("locationMapper")
public interface LocationMapper {
    
    /**
     * 获取省级地区
     * @return list
     * @author kangyanxiang 
     * @date Oct 26, 2016 10:46:28 AM
     */
    @Results(value = {
            @Result(property = "locationId", column = "id", javaType = String.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "locationName", column = "location_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "parentId", column = "parent_id", javaType = String.class, jdbcType = JdbcType.BIGINT)
            })
    @Select("select id,location_name,parent_id from location where region_class=1")
    List<Location> getProvince();
    
    /**
     * 获取市/区县
     * @param parentId 父级ID
     * @param quetyType 查询层级
     * @return list
     * @author kangyanxiang 
     * @date Oct 26, 2016 10:46:47 AM
     */
    @Results(value = {
            @Result(property = "locationId", column = "id", javaType = String.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "locationName", column = "location_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "parentId", column = "parent_id", javaType = String.class, jdbcType = JdbcType.BIGINT)
            })
    @Select("select id,location_name,parent_id from location where parent_id=#{parentId} and region_class=#{quetyType}")
    List<Location> getLocation(@Param("parentId")String parentId,@Param("quetyType")int quetyType);

    /**
     * 获取全路径
     * @param locationId 地区ID
     * @return LocationFullName
     * @author kangyanxiang 
     * @date Oct 27, 2016 10:17:55 AM
     */
    @Select("select location_full_name from location where id=#{locationId}")
    String getLocationFullName(String locationId);

}
