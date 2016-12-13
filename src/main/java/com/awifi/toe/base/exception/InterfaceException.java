package com.awifi.toe.base.exception;

/**
 * @Description: 接口异常
 * @Title: InterfaceException.java
 * @Package com.awifi.toe.base.exception
 * @author kangyanxiang
 * @date 2015年7月3日 下午2:56:38
 * @version V1.0
 */
public class InterfaceException extends Exception {

    /** 序列号 */
    private static final long serialVersionUID = 8662044174443879224L;
    
    /** 接口地址 */
    private String interfaceUrl;
    
    /** 接口参数 */
    private String interfaceParam;
    
    /** 接口返回值 */
    private String interfaceReturnValue;
    

    public InterfaceException() {
        super();
    }

    public InterfaceException(Throwable cause) {
        super(cause);
    }

    public InterfaceException(String message) {
        super(message);
    }

    public InterfaceException(String message, Throwable e) {
        super(message, e);
    }
    
    public InterfaceException(String message, String interfaceUrl, String interfaceParam) {
        super(message);
        this.interfaceUrl = interfaceUrl;
        this.interfaceParam = interfaceParam;
    }
    
    public InterfaceException(String message, String interfaceUrl, String interfaceParam, String interfaceReturnValue) {
        super(message);
        this.interfaceUrl = interfaceUrl;
        this.interfaceParam = interfaceParam;
        this.interfaceReturnValue = interfaceReturnValue;
    }
    
    public InterfaceException(String message, Throwable e, String interfaceUrl, String interfaceParam) {
        super(message, e);
        this.interfaceUrl = interfaceUrl;
        this.interfaceParam = interfaceParam;
    }
    
    public InterfaceException(String message, Throwable e, String interfaceUrl, String interfaceParam, String interfaceReturnValue) {
        super(message, e);
        this.interfaceUrl = interfaceUrl;
        this.interfaceParam = interfaceParam;
        this.interfaceReturnValue = interfaceReturnValue;
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
