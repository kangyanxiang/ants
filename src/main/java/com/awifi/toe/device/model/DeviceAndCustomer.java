package com.awifi.toe.device.model;
/**   
 * @Description:  设备数和用户数实体类
 * @Title: DeviceAndCustomer.java 
 * @Package com.awifi.toe.device.model 
 * @author 郭海勇 
 * @date 2016年3月24日 上午11:05:13
 * @version V1.0   
 */
public class DeviceAndCustomer {
     
    /**
     * 项目id
     */
    private String projectId;
    
    /**
     * 用户数
     */
    private Long customers;
    
    /**
     * 设备数
     */
    private Long devices;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Long getCustomers() {
        return customers;
    }

    public void setCustomers(Long customers) {
        this.customers = customers;
    }

    public Long getDevices() {
        return devices;
    }

    public void setDevices(Long devices) {
        this.devices = devices;
    }
    
    
    
}
