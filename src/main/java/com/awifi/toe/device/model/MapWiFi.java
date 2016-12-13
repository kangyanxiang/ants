package com.awifi.toe.device.model;

import java.io.Serializable;

/**   
 * @Description:  wifi地图实体
 * @Title: MapWifi.java 
 * @Package com.awifi.toe.inerface.client.center.device.model 
 * @author 郭海勇 
 * @date 2016年4月5日 下午2:57:05
 * @version V1.0   
 */
public class MapWiFi implements Serializable{

    /**
     * 序列化
     */
    private static final long serialVersionUID = -78973233479384138L;

    /**
     * 设备名称
     */
    private String deviceName;
    
    /**
     * mac地址
     */
    private String macAddr;
    
    /**
     * 经度
     */
    private String longitude;
    
    /**
     * 维度
     */
    private String latiude;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatiude() {
        return latiude;
    }

    public void setLatiude(String latiude) {
        this.latiude = latiude;
    }
    
    
}
