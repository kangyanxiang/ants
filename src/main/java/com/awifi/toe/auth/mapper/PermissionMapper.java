package com.awifi.toe.auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Service;

import com.awifi.toe.auth.model.Permission;

/**   
 * @Description:  权限模型层
 * @Title: PermissionMapper.java 
 * @Package com.awifi.toe.auth.mapper 
 * @author kangyanxiang 
 * @date 2015年8月12日 上午10:04:49
 * @version V1.0   
 */
@Service("permissionMapper")
public interface PermissionMapper {

    /**
     * 获取该登录账号的一级菜单
     * @param accountId 账号id
     * @return 权限集合
     * @author ZhouYing 
     * @date 2015年10月20日 上午9:50:45
     */
    @Select("select p.pk_id,p.code,p.alias_name,p.parent_id,p.order_no from toe_permission p inner join toe_user_permission up on p.pk_id = up.permission_id inner join toe_user u on up.user_id = u.pk_id where u.pk_id=#{accountId} and p.parent_id=0")
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "code", column = "code", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "aliasName", column = "alias_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "parentId", column = "parent_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "orderNo", column = "order_no", javaType = Integer.class, jdbcType = JdbcType.INTEGER)
            })
    List<Permission> getPermissionList(@Param("accountId") Long accountId);

	/**
	 * 获取该一级菜单下的二级菜单（获取该二级菜单下的三级菜单）
	 * @param accountId 账号id
	 * @param permisssionId 权限id
	 * @return 权限
	 * @author ZhouYing 
	 * @date 2015年10月20日 上午10:06:27
	 */
    @Select("select p.pk_id,p.code,p.alias_name,p.parent_id,p.order_no from toe_permission p inner join toe_user_permission up on p.pk_id = up.permission_id inner join toe_user u on up.user_id = u.pk_id where u.pk_id=#{accountId} and p.parent_id=#{permisssionId}")
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "code", column = "code", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "aliasName", column = "alias_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "parentId", column = "parent_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "orderNo", column = "order_no", javaType = Integer.class, jdbcType = JdbcType.INTEGER)
            })
    List<Permission> getPermissionTwoList(@Param("accountId") Long accountId, @Param("permisssionId") Long permisssionId);

	/**
	 * 移除旧的账号权限关系
	 * @param accountId 账号id
	 * @author ZhouYing 
	 * @date 2015年10月20日 下午1:56:30
	 */
    @Delete("delete from toe_user_permission where user_id=#{accountId}")
    void removeOldPermission(@Param("accountId") Long accountId);
	
	/**
	 * 赋予新的权限
	 * @param accountId 账号id
	 * @param permissionid 权限id
	 * @author ZhouYing 
	 * @date 2015年10月20日 下午2:01:06
	 */
    @Insert("insert into toe_user_permission(permission_id,user_id) values(#{permissionid},#{accountId})")
    void addPermission(@Param("accountId") Long accountId, @Param("permissionid") Long permissionid);
	
	
	/**
	 * 通过用户id、权限编号 查询对应的权限记录数
	 * @param userId 用户id
	 * @param code 权限编号
	 * @return 总数
	 * @author kangyanxiang  
	 * @date 2015年8月12日 上午10:24:33
	 */
    @Select("select count(p.pk_id) "
            + "from toe_permission p inner join toe_user_permission up on p.pk_id=up.permission_id "
            + "inner join toe_user u on up.user_id=u.pk_id " + "where u.pk_id=#{userId} and p.code=#{code} ")
    int findNumByUserAndCode(@Param("userId") Long userId, @Param("code") String code);

	/**
	 * 通过账号查找已有权限的id集合
	 * @param accountId 账号id
	 * @return 权限id集合
	 * @author ZhouYing 
	 * @date 2015年10月21日 上午10:24:52
	 */
    @Select("select permission_id from toe_user_permission where user_id=#{accountId}")
    List<Long> getPermissionIdList(@Param("accountId") Long accountId);

	/**
	 * 通过账号查找已有权限的code集合
	 * @param userId 用户id
	 * @return 编码集合
	 * @author kangyanxiang
	 * @date 2015年10月23日 下午7:17:52
	 */
    @Select("select p.code from toe_user_permission up inner join toe_permission p on up.permission_id=p.pk_id where up.user_id=#{userId}")
    List<String> findCodeByUserId(Long userId);

    /**
     * 删除权限
     * @param old 要删除的权限
     * @param ids 账号ids
     * @author ZhouYing 
     * @date 2016年5月31日 下午5:41:18
     */
    @Delete("delete from toe_user_permission where user_id in (${ids}) and permission_id in (${old})")
    void deletePermission(@Param("old") String old,@Param("ids") String ids);

    /**
     * 删除权限
     * @param o 要删除的权限
     * @param i 账号id
     * @author ZhouYing 
     * @date 2016年6月3日 下午4:29:25
     */
    @Delete("delete from toe_user_permission where user_id=#{i} and permission_id=#{o}")
    void delete(@Param("o") String o,@Param("i") Long i);

    /**
     * 判断客户是否有权限
     * @param customerId 客户id
     * @param permissionId 权限id
     * @return 总数
     * @author ZhouYing 
     * @date 2016年7月13日 上午9:46:23
     */
    @Select("select count(p.pk_id) "
            + "from toe_permission p inner join toe_user_permission up on p.pk_id=up.permission_id inner join toe_user_customer uc on up.user_id=uc.user_id"
            + " where uc.customer_id=#{customerId} and p.pk_id=#{permissionId} ")
    int findByCustomerAndId(@Param("customerId") String customerId,@Param("permissionId") String permissionId);
}