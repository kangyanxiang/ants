package com.awifi.toe.device.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.awifi.toe.base.util.FormatUtil;

/**   
 * @Description:  
 * @Title: Device.java 
 * @Package com.awifi.toe.device.model 
 * @author 郭海勇 
 * @date 2015年12月3日 上午11:03:37
 * @version V1.0   
 */
@SuppressWarnings("unused")
public class Device implements Serializable{
    
    /** 流化实体 */
    private static final long serialVersionUID = 705471816479765579L;

    /** 客户Id */
    private String customerId;
    
    /** 设备名称  */
    private String deviceName;
    
    /** 设备实体名称  */
    private String entityName;
    
    /** 热点名称  */
    private String hotareaName;
    
    /** 客户名称  */
    private String customerName;
    
    /** ap物理地址 */
    private String apMac;
    
    /** 热点 */
    private String ssid;
    
    /** 设备虚拟编号 */
    private String deviceId;
    
    /** 设备实体类型 */
    private String entityType;
    
    /**设备实体类型数字*/
    private String entityTypeNum;
    
    /** 地区 */
    private String address;
    
    /**
     * 激活时间
     */
    private String activeTime;
    
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getApMac() {
        return apMac;
    }

    public void setApMac(String apMac) {
        this.apMac = apMac;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 格式化设备名称
     * @return 设备名称
     * @author kangyanxiang 
     * @date 2016年5月16日 下午4:15:16
     */
    public String getDeviceName() {
        if(entityType == null){
            return StringUtils.EMPTY;
        }
        return FormatUtil.entityTypeToDeviceName(entityType,apMac,ssid,entityName,hotareaName);
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public String getEntityTypeNum() {
        return entityTypeNum;
    }

    public void setEntityTypeNum(String entityTypeNum) {
        this.entityTypeNum = entityTypeNum;
    }

    public String getHotareaName() {
        return hotareaName;
    }

    public void setHotareaName(String hotareaName) {
        this.hotareaName = hotareaName;
    }
    
}
