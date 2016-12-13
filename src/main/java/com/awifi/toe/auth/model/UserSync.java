package com.awifi.toe.auth.model;

import java.io.Serializable;

/**   
 * @Description:  客户同步接口实体类
 * @Title: UserSync.java 
 * @Package com.awifi.toe.auth.model 
 * @author kangyanxiang
 * @date 2016年9月30日 下午5:30:47
 * @version V1.0   
 */
public class UserSync implements Serializable{

    /** 对象序列化 */
    private static final long serialVersionUID = -4221542600119482904L;

    /** 用户名 */
    private String userName;

    /** 密码 */
    private String password;
    
    /** 客户id */
    private Long customerId;
    
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    
}