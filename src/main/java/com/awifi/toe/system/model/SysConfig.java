package com.awifi.toe.system.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.awifi.toe.base.validator.Add;
import com.awifi.toe.base.validator.Edit;

/**
 * @Description:系统配置实体类
 * @Title: SysConfig.java
 * @Package com.awifi.toe.system.model
 * @author ZhouYing
 * @date 2015年6月26日 下午2:05:47
 * @version V1.0
 */
public class SysConfig {

    /** 主键id */
    @NotNull(message = "{sysconfig.id.null}", groups = { Edit.class })
    private Long id;

    /** 别名 */
    @NotBlank(message = "{sysconfig.aliasName.blank}", groups = { Add.class, Edit.class })
    private String aliasName;

    /** 参数键 */
    @NotBlank(message = "{sysconfig.paramKey.blank}", groups = { Add.class, Edit.class })
    private String paramKey;

    /** 参数值 */
    @NotBlank(message = "{sysconfig.paramValue.blank}", groups = { Add.class, Edit.class })
    private String paramValue;

    /** 备注 */
    private String remark;

    /** 排序号 */
    @NotNull(message = "{sysconfig.orderNo.null}", groups = { Add.class, Edit.class })
    private Integer orderNo;

    /** 创建时间 */
    private String createDatetime;

    /** 修改时间 */
    private String updateDatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}