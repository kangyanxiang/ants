package com.awifi.toe.auth.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.awifi.toe.account.service.AccountService;
import com.awifi.toe.auth.mapper.UserMapper;
import com.awifi.toe.auth.model.User;
import com.awifi.toe.base.service.BaseService;

/**   
 * @Description:  用户业务层
 * @Title: UserService.java 
 * @Package com.awifi.toe.auth.service 
 * @author kangyanxiang
 * @date 2015年4月30日 下午3:19:43
 * @version V1.0   
 */
@Service("userService")
public class UserService extends BaseService{

	/** 用户dao */
    @Resource(name = "userMapper")
	private UserMapper userMapper;
    
    /**账号业务层*/
    @Resource(name="accountService")
    private AccountService accountService;
	
	/**
     * 通过用户名 查询用户信息
     * @param userName 用户名
     * @return 用户
     * @author kangyanxiang
     * @date 2015年5月8日 上午11:08:02
     */
    public User findUserByUserName(String userName){
        User user = userMapper.findUserByUserName(userName);
        if(user == null){
            logger.error("错误：通过用户名["+userName+"] 未找到对应的用户信息！");
            return null;
        }
        return user;
    }
    
    /**
     * 用户详细信息
     * @param id 用户表主键id
     * @return 用户对象
     * @author kangyanxiang
     * @throws Exception 异常
     * @date 2015年11月26日 下午3:48:22
     */
    public User getInfoById(Long id) throws Exception{
        User user = userMapper.getInfoByIdWithCustomer(id);
        String location=null;//accountService.getLocationInfoByUser(user);
        logger.debug("返回地区：" + location);
        user.setLocation(location);
        return user;
    }
    
    /**
     * 用户详细信息
     * @param userName 用户名
     * @return 用户对象
     * @author kangyanxiang
     * @throws Exception  异常
     * @date 2015年11月26日 下午3:48:22
     */
    public User getInfoByName(String userName) throws Exception{
        User user = userMapper.getInfoByName(userName);
        String location=null;//accountService.getLocationInfoByUser(user);
        logger.debug("返回地区：" + location);
        user.setLocation(location);
        return user;
    }
    
    /**
     * 通过主键ID获取客户ID
     * @param id 主键ID
     * @return null
     * @author kangyanxiang
     * @date 2015年11月30日 上午10:44:36
     */
    public Long getCustomerIdById(Long id) {
        return userMapper.getCustomerIdById(id);
    }
    
    /**
     * 通过用户id获取用户名称以及客户信息
     * @param userId 用户id
     * @return user
     * @author kangyanxiang 
     * @date 2016年7月19日 下午1:53:24
     */
    public User getUserInfoById(long userId) {
        return userMapper.getUserInfoById(userId);
    }
    
}