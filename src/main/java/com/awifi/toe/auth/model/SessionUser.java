package com.awifi.toe.auth.model;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.awifi.toe.base.util.FormatUtil;

/**   
 * @Description:  session中的用户信息
 * @Title: SessionUser.java 
 * @Package com.awifi.toe.auth.model 
 * @author kangyanxiang
 * @date 2015年12月11日 下午1:42:00
 * @version V1.0   
 */
public class SessionUser extends User {

    /** 对象序列号 */
    private static final long serialVersionUID = 8476249589179097817L;
    
    /** 用户表主键id */
    private Long id;
    
    /** 用户名 */
    private String userName;
    
    /**用户类型*/
    private Integer userType;
    
    /** 客户id[外键]，仅用于行业平台 */
    private Long customerId;
    
    /** 客户层级,仅用于行业平台*/
    private String cascadeLabel;
    
    /** 构造函数 */
    public SessionUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
    
    /**
     * 将用户类型转化成中文
     * @return 用户类型
     */
    public String getUserTypeDsp(){
        if(userType == null){
            return StringUtils.EMPTY;
        }
        return FormatUtil.userTypeToRoleDspName(userType);
    }
}