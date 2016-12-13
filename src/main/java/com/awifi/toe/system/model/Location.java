package com.awifi.toe.system.model;

import java.io.Serializable;

/**   
 * @Description:  地区实体
 * @Title: Location.java 
 * @Package com.awifi.toe.system.model 
 * @author 亢燕翔 
 * @date 2015年11月23日 下午4:27:10
 * @version V1.0   
 */
public class Location implements Serializable{

    /** 流化实体  */
    private static final long serialVersionUID = 1797170336021429362L;

    /** 地区Id */
    private String locationId;
    
    /** 地区名称  */
    private String locationName;
    
    /** 父id */
    private String parentId;
    
    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
}
