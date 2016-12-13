package com.awifi.toe.auth.security;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.awifi.toe.auth.model.SessionUser;
import com.awifi.toe.auth.model.User;
import com.awifi.toe.auth.service.RoleService;
import com.awifi.toe.auth.service.UserService;
import com.awifi.toe.base.service.BaseService;

/**   
 * @Description:  
 * @Title: CustomUserDetailsService.java 
 * @Package com.awifi.toe.auth.service 
 * @author kangyanxiang
 * @date 2015年4月30日 下午4:37:45
 * @version V1.0   
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService extends BaseService implements UserDetailsService{

    /** 用户业务类 */
    @Resource(name = "userService")
	private UserService userService;
	
	/** 角色业务类 */
    @Resource(name = "roleService")
	private RoleService roleService;
	
	
    /**
     * 通过用户名获取用户信息
     * @param username 用户名
     * @return 用户对象
     * @throws UsernameNotFoundException 用户名未找到异常
     * @throws DataAccessException 数据访问异常
     * @author kangyanxiang
     * @date 2015年11月18日 下午1:27:12
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        logger.debug("提示：开始调用 --> CustomUserDetailsService.loadUserByUsername() ......");
        User user = userService.findUserByUserName(username);
        if(user == null){
            logger.debug("提示：通过用户名["+ username +"] 未找到对应的用户信息！");
            throw new UsernameNotFoundException("Error in retrieving user");
        }
        SessionUser sessionUser = new SessionUser(username, user.getPassword(),roleService.findByUserId(user.getId()));
        /** 完善用户信息 */
        sessionUser.setId(user.getId());//用户表主键id
        sessionUser.setUserName(username);//用户名
        Integer userType = user.getUserType();//用户类型
        sessionUser.setUserType(userType);//用户类型
        return sessionUser;
    }
    
}