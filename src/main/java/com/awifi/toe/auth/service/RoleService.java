package com.awifi.toe.auth.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.awifi.toe.auth.mapper.RoleMapper;
import com.awifi.toe.base.service.BaseService;
import com.awifi.toe.base.util.FormatUtil;

/**   
 * @Description:  角色业务层
 * @Title: RoleService.java 
 * @Package com.awifi.toe.auth.service 
 * @author kangyanxiang
 * @date 2015年6月29日 下午3:49:57
 * @version V1.0   
 */
@Service("roleService")
public class RoleService extends BaseService{

    /** 角色mapper */
    @Resource(name = "roleMapper")
	private RoleMapper roleMapper;
	
    /**
     * 授权
     * @param userId 用户id
     * @param userType 用户角色
     * @author kangyanxiang
     * @date 2015年6月29日 下午4:00:15
     */
    public void authorize(Long userId, Integer userType){
        String roleName = FormatUtil.userTypeToRoleNameToE(userType);//角色名称
        if(StringUtils.isBlank(roleName)){
            return;
        }
        Long roleId = roleMapper.findRoleIdByName(roleName);
        roleMapper.insertUserRole(userId, roleId);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 通过用户id 查询角色信息
    * @param userId 用户id
    * @return 用户 集合
    * @author kangyanxiang
    * @date 2015年6月29日 下午3:51:28
    */
    public Collection<SimpleGrantedAuthority> findByUserId(Long userId){
        return roleMapper.findByUserId(userId);
    }
    
    /**
     * 移除授权
     * @param userId 用户id
     * @author kangyanxiang
     * @date 2015年6月29日 下午4:32:47
     */
    public void deauthorize(Long userId){
        roleMapper.deleteUserRoleByUserId(userId);
    }
	
}