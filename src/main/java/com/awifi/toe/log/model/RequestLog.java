package com.awifi.toe.log.model;

import java.io.Serializable;

/**   
 * @Description:  
 * @Title: RequestLog.java 
 * @Package com.awifi.toe.log.model 
 * @author 苏晨斌 
 * @date 2016年4月21日 上午10:43:59
 * @version V1.0   
 */
public class RequestLog implements Serializable{
    
    /** 对象序列化 */
    private static final long serialVersionUID = -1799226952073443511L;
    
    /** 主键id */
    private Long id;
    
    /** 模块名称 */
    private String moduleName;
    
    /** 请求类型 */
    private String requestType;
    
    /** 请求IP */
    private String requestIp;
    
    /** 请求源端口 */
    private String requestPort;
    
    /** 请求源URL */
    private String requestUrl;
    
    /** 请求源参数 */
    private String requestParam;
    
    /** 请求返回值 */
    private String requestReturnValue;
    
    /** 创建时间 */
    private String createDatetime;

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

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
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

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public String getRequestReturnValue() {
        return requestReturnValue;
    }

    public void setRequestReturnValue(String requestReturnValue) {
        this.requestReturnValue = requestReturnValue;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }
    
}
