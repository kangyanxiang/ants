package com.awifi.toe.device.model;

import java.io.Serializable;

/**   
 * @Description:  设备监控实体
 * @Title: DeviceMonitor.java 
 * @Package com.awifi.toe.device.model 
 * @author 郭海勇 
 * @date 2016年4月5日 下午4:33:29
 * @version V1.0   
 */
public class DeviceMonitor implements Serializable{

    /**
     * 序列化
     */
    private static final long serialVersionUID = -3790422292685076795L;

    /**
     * 设备mac地址
     */
    private String mac;
    
    /**
     * SSID
     */
    private String ssid;
    
    /**
     * ac名称
     */
    private String acName;
    
    /**
     * 设备类型
     */
    private String entityType;
    
    /**设备实体类型数字*/
    private String entityTypeNum;
    
    /**
     * 在线人数
     */
    private String onlineNum;
    
    /**
     * 状态
     */
    private String status;
    
    public String getMac() {
        return mac;
    }
    public void setMac(String mac) {
        this.mac = mac;
    }
    public String getSsid() {
        return ssid;
    }
    public void setSsid(String ssid) {
        this.ssid = ssid;
    }
    public String getAcName() {
        return acName;
    }
    public void setAcName(String acName) {
        this.acName = acName;
    }
    public String getEntityType() {
        return entityType;
    }
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
    public String getOnlineNum() {
        return onlineNum;
    }
    public void setOnlineNum(String onlineNum) {
        this.onlineNum = onlineNum;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getEntityTypeNum() {
        return entityTypeNum;
    }
    public void setEntityTypeNum(String entityTypeNum) {
        this.entityTypeNum = entityTypeNum;
    }
    
    
}
