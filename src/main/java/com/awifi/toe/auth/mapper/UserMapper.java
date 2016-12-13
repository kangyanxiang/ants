package com.awifi.toe.auth.mapper;

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

import com.awifi.toe.auth.mapper.sql.UserMapperSql;
import com.awifi.toe.auth.model.User;
import com.awifi.toe.auth.model.UserSync;

/**   
 * @Description:  用户 -- 持久层
 * @Title: UserMapper.java 
 * @Package com.awifi.toe.auth.mapper 
 * @author kangyanxiang 
 * @date 2015年5月8日 上午11:01:49
 * @version V1.0   
 */
@Service("userMapper")
public interface UserMapper {
	
    /**
     *查找该账号的角色id
     * @param accountId 账号id
     * @return 角色id
     * @author ZhouYing 
     * @date 2015年11月24日 下午5:47:27
     */
    @Select("select r.pk_id from toe_user u inner join toe_user_role ur on u.pk_id=ur.user_id inner join toe_role r on r.pk_id=ur.role_id where u.pk_id=#{accountId}")
    Long getRoleIdByAccountId(Long accountId);
    
    /**
     * 插入一条数据(一级用户)
     * @param user 账号
     * @return 主键
     * @author ZhouYing 
     * @date 2015年11月24日 上午9:46:40
     */
    @SelectKey(before=false,keyProperty="id",resultType=Long.class,statementType=StatementType.STATEMENT,statement="SELECT LAST_INSERT_ID() AS id")
    @Insert("insert into toe_user(user_name,password,user_type,parent_id,province_id,city_id,area_id,contact_person,contact_way,remark,create_date,update_date,fk_project_id,cascade_label,cascade_level) "
            + "values(#{userName},#{password},#{userType},#{parentId},#{provinceId},#{cityId},#{areaId},#{contactPerson},#{contactWay},#{remark},unix_timestamp(now()),unix_timestamp(now()),#{fkProjectId},#{cascadeLabel},#{cascadeLevel})")
    Long insert(User user);
    
    /**
     * 更新用户层级
     * @param id 
     * @param cascadeLabel 
     * @param cascadeLevel 
     * @param parentId 
     * @author kangyanxiang 
     * @date Oct 28, 2016 3:50:46 PM
     */
    @Update("update toe_user set parent_id=#{parentId},cascade_label=#{cascadeLabel},cascade_level=#{cascadeLevel} where pk_id=#{id}")
    void updateUserLabel(@Param("id")Long id,@Param("parentId")Long parentId, @Param("cascadeLabel")String cascadeLabel,@Param("cascadeLevel")int cascadeLevel);
    
    /**
     * 查询管理员账号总数
     * @param keywords 关键字
     * @param roleId 角色
     * @param provinceId 省
     * @param cityId 市
     * @param areaId 区
     * @param cascadeLabel 层级
     * @param cascadeLevel 等级
     * @return count
     * @author kangyanxiang 
     * @date Oct 26, 2016 2:23:37 PM
     */
    @SelectProvider(type=UserMapperSql.class, method="pageCount")
    int pageCount(@Param("keywords")String keywords,@Param("roleId")String roleId,@Param("provinceId")String provinceId,@Param("cityId")String cityId,
            @Param("areaId")String areaId,@Param("cascadeLabel")String cascadeLabel,@Param("cascadeLevel")String cascadeLevel);
    
