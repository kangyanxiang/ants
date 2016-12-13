package com.awifi.toe.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Service;

import com.awifi.toe.project.mapper.sql.ProjectSql;
import com.awifi.toe.project.model.Project;

/**   
 * @Description:  项目维护持久层
 * @Title: ProjectMapper.java 
 * @Package com.awifi.toe.customer.mapper 
 * @author 亢燕翔 
 * @date 2015年11月23日 下午5:15:04
 * @version V1.0   
 */
@Service("projectMapper")
public interface ProjectMapper {

    /**
     * 查询项目总条数
     * @param keywords 关键字
     * @param areaId 
     * @param cityId 
     * @param provinceId 
     * @param projectIds 
     * @return count
     * @author 亢燕翔 
     * @date 2015年12月8日 下午2:29:41
     */
    @SelectProvider(type=ProjectSql.class, method="pageCount")
    int pageCount(@Param("keywords")String keywords,@Param("provinceId")String provinceId,@Param("cityId")String cityId,@Param("areaId")String areaId,@Param("projectIds")String projectIds);

    /**
     * 项目列表(分页)
     * @param begin 开始条数
     * @param pageSize 每页显示条数
     * @param keywords 关键字
     * @param areaId 
     * @param cityId 
     * @param provinceId 
     * @param projectIds 
     * @return list
     * @author 亢燕翔 
     * @date 2015年12月8日 下午2:30:51
     */
    @Results(value = {  
            @Result(property = "projectId", column = "pk_id", javaType = String.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "projectName", column = "project_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "contact", column = "contact_person", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "contactWay", column = "contact_way", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "remark", column = "remark", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "createDate", column = "create_date", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "provinceId", column = "province_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "cityId", column = "city_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "countyId", column = "area_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT)})
    @SelectProvider(type=ProjectSql.class, method="pageQuery")
    List<Project> pageQuery(@Param("begin")Integer begin,@Param("pageSize")Integer pageSize,@Param("keywords")String keywords,@Param("provinceId")String provinceId,
            @Param("cityId")String cityId,@Param("areaId")String areaId,@Param("projectIds")String projectIds);
    
    /**
     * 校验项目名称是否重复
     * @param projectName 项目名称
     * @return null
     * @author 亢燕翔 
     * @date 2015年12月7日 上午10:47:29
     */
    @Select("select count(pk_id) from toe_project where project_name = #{projectName} and delete_flag=1")
    int eqAddRepeat(@Param("projectName") String projectName);

    
    /**
     * 创建项目
     * @param projectName 项目名称
     * @param contact 联系人
     * @param contactWay 联系电话
     * @param remark 备注
     * @author 亢燕翔 
     * @param areaId 
     * @param cityId 
     * @param provinceId 
     * @date 2015年12月10日 下午2:21:15
     */
    @Insert("insert into toe_project (project_name,remark,contact_person,contact_way,create_date,update_date,province_id,city_id,area_id) "
            + "values (#{projectName},#{remark},#{contact},#{contactWay},unix_timestamp(now()),unix_timestamp(now()),#{provinceId},#{cityId},#{areaId})")
    void add(@Param("projectName")String projectName,@Param("contact") String contact,@Param("contactWay") String contactWay,
            @Param("remark")String remark,@Param("provinceId")String provinceId,@Param("cityId")String cityId,@Param("areaId")String areaId);

    /**
     * 项目名称重复性校验（编辑）
     * @param projectName 项目名称
     * @param id 项目ID
     * @return count
     * @author 亢燕翔 
     * @date 2016年1月5日 下午3:42:09
     */
    @Select("select count(pk_id) from toe_project where project_name = #{projectName} and pk_id != #{id} and delete_flag=1")
    int eqEditRepeat(@Param("projectName")String projectName,@Param("id")String id);
    
    /**
     * 编辑项目
     * @param id 
     * @param projectName 项目名称
     * @param contact 联系人
     * @param contactWay 联系方式
     * @param remark 备注
     * @param provinceId 
     * @param cityId 
     * @param areaId 
     * @author 亢燕翔 
     * @date 2015年12月9日 下午3:16:37
     */
    @Update("update toe_project set project_name=#{projectName},remark=#{remark},update_date=unix_timestamp(now()),contact_person=#{contact},contact_way=#{contactWay},"
            + "province_id=#{provinceId},city_id=#{cityId},area_id=#{areaId} where pk_id=#{id}")
    void edit(@Param("id")String id,@Param("projectName") String projectName,@Param("contact") String contact,@Param("contactWay") String contactWay,
            @Param("remark")String remark,@Param("provinceId")String provinceId,@Param("cityId")String cityId,@Param("areaId")String areaId);
   
