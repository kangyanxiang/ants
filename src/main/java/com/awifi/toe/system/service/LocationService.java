package com.awifi.toe.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.awifi.toe.system.mapper.LocationMapper;
import com.awifi.toe.system.model.Location;

/**   
 * @Description:  地区业务层
 * @Title: LocationService.java 
 * @Package com.awifi.toe.system.service 
 * @author 亢燕翔 
 * @date 2015年11月23日 下午4:58:31
 * @version V1.0   
 */
@Service("locationService")
public class LocationService {

    /** 地区持久层  */
    @Resource(name = "locationMapper")
    private LocationMapper locationMapper;
    
    
    /**
     * 获取省数据集
     * @return list
     * @author 亢燕翔 
     * @throws Exception 
     * @date 2015年11月26日 下午6:18:35
     */
    public List<Location> province() throws Exception {
        return locationMapper.getProvince();
    }

    /**
     * 获取市/区县数据集
     * @param parentId 父ID
     * @param quetyType 查询类型
     * @return list
     * @throws Exception 异常
     * @author 亢燕翔 
     * @date 2015年11月27日 上午10:41:49
     */
    public List<Location> getLocation(String parentId, int quetyType) {
        return locationMapper.getLocation(parentId,quetyType);
    }
}