    /**
     * 列表
     * @param keywords 关键字
     * @param roleId 角色
     * @param provinceId 省
     * @param cityId 市
     * @param areaId 区
     * @param cascadeLabel 层级
     * @param cascadeLevel 等级
     * @param begin 
     * @param pageSize 
     * @return list
     * @author kangyanxiang     
     * @date Oct 26, 2016 3:37:34 PM
     */
    @Results(value = {  
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "userType", column = "user_type", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "parentId", column = "parent_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "provinceId", column = "province_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "cityId", column = "city_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "areaId", column = "area_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "contactPerson", column = "contact_person", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "contactWay", column = "contact_way", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "remark", column = "remark", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "createDate", column = "create_date", javaType = String.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "fkProjectId", column = "fk_project_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "cascadeLabel", column = "cascade_label", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "cascadeLevel", column = "cascade_level", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "projectName", column = "project_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "parentName", column = "parentName", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
    @SelectProvider(type=UserMapperSql.class, method="pageQuery")
    List<User> pageQuery(@Param("keywords")String keywords,@Param("roleId")String roleId,@Param("provinceId")String provinceId,@Param("cityId")String cityId,
            @Param("areaId")String areaId,@Param("cascadeLabel")String cascadeLabel,@Param("cascadeLevel")String cascadeLevel,@Param("begin")Integer begin,@Param("pageSize")Integer pageSize);
    
    /**
     * 项目下用户数量
     * @param projectId 
     * @return count
     * @author kangyanxiang 
     * @date Oct 28, 2016 10:59:53 AM
     */
    @Select("select count(pk_id) from toe_user where fk_project_id=#{projectId} and delete_flag=1")
    int getCustomerCount(String projectId);
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	/**
	 * 通过用户名 查询用户信息
	 * @param userName 用户名
	 * @return 用户
	 * @author kangyanxiang
	 * @date 2015年5月8日 上午11:08:02
	 */
    @Select("select pk_id,user_name,password,user_type from toe_user where user_name=#{userName} and delete_flag=1")
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "password", column = "password", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "userType", column = "user_type", javaType = Integer.class, jdbcType = JdbcType.INTEGER)
            })
	User findUserByUserName(@Param("userName")String userName);

    /**
     * 电信侧账号总数
     * @param keywords 关键字
     * @param accountId 账号id
     * @param roleId 角色id
     * @param provinceId 省id
     * @param cityId 市id
     * @param areaId 区县id
     * @return 符合条件的总数
     * @author ZhouYing 
     * @date 2015年11月23日 下午2:21:17
     */
    @SelectProvider(type=UserMapperSql.class, method="telecomCount")
    int telecomCount(@Param("keywords") String keywords,@Param("accountId") Long accountId,@Param("roleId") String roleId,@Param("provinceId") String provinceId,@Param("cityId") String cityId,@Param("areaId") String areaId);

    /**
     * 电信侧账号集合
     * @param keywords 关键字
     * @param accountId 账号id
     * @param roleId 角色id
     * @param provinceId 省id
     * @param cityId 市id
     * @param areaId 区县id
     * @param begin 开始行
     * @param pageSize 页面大小
     * @return 账号
     * @author ZhouYing 
     * @date 2015年11月23日 下午2:34:31
     */
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "userType", column = "user_type", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "contactPerson", column = "contact_person", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "contactWay", column = "contact_way", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "provinceId", column = "province_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "cityId", column = "city_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "areaId", column = "area_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "createDate", column = "create_date", javaType = String.class, jdbcType = JdbcType.VARCHAR)
            })
    @SelectProvider(type=UserMapperSql.class, method="telecomList")
    List<User> telecomList(@Param("keywords") String keywords,@Param("accountId") Long accountId,@Param("roleId") String roleId,@Param("provinceId") String provinceId,@Param("cityId") String cityId,@Param("areaId") String areaId,@Param("begin") Integer begin,@Param("pageSize") Integer pageSize);

    /**
     * 根据账号id重置密码
     * @param password 密码
     * @param id 账号主键id
     * @author ZhouYing 
     * @date 2015年11月24日 上午10:14:43
     */
    @Update("update toe_user set password=#{password},update_date=unix_timestamp(now()) where pk_id=#{id}")
    void updatePwdById(@Param("password") String password,@Param("id") Long id);

    /**
     * 逻辑删除账号
     * @param id 主键id
     * @author ZhouYing 
     * @date 2015年11月24日 上午10:39:21
     */
    @Update("update toe_user set delete_flag=-1,update_date=unix_timestamp(now()) where pk_id=#{id}")
    void delete(Long id);

    /**
     * 查看该账号名称是否存在
     * @param userName 账号名称
     * @return 满足条件的总数
     * @author ZhouYing 
     * @date 2015年11月24日 上午11:52:54
     */
    @Select("select count(pk_id) from toe_user where user_name=#{userName} and delete_flag=1")
    int isUserNameExit(String userName);

    /**
     * 保存编辑后的账号信息
     * @param accountId 账号id
     * @param contactPerson 联系人
     * @param contactWay 联系方式
     * @param remark 备注
     * @author ZhouYing 
     * @date 2015年11月24日 下午4:12:55
     */
    @Update("update toe_user set fk_project_id=#{projectId},contact_person=#{contactPerson},contact_way=#{contactWay},remark=#{remark},update_date=unix_timestamp(now())where pk_id=#{accountId}")
    void updateAccount(@Param("accountId") String accountId,@Param("projectId")String projectId,@Param("contactPerson") String contactPerson,@Param("contactWay") String contactWay,@Param("remark") String remark);

    /**
     * 客户侧编辑保存
     * @param accountId 账号id
     * @param contactPerson 联系人
     * @param contactWay 联系方式
     * @param remark 备注
     * @param projectId 项目id
     * @author ZhouYing 
     * @date 2015年12月2日 下午1:58:04
     */
    @Update("update toe_user set contact_person=#{contactPerson},contact_way=#{contactWay},remark=#{remark},fk_project_id=#{projectId},update_date=unix_timestamp(now()) where pk_id=#{accountId}")
    void update(@Param("accountId") Long accountId,@Param("contactPerson") String contactPerson,@Param("contactWay") String contactWay,@Param("remark") String remark,@Param("projectId") Long projectId);
    
   

    /**
     *账号详情
     * @param accountId 账号id
     * @return 账号详情
     * @author ZhouYing 
     * @date 2015年11月25日 上午10:47:16
     */
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "userType", column = "user_type", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "contactPerson", column = "contact_person", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "contactWay", column = "contact_way", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "provinceId", column = "province_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "cityId", column = "city_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "areaId", column = "area_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "remark", column = "remark", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "fkProjectId", column = "fk_project_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "createDate", column = "create_date", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "cascadeLabel", column = "cascade_label", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "cascadeLevel", column = "cascade_level", javaType = Integer.class, jdbcType = JdbcType.INTEGER)
            })
    @Select("select pk_id,user_name,user_type,contact_person,contact_way,province_id,city_id,area_id,remark,fk_project_id,from_unixtime(create_date, '%Y-%m-%d %H:%i:%S') as create_date,cascade_label,cascade_level from toe_user where pk_id=#{accountId}")
    User getInfoById(Long accountId);
    
    /**
     *账号详情
     * @param accountId 账号id
     * @return 账号详情
     * @author kangyanxiang  
     * @date 2016年1月9日 下午6:25:40
     */
    @Select("select pk_id,user_name,user_type,parent_id,contact_person,contact_way,province_id,city_id,area_id,remark,fk_project_id,from_unixtime(create_date, '%Y-%m-%d %H:%i:%S') as create_date "
            + "from toe_user where pk_id=#{accountId}")
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "userType", column = "user_type", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "parentId", column = "parent_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "contactPerson", column = "contact_person", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "contactWay", column = "contact_way", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "provinceId", column = "province_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "cityId", column = "city_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "areaId", column = "area_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "remark", column = "remark", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "fkProjectId", column = "fk_project_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "createDate", column = "create_date", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            })
    User getInfoByIdWithCustomer(Long accountId);
    
    /**
     * 用户详细信息
     * @param userName 用户名
     * @return 用户对象
     * @author kangyanxiang  
     * @date 2015年11月26日 下午3:48:22
     */
    @Select("select pk_id,user_name,user_type,contact_person,contact_way,province_id,city_id,area_id,remark,fk_project_id from toe_user where delete_flag=1 and user_name=#{userName}")
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "userType", column = "user_type", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "contactPerson", column = "contact_person", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "contactWay", column = "contact_way", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "provinceId", column = "province_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "cityId", column = "city_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "areaId", column = "area_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "remark", column = "remark", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "fkProjectId", column = "fk_project_id", javaType = Long.class, jdbcType = JdbcType.BIGINT)
            })
    User getInfoByName(String userName);

    /**
     * 通过用户名查找该用户密码
     * @param userName 用户名
     * @return 密码
     * @author kangyanxiang
     * @date 2015年11月25日 下午3:16:24
     */
    @Select("select password from toe_user where user_name = #{userName} and delete_flag=1")
    String findPwdByUserName(@Param("userName") String userName);
    
    /**
     * 通过用户名修改密码
     * @param password 密码
     * @param userName 用户名
     * @return 是否修改成功
     * @author kangyanxiang
     * @date 2015年11月25日 下午3:21:57
     */
    @Update("update toe_user set password=#{password},update_date=unix_timestamp(now()) where user_name=#{userName} and delete_flag=1")
    int updatePwdByUserName(@Param("password")String password,@Param("userName")String userName);

    /**
     * 客户侧创建账号，关联账号和客户
     * @param id 账号id
     * @param customerId 客户id
     * @author kangyanxiang
     * @date 2015年11月26日 下午1:44:05
     */
    @Insert(" insert into toe_user_customer(user_id,customer_id) values(#{id},#{customerId})")
    void userCustomer(@Param("id") Long id,@Param("customerId") Long customerId);
    
    /**
     * 修改账号信息
     * @param userName 用户名
     * @param contactPerson 联系人
     * @param contactWay 联系方式
     * @author kangyanxiang
     * @date 2015年11月25日 上午9:36:25
     */
    @Update("update toe_user set contact_person=#{contactPerson},contact_way=#{contactWay},update_date=unix_timestamp(now()) where user_name=#{userName}")
    void updateInfoModify(@Param("userName") String userName,@Param("contactPerson") String contactPerson,@Param("contactWay") String contactWay);

    /**
     * 查询市级管理员的下级账号
     * @param cityId 市id
     * @return 账号id集合
     * @author kangyanxiang
     * @date 2015年11月26日 下午3:48:40
     */
    @Select("select pk_id from toe_user where city_id=#{cityId} and delete_flag=1 and user_type between 2 and 4")
    List<Long> getAreaAccount(Long cityId);

    /**
     * 查询省级管理员的下级账号
     * @param provinceId 省id
     * @return 账号id集合
     * @author kangyanxiang 
     * @date 2015年11月26日 下午3:56:19
     */
    @Select("select pk_id from toe_user where province_id=#{provinceId} and delete_flag=1 and user_type between 2 and 4")
    List<Long> getCityAccount(Long provinceId);
    
    /**
     * 获取全部电信侧账号
     * @return 账号
     * @author kangyanxiang 
     * @date 2016年6月2日 下午5:19:01
     */
    @Select("select pk_id from toe_user where delete_flag=1 and user_type in(2,3,4,6)")
    List<Long> getAllAccount();

    /**
     * 通过主键ID获取客户ID
     * @param id 主键ID
     * @return null
     * @author kangyanxiang
     * @date 2015年11月30日 上午10:45:28
     */
    @Select("select c.customer_id from toe_user u inner join toe_user_customer c on u.pk_id=c.user_id where u.pk_id=#{id}")
    Long getCustomerIdById(@Param("id") Long id);
   
    /**
     * 通过客户id获取账号id
     * @param customerId 客户id
     * @return 账号id
     * @author kangyanxiang
     * @date 2015年12月1日 下午2:55:20
     */
    @Select("select user_id from toe_user_customer where customer_id=#{customerId} limit 1")
    Long getAccountIdById(Long customerId);
    
    /**
     * 通过客户id获取账号
     * @param customerId 客户id
     * @return 账号id
     * @author kangyanxiang 
     * @date 2015年12月1日 下午2:55:20
     */
    @Select("select u.user_name from toe_user_customer uc left join toe_user u on u.pk_id=uc.user_id where uc.customer_id=#{customerId} limit 1")
    String getUserNameById(String customerId);
    
    /**
     * 判断该省下是否已经存在账号
     * @param provinceId 省id
     * @return 满足条件的总数
     * @author kangyanxiang 
     * @date 2015年12月1日 上午11:49:29
     */
    @Select("select count(pk_id) from toe_user where province_id=#{provinceId} and city_id is null and area_id is null and delete_flag=1 and user_type between 2 and 4")
    int isProvinceAccountExit(Long provinceId);

    /**
     *  判断该市下是否已经存在账号
     * @param cityId 市id
     * @return 满足条件的总数
     * @author kangyanxiang 
     * @date 2015年12月1日 下午12:26:55
     */
    @Select("select count(pk_id) from toe_user where city_id=#{cityId} and area_id is null and delete_flag=1 and user_type between 2 and 4")
    int isCityAccountExit(Long cityId);

    /**
     * 判断该区县下是否存在账号
     * @param areaId 区县id
     * @return 满足条件的总数
     * @author kangyanxiang 
     * @date 2015年12月1日 下午12:29:32
     */
    @Select("select count(pk_id) from toe_user where area_id=#{areaId} and delete_flag=1 and user_type between 2 and 4")
    int isAreaAccountExit(Long areaId);

    /**
     * 判断集团管理员是否已经存在
     * @return 满足条件的总数
     * @author kangyanxiang 
     * @date 2016年3月10日 下午3:59:20
     */
    @Select("select count(pk_id) from toe_user where user_type=6 and delete_flag=1")
    int isGroupExit();

    /**
     * 根据账号查找客户
     * @param userName 账号
     * @return 客户id
     * @author kangyanxiang 
     * @date 2016年7月5日 下午6:02:19
     */
    @Select("select uc.customer_id from toe_user u left join toe_user_customer uc on u.pk_id=uc.user_id where u.user_name=#{userName} and u.delete_flag=1")
    Long findByUserName(String userName);

    /**
     * 判断手机号和账号是否匹配
     * @param userName 账号
     * @param cellphone 手机号
     * @return count
     * @author kangyanxiang 
     * @date 2016年7月6日 上午9:45:43
     */
    @Select("select pk_id from toe_user where delete_flag=1 and user_name=#{userName} and contact_way=#{cellphone}")
    Long match(@Param("userName") String userName,@Param("cellphone") String cellphone);

    /**
     * 通过账号名称查找账号id和客户id
     * @param userName 账号名称
     * @return 账号
     * @author kangyanxiang 
     * @date 2016年7月7日 上午10:34:41
     */
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "customer.customerId", column = "customer_id", javaType = Long.class, jdbcType = JdbcType.BIGINT)
            })
    @Select("select u.pk_id,uc.customer_id from toe_user u left join toe_user_customer uc on u.pk_id=uc.user_id where u.user_name=#{userName} and u.delete_flag=1")
    User getUserByUserName(String userName);

    /**
     * 通过主键查找账号名称
     * @param id 主键
     * @return 账号名称
     * @author kangyanxiang 
     * @date 2016年7月18日 上午9:23:41
     */
    @Select("select user_name from toe_user where pk_id=#{id}")
    String getNameById(Long id);

    /**
     * 通过用户id获取用户名称以及客户信息
     * @param userId 用户id
     * @return user
     * @author kangyanxiang 
     * @date 2016年7月19日 下午1:54:28
     */
    @Results(value = {
            @Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "customer.customerId", column = "fk_customer_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "customer.cascadeLabel", column = "cascade_label", javaType = String.class, jdbcType = JdbcType.VARCHAR)
            })
    @Select("select u.user_name,su.fk_customer_id,su.cascade_label from toe_user u left join toe_user_customer uc on u.pk_id=uc.user_id left join toe_static_user su on uc.customer_id = su.fk_customer_id where u.pk_id=#{userId} limit 1")
    User getUserInfoById(long userId);

    /**
     * 通过客户ID获取用户名、用户密码
     * @param customerIds 客户id(多个)
     * @return 用户名、用户密码
     * @author kangyanxiang 
     * @date Sep 30, 2016 3:14:16 PM
     */
    @Results(value = {
            @Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "password", column = "password", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "customerId", column = "customer_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            })
    @SelectProvider(type=UserMapperSql.class, method="getUserByCustomerIds")
    List<UserSync> getUserByCustomerIds(@Param("customerIds")Long[] customerIds);

    

   
}