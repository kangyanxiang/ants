package com.awifi.toe.device.model;

import java.io.Serializable;

/**   
 * @Description:  
 * @Title: Device.java 
 * @Package com.awifi.toe.device.model 
 * @author 郭海勇
 * @date 2015年11月30日 上午11:00:51
 * @version V1.0   
 */
/**
 * 
 * @author 亢燕翔
 * 2015年12月4日 下午2:22:38
 */
public class DeviceImport implements Serializable{

    /** 序列化  */
    private static final long serialVersionUID = 1L;

   
    /**设备类型
     * FAT：胖ap、  NAS：AC/BAS 、  FIT：瘦ap
     */
    private String devType;
    
    /** SSID  */
    private String ssid;
    
    /** MAC */
    private String mac;
    
    /** 设备名称  */
    private String deviceName;
    
    /** 商户编号  */
    private String merchantId;
    
    /** 工程id  */
    private String projectId;

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

   
    
}
