package com.awifi.toe.log.model;

import java.io.Serializable;

/**   
 * @Description:  
 * @Title: MicShopLog.java 
 * @Package com.awifi.toe.log.model 
 * @author ZhouYing 
 * @date 2016年7月12日 下午2:21:46
 * @version V1.0   
 */
public class MicShopLog implements Serializable {

    private static final long serialVersionUID = -394033914413781453L;
   
    /***/
    private Long id;
    
    /**redis缓存key*/
    private String redisKey;
    
    /**redis缓存value*/
    private String redisValue;
    
    /**微旺铺shopid*/
    private String shopId;
    
    /**微旺铺名称*/
    private String shopName;
    
    /**强制关注*/
    private Integer forceAttention;
    
    /**组件类型*/
    private String componentType;
    
    /**用户手机号*/
    private String userPhone;
    
    /**用户mac*/
    private String userMac;
    
    /**接口参数*/
    private String interfaceParam;
    
    /**接口返回值*/
    private String interfaceReturnValue;
    
    /**执行结果*/
    private String result;
    
    /**客户id*/
    private String fkCustomerId;
   
    /**客户层级*/
    private String cascadeLabel;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }

    public String getRedisValue() {
        return redisValue;
    }

    public void setRedisValue(String redisValue) {
        this.redisValue = redisValue;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getForceAttention() {
        return forceAttention;
    }

    public void setForceAttention(Integer forceAttention) {
        this.forceAttention = forceAttention;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserMac() {
        return userMac;
    }

    public void setUserMac(String userMac) {
        this.userMac = userMac;
    }

    public String getInterfaceParam() {
        return interfaceParam;
    }

    public void setInterfaceParam(String interfaceParam) {
        this.interfaceParam = interfaceParam;
    }

    public String getInterfaceReturnValue() {
        return interfaceReturnValue;
    }

    public void setInterfaceReturnValue(String interfaceReturnValue) {
        this.interfaceReturnValue = interfaceReturnValue;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFkCustomerId() {
        return fkCustomerId;
    }

    public void setFkCustomerId(String fkCustomerId) {
        this.fkCustomerId = fkCustomerId;
    }

    public String getCascadeLabel() {
        return cascadeLabel;
    }

    public void setCascadeLabel(String cascadeLabel) {
        this.cascadeLabel = cascadeLabel;
    }
}