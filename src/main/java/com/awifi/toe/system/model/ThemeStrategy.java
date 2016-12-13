package com.awifi.toe.system.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;

import com.awifi.toe.base.util.FormatUtil;
import com.awifi.toe.base.validator.Add;
import com.awifi.toe.base.validator.Edit;

/**  
 * @Description: 皮肤策略实体类 
 * @Title: ThemeStrategy.java 
 * @Package com.awifi.toe.system.model 
 * @author 牛华凤
 * @date 2016年4月21日 上午10:17:09
 * @version V1.0   
 */
public class ThemeStrategy implements Serializable{

    /** 流化实体  */
    private static final long serialVersionUID = 1797170336021429362L;

    /** 主键id */
    @NotNull(message = "{themeStrategy.id.null}", groups = {Edit.class})
    private Long id;

    /** 类别(1、域名       2、编号) */
    private Integer themeType;

    /** 内容(域名或编号) */
    @NotNull(message = "{themeStrategy.content.null}", groups = {Edit.class})
    private String content;
    
    /**备注*/
    private String remark;

    /** 客户id(外键) */
    @NotNull(message = "{themeStrategy.customer.null}", groups = {Add.class, Edit.class})
    private Long customerId;

    /** 客户名称 */
    private String customerName;

    /** 客户层级 */
    private String cascadeLabel;
    
    /** 皮肤id(外键) */
    private Long themeId;

    /** 皮肤名称 */
    private String themeName;
    
    /** 创建时间 */
    private String createDate;
    
    /** 更新时间 */
    private String updateDate;

    /** 皮肤表 */
    private Theme theme;

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 将皮肤策略类型转化成中文
     * @return 皮肤策略类型
     */
    public String getThemeTypeDsp(){
        if(themeType == null){
            return StringUtils.EMPTY;
        }
        return FormatUtil.formatThemeType(themeType);
    }


    public Integer getThemeType() {
        return themeType;
    }

    public void setThemeType(Integer themeType) {
        this.themeType = themeType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    
    /**
     * 获取客户等级
     * @return 客户等级
     * @author 许小满  
     * @date 2016年5月11日 下午7:07:01
     */
    public Integer getCascadeLevel() {
        if(StringUtils.isBlank(cascadeLabel)){
            return null;
        }
        return cascadeLabel.split("-").length - 1;
    }
    
    public Long getThemeId() {
        return themeId;
    }

    public void setThemeId(Long themeId) {
        this.themeId = themeId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
}
