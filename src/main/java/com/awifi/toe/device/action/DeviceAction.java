package com.awifi.toe.device.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.awifi.toe.auth.service.UserService;
import com.awifi.toe.base.action.BaseAction;
import com.awifi.toe.device.service.DeviceService;

/**   
 * @Description:  
 * @Title: DeviceAction.java 
 * @Package com.awifi.toe.device.action 
 * @author 亢燕翔 
 * @date 2015年11月20日 下午2:29:18
 * @version V1.0   
 */
@Controller
@Scope("prototype")
@RequestMapping("device")
public class DeviceAction extends BaseAction{
    
    /** 设备业务层  */
    @Resource(name = "deviceService")
    private DeviceService deviceService;
    
    /** 用户业务层 */
    @Resource(name = "userService")
    private UserService userService;
    

}
