package com.awifi.toe.auth.model;

import java.util.ArrayList;
import java.util.List;

/**   
 * @Description:  权限实体类
 * @Title: Permission.java 
 * @Package com.awifi.toe.account.model 
 * @author kangyanxiang 
 * @date 2015年11月25日 上午9:37:13
 * @version V1.0   
 */
public class Permission {

    /**主键*/
    private Long id;
    
    /**权限编号*/
    private String code;
    
    /**权限别名*/
    private String aliasName;
    
    /**父权限id*/
    private Long parentId;
    
    /**排序号*/
    private Integer orderNo;
    
    /**创建日期*/
    private String createDate;
    
    /**修改日期*/
    private String updateDate;
    
    /**权限集合*/
    private List<Permission> childPermissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 下级权限
     * @return 下级权限
     */
    public List<Permission> getChildPermissions() {
        if(childPermissions==null){
            childPermissions=new ArrayList<Permission>();
        }
        return childPermissions;
    }

    public void setChildPermissions(List<Permission> childPermissions) {
        this.childPermissions = childPermissions;
    }
}