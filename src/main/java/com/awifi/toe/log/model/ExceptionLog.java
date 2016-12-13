package com.awifi.toe.log.model;

import java.io.Serializable;

/**
 * 异常实体类
 * 
 * @author niuhuafeng
 * 
 */
public class ExceptionLog implements Serializable {

    /** 对象序列化 */
    private static final long serialVersionUID = -1799226952073443511L;

    /** 主键id */
    private Long id;

    /** 错误代码 */
    private String errorCode;

    /** 模块名称 */
    private String moduleName;

    /** 服务名称 */
    private String serviceName;

    /** 参数 */
    private String parameter;

    /** 错误消息 */
    private String errorMessage;
    
    /** 接口地址 */
    private String interfaceUrl;
    
    /** 接口参数 */
    private String interfaceParam;
    
    /** 接口返回值 */
    private String interfaceReturnValue;

    /** 创建时间 */
    private String createDatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
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

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
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
}