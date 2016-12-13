package com.awifi.toe.base.util.rules;

public abstract class Rule {
    
    /** 值  */
    protected String value;
    /** 消息 */
    protected String message;
    /**  */
    public static final String charset = "UTF-8";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * 校验
     * @return true、false
     * @throws Exception 异常
     */
    public abstract boolean valid() throws Exception;
}