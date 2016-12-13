package com.awifi.toe.auth.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.awifi.toe.base.util.FormatUtil;

/**
 * @Description: 用户实体类
 * @Title: User.java
 * @Package com.awifi.toe.auth.model
 * @author 许小满
 * @date 2015年5月8日 上午11:16:06
 * @version V1.0
 */
public class User implements Serializable {

    /** 对象序列化id */
    private static final long serialVersionUID = -1799226952073443511L;

    /** 主键id */
    private Long id;

    /** 用户名 */
    private String userName;

    /** 密码 */
    private String password;
    
    /**用户类型*/
    private Integer userType;
    
    /** 父ID  */
    private Long parentId;
    
    /** 父名称  */
    private String parentName;
    
    /**省*/
    private Long provinceId;
    
    /**市*/
    private Long cityId;
   
    /**区县*/
    private Long areaId;
    
    /**联系人*/
    private String contactPerson;
    
    /**联系方式*/
    private String contactWay;
    
    /**备注*/
    private String remark;
    
    /**删除标志*/
    private Integer deleteFlag;
   
    /**创建时间*/
    private String createDate;
    
    /**更新时间*/
    private String updateDate;
    
    /**项目id*/
    private Long fkProjectId;
    
    /**地区*/
    private String location;
    
    /**部门*/
    private String deptName;
    
    /** 用户层级  */
    private String cascadeLabel;
    
    /** 用户等级  */
    private int cascadeLevel;
    
    /** 项目名称  */
    private String projectName;
    
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    public Long getFkProjectId() {
        return fkProjectId;
    }

    public void setFkProjectId(Long fkProjectId) {
        this.fkProjectId = fkProjectId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCascadeLabel() {
        return cascadeLabel;
    }

    public void setCascadeLabel(String cascadeLabel) {
        this.cascadeLabel = cascadeLabel;
    }

    public int getCascadeLevel() {
        return cascadeLevel;
    }

    public void setCascadeLevel(int cascadeLevel) {
        this.cascadeLevel = cascadeLevel;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}