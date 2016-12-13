package com.awifi.toe.auth.mapper;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.awifi.toe.auth.model.Role;

/**   
 * @Description:  角色
 * @Title: RoleMapper.java 
 * @Package com.awifi.toe.auth.mapper 
 * @author kangyanxiang 
 * @date 2015年5月8日 上午11:02:05
 * @version V1.0   
 */
@Service("roleMapper")
public interface RoleMapper {

    /**
     * 获取全部电信侧角色 包含集团管理员
     * @return 全部电信侧角色 包含集团管理员
     * @author kangyanxiang 
     * @date 2016年3月2日 上午10:23:42
     */
    @Select("select pk_id,alias_name from toe_role where pk_id>1")
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "aliasName", column = "alias_name", javaType = String.class, jdbcType = JdbcType.VARCHAR)
            })
    List<Role> getAllRole();
    
    /**
     * 集团管理员登陆角色下拉框
     * @return 角色
     * @author kangyanxiang 
     * @date 2016年3月2日 下午5:30:01
     */
    @Select("select pk_id,alias_name from toe_role where pk_id>=2")
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "aliasName", column = "alias_name", javaType = String.class, jdbcType = JdbcType.VARCHAR)
            })
    List<Role> getAll();
    
    /**
     * 获取下级角色列表
     * @param roleId 角色id
     * @return 角色列表
     * @author kangyanxiang 
     * @date 2015年11月24日 下午6:06:05
     */
    @Select("select pk_id,alias_name from toe_role where pk_id>=#{roleId} and pk_id != 6")
    @Results(value = {
            @Result(property = "id", column = "pk_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "aliasName", column = "alias_name", javaType = String.class, jdbcType = JdbcType.VARCHAR)
            })
    List<Role> getRoleList(Long roleId);
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	/**
	 * 通过用户id 查询角色信息
	 * @param userId 用户id
	 * @return 用户 集合
	 * @author kangyanxiang  
	 * @date 2015年5月8日 上午11:08:02
	 */
    @Select("select r.role_name from toe_role r inner join toe_user_role ur on r.pk_id= ur.role_id where ur.user_id=#{userId}")
    @ConstructorArgs(value={@Arg(column = "role_name",javaType = String.class , jdbcType = JdbcType.VARCHAR)})
    Collection<SimpleGrantedAuthority> findByUserId(Long userId);

	/**
	 * 通过角色名称查询角色id
	 * @param roleName 角色名称
	 * @return 角色id
	 * @author kangyanxiang  
	 * @date 2015年6月29日 下午4:10:16
	 */
    @Select("select pk_id from toe_role where role_name=#{roleName}")
    Long findRoleIdByName(String roleName);
	
	/**
	 * 新增 用户-角色 记录
	 * @param userId 用户id
	 * @param roleId 角色id
	 * @return 记录数
	 * @author kangyanxiang  
	 * @date 2015年6月29日 下午4:14:12
	 */
    @Insert("insert into toe_user_role(user_id,role_id) values(#{userId},#{roleId})")
    int insertUserRole(@Param("userId")Long userId, @Param("roleId")Long roleId);
	
	/**
	 * 删除 用户-角色 记录
	 * @param userId 用户id
	 * @return 记录数
	 * @author kangyanxiang  
	 * @date 2015年6月29日 下午6:49:14
	 */
    @Delete("delete from toe_user_role where user_id=#{userId}")
    int deleteUserRoleByUserId(Long userId);

    
    
    

    
}