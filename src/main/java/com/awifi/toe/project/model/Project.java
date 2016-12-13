package com.awifi.toe.project.model;

import java.io.Serializable;

/**   
 * @Description:  
 * @Title: Project.java 
 * @Package com.awifi.toe.customer.model 
 * @author 亢燕翔 
 * @date 2015年11月24日 下午1:43:32
 * @version V1.0   
 */
public class Project implements Serializable{

    /** 流化实体  */
    private static final long serialVersionUID = 2518397386172417957L;

    /** 项目ID  */
    private String projectId;
    
    /** 项目名称  */
    private String projectName;

    /** 联系人  */
    private String contact;
    
    /** 联系方式  */
    private String contactWay;
    
    /** 登录平台  */
    private String platform;
    
    /** 登录平台ID  */
    private Long platformId;
    
    /** 备注  */
    private String remark;
    
    /**创建时间*/
    private String createDate;
    
    /** 设备总数  */
    private int deviceCount = 0;
    
    /** 客户总数  */
    private int customerCount = 0;
    
    /** 省  */
    private String province;
    
    /** 省ID */
    private Integer provinceId;
    
    /** 市  */
    private String city;
    
    /** 市ID */
    private Integer cityId;
    
    /** 区县  */
    private String county;
    
    /** 区县ID */
    private Integer countyId;
    
    /** 详细地址  */
    private String address;
    
    /** 地区全名  */
    private String locationFullName;
    
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(int deviceCount) {
        this.deviceCount = deviceCount;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCountyId() {
        return countyId;
    }

    public void setCountyId(Integer countyId) {
        this.countyId = countyId;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationFullName() {
        return locationFullName;
    }

    public void setLocationFullName(String locationFullName) {
        this.locationFullName = locationFullName;
    }
}
