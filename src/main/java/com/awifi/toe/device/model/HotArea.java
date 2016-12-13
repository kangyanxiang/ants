package com.awifi.toe.device.model;

import java.io.Serializable;

/**   
 * @Description:  热点
 * @Title: HotArea.java 
 * @Package com.awifi.toe.device.model 
 * @author kangyanxiang 
 * @date 2016年6月15日 上午8:45:11
 * @version V1.0   
 */
public class HotArea implements Serializable{

    /** 流化实体  */
    private static final long serialVersionUID = -6859636052718538632L;

    /** MAC  */
    private String apMac;
    
    /** 热点名称  */
    private String hotName;
    
    /** 客户名称  */
    private String customerName;
    
    /** 热点id  */
    private String hotareaId;
    
    /** 设备状态  */
    private String deviceState;

    /**
     * 格式化设备状态
     * @return String
     * @author kangyanxiang 
     * @date 2016年6月20日 下午4:55:57
     */
    public String getDeviceStateDsp(){
        if(deviceState.equals("0")){
            return "离线";
        } else {
            return "在线";
        }
    }
    
    public String getApMac() {
        return apMac;
    }

    public void setApMac(String apMac) {
        this.apMac = apMac;
    }

    public String getHotName() {
        return hotName;
    }

    public void setHotName(String hotName) {
        this.hotName = hotName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getHotareaId() {
        return hotareaId;
    }

    public void setHotareaId(String hotareaId) {
        this.hotareaId = hotareaId;
    }

    public String getDeviceState() {
        return deviceState;
    }

    public void setDeviceState(String deviceState) {
        this.deviceState = deviceState;
    }
    
}