    /**
     * 查找用户下级项目ID集合
     * @param cascadeLabel 用户层级
     * @return ids
     * @author kangyanxiang 
     * @date Oct 27, 2016 2:25:59 PM
     */
    @Select("select fk_project_id from toe_user u where cascade_label like concat(#{cascadeLabel},'%') ")
    List<String> getProjectIds(String cascadeLabel);
    
    /**
     * 项目下拉列表
     * @param provinceId 省
     * @param cityId 市
     * @param areaId 区县
     * @param projectIds 项目IDs
     * @return list
     * @author kangyanxiang 
     * @date Oct 31, 2016 10:10:04 AM
     */
    @Results(value = {  
            @Result(property = "projectId", column = "pk_id", javaType = String.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "projectName", column = "project_name", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
    @SelectProvider(type=ProjectSql.class, method="projectList")
    List<Project> getProjectList(@Param("provinceId")String provinceId,@Param("cityId")String cityId,@Param("areaId")String areaId,@Param("projectIds")String projectIds);
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 通过项目ID查找项目名称
     * @param projectId 项目ID
     * @return null
     * @author 亢燕翔 
     * @date 2015年12月2日 上午11:33:26
     */
    @Select("select project_name from toe_project where pk_id = #{projectId}")
    String getProjectNameById(@Param("projectId") Long projectId);
    
    /**
     * 通过项目IDs查找项目名称
     * @param projectIds 多个项目id
     * @return 项目名称
     * @author 许小满  
     * @date 2016年8月24日 下午7:20:28
     */
    @SelectProvider(type=ProjectSql.class, method="getProjectNamesByIds")
    List<String> getProjectNamesByIds(@Param("projectIds") Long[] projectIds);

    
    /**
     * 删除项目
     * @param id 项目ID
     * @author 亢燕翔 
     * @date 2015年12月8日 下午2:38:32
     */
    @Update("update toe_project set delete_flag = -1 where pk_id = #{id}")
    void delete(@Param("id") String id);

    /**
     * 项目详情
     * @param id 项目ID
     * @return null
     * @author 亢燕翔 
     * @date 2015年12月8日 下午2:47:00
     */
    @Results(value = {  
            @Result(property = "projectId", column = "pk_id", javaType = String.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "projectName", column = "project_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "contact", column = "contact_person", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "contactWay", column = "contact_way", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "remark", column = "remark", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "provinceId", column = "province_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "cityId", column = "city_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "countyId", column = "area_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT)})
    @Select("select pk_id,project_name,contact_person,contact_way,remark,province_id,city_id,area_id from toe_project where pk_id = #{id}")
    Project projectShow(@Param("id") String id);

    /**
     * 获取项目是否已经关联
     * @param id 项目ID
     * @return count
     * @author 亢燕翔 
     * @date 2015年12月25日 下午2:50:55
     */
    @Select("select count(t.pk_id) from toe_project t INNER JOIN toe_user u on t.pk_id = u.fk_project_id where t.pk_id=#{id}")
    int queryCount(String id);

    
    /**
     * 查询项目ID集合
     * @param projectName 项目名称
     * @return IDS
     * @author 亢燕翔 
     * @param platformId 平台
     * @date 2016年3月4日 上午9:17:24
     */
    @SelectProvider(type=ProjectSql.class, method="findProjectId")
    List<String> findProjectId(@Param("platformId")String platformId,@Param("projectName") String projectName);

    /**
     * 插入一条数据
     * @param project 项目实体
     * @author ZhouYing 
     * @date 2016年3月7日 上午9:48:11
     */
    @SelectKey(before=false,keyProperty="projectId",resultType=String.class,statementType=StatementType.STATEMENT,statement="SELECT LAST_INSERT_ID() AS projectId")
    @Insert("insert into toe_project (project_name,fk_platform_id,contact_person,contact_way,delete_flag,province_id,city_id,area_id,create_date,update_date) "
            + "values (#{projectName},#{platformId},#{contact},#{contactWay},1,#{provinceId},#{cityId},#{countyId},unix_timestamp(now()),unix_timestamp(now()))")
    void insert(Project project);

    /**
     * 根据项目名称获得主键
     * @param projectName 项目名称
     * @return 主键
     * @author ZhouYing 
     * @date 2016年3月7日 上午9:47:39
     */
    @Select("select pk_id from toe_project where project_name=#{projectName} and delete_flag=1")
    String getProjectIdByName(String projectName);

    
}