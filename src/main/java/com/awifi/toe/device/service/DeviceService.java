package com.awifi.toe.device.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.awifi.toe.auth.mapper.UserMapper;

/**   
 * @Description:  设备业务层
 * @Title: DeviceService.java 
 * @Package com.awifi.toe.device.service 
 * @author 亢燕翔 
 * @date 2015年11月24日 上午10:36:10
 * @version V1.0   
 */
@Service("deviceService")
public class DeviceService {

    /** 用户持久层  */
    @Resource(name = "userMapper")
    private UserMapper userMapper;


}