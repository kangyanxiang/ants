package com.awifi.toe.log.model;

import java.io.Serializable;

/**   
 * @Description:  
 * @Title: OperationLog.java 
 * @Package com.awifi.toe.log.model 
 * @author ZhouYing 
 * @date 2016年7月7日 下午3:53:27
 * @version V1.0   
 */
public class OperationLog implements Serializable {

    /** 对象序列号 */
    private static final long serialVersionUID = 3578851056864416106L;
    
    /**主键id*/
    private Long id;
    
    /**模块名称*/
    private String moduleName;
    
    /** 服务名称 */
    private String serviceName;
    
    /**请求ip*/
    private String requestIp;
    
    /**请求端口*/
    private String requestPort;
    
    /**内容*/
    private String content;
    
    /**账号id*/
    private Long userId;
    
    /**账号名称*/
    private String userName;
   
    /**客户id*/
    private Long customerId;
    
    /**客户层级*/
    private String cascadeLabel;
    
    /**操作时间*/
    private String createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getRequestPort() {
        return requestPort;
    }

    public void setRequestPort(String requestPort) {
        this.requestPort = requestPort;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCascadeLabel() {
        return cascadeLabel;
    }

    public void setCascadeLabel(String cascadeLabel) {
        this.cascadeLabel = cascadeLabel;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}