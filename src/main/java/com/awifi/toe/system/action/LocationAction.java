package com.awifi.toe.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awifi.toe.auth.service.UserService;
import com.awifi.toe.base.action.BaseAction;
import com.awifi.toe.base.http.HttpRequest;
import com.awifi.toe.base.util.ErrorUtil;
import com.awifi.toe.base.util.MessageUtil;
import com.awifi.toe.system.model.Location;
import com.awifi.toe.system.service.LocationService;

/**   
 * @Description:  地区
 * @Title: Location.java 
 * @Package com.awifi.toe.system.action 
 * @author 亢燕翔 
 * @date 2015年11月20日 上午11:08:25
 * @version V1.0   
 */
@SuppressWarnings("rawtypes")
@Controller
@Scope("prototype")
@RequestMapping("location")
public class LocationAction extends BaseAction{
    
    /** 地区业务层  */
    @Resource(name = "locationService")
    private LocationService locationService;
    
    /** 用户业务层 */
    @Resource(name = "userService")
    private UserService userService;
    
    /**
     * 获取省数据集
     * @return resultMap
     * @author 亢燕翔 
     * @date 2015年11月26日 下午6:17:55
     */
    @RequestMapping(value = "/province")
    @ResponseBody
    public Map province(){
        resultMap = new HashMap<String, Object>();
        try{
            if(request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));
                return resultMap;
            }
            List<Location> locationList = locationService.province();
            resultMap.put("result", "OK"); 
            resultMap.put("message", "");
            resultMap.put("data", locationList);
        } catch(Exception e){
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", e.getMessage());
            saveExceptionLog("", "location", locationService.getClass().toString(), e);
        }
        return resultMap;
    }
    
    /**
     * 获取市数据集
     * @param parentId 父ID
     * @return resultMap
     * @author 亢燕翔 
     * @date 2015年11月27日 上午10:38:56
     */
    @RequestMapping(value = "/city")
    @ResponseBody
    public Map city(String parentId){
        resultMap = new HashMap<String, Object>();
        try{
            if(request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));
                return resultMap; 
            }
            int quetyType = 2;
            List<Location> locationList = locationService.getLocation(parentId,quetyType);
            resultMap.put("result", "OK"); 
            resultMap.put("message", "");
            resultMap.put("data", locationList);
        } catch(Exception e){
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", e.getMessage());
            saveExceptionLog("", "location", locationService.getClass().toString(), e);
        }
        return resultMap;
    }
    
    
    /**
     * 获取区县数据集
     * @param parentId 父ID
     * @return resultMap
     * @author 亢燕翔 
     * @date 2015年11月27日 上午10:39:38
     */
    @RequestMapping(value = "/county")
    @ResponseBody
    public Map county(String parentId){
        resultMap = new HashMap<String, Object>();
        try{
            if(request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));
                return resultMap; 
            }
            int quetyType = 3;
            List<Location> locationList = locationService.getLocation(parentId,quetyType);
            resultMap.put("result", "OK"); 
            resultMap.put("message", "");
            resultMap.put("data", locationList);
        } catch(Exception e){
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", e.getMessage());
            saveExceptionLog("", "location", locationService.getClass().toString(), e);
        }
        return resultMap;
    }
    
}
