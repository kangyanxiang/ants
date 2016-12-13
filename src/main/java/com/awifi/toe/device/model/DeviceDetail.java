package com.awifi.toe.device.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.awifi.toe.base.util.FormatUtil;

/**   
 * @Description:  
 * @Title: DeviceDetail.java 
 * @Package com.awifi.toe.device.model 
 * @author 郭海勇 
 * @date 2015年12月3日 下午4:30:56
 * @version V1.0   
 */
public class DeviceDetail implements Serializable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -898374122950756002L;

    
    /**
     * ap物理地址
     */
    private String apMac;
    
    /**
     * 虚拟设备编号
     */
    private String deviceId;
    
    /** 设备名称  *//*
    private String deviceName;*/
   
    
    /**
     * SSID
     */
    private String ssid;
    
    /**
     * 商户名称
     */
    private String customerName;
    
    /**
     * 设备类型
     */
    private String entityType;
   
    /**设备实体类型数字*/
    private String entityTypeNum;
    
    /**
     * 设备名称
     */
    private String entityName;
    
    /** 热点名称  */
    private String hotareaName;
    
    /**
     * 在线人数
     */
    private String onlineNum;
    
    /**
     * 设备状态
     */
    private String status;
    
    /**
     * 型号
     */
    private String modelText;
    
    /**
     * 地区
     */
    private String address;
    /**
     * 省份名称
     */
    private String provinceText;
    
    /**
     * 城市名称
     */
    private String cityText;
    
    /**
     * 县区名称
     */
    private String countyText;
    
    /**
     * 厂家
     */
    private String corporationText;
    
    /**
     * 固件版本号
     */
    private String fwVersion;
    
    /**
     * 安装详细地址
     */
    private String fixAddr;
    
    /**
     * PING码
     */
    private String pinCode;
    
    /**
     * 备注
     */
    private String remarks;
    
    /**
     * 激活时间
     */
    private String activateDate;
    
    /**
     * 省id
     */
    private String provinceId;
    
    /**
     * 市id
     */
    private String cityId;
    
    /**
     * 县区id
     */
    private String countyId;
    /***
     * 获取设备名称
     * @return 设备名称
     * @author ZhouYing 
     * @date 2016年5月5日 上午10:03:12
     */
    public String getDeviceName(){
        if(entityType == null){
            return StringUtils.EMPTY;
        }
        return FormatUtil.entityTypeToDeviceName(entityType,apMac,ssid,entityName,hotareaName);
    }
    
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getModelText() {
        return modelText;
    }

    public void setModelText(String modelText) {
        this.modelText = modelText;
    }

    public String getCorporationText() {
        return corporationText;
    }

    public void setCorporationText(String corporationText) {
        this.corporationText = corporationText;
    }

    public String getFwVersion() {
        return fwVersion;
    }

    public void setFwVersion(String fwVersion) {
        this.fwVersion = fwVersion;
    }

    public String getFixAddr() {
        return fixAddr;
    }

    public void setFixAddr(String fixAddr) {
        this.fixAddr = fixAddr;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getApMac() {
        return apMac;
    }

    public void setApMac(String apMac) {
        this.apMac = apMac;
    }

    public String getProvinceText() {
        return provinceText;
    }

    public void setProvinceText(String provinceText) {
        this.provinceText = provinceText;
    }

    public String getCityText() {
        return cityText;
    }

    public void setCityText(String cityText) {
        this.cityText = cityText;
    }

    public String getCountyText() {
        return countyText;
    }

    public void setCountyText(String countyText) {
        this.countyText = countyText;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getActivateDate() {
        return activateDate;
    }

    public void setActivateDate(String activateDate) {
        this.activateDate = activateDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
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
