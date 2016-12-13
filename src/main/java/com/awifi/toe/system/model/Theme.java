package com.awifi.toe.system.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.awifi.toe.base.validator.Add;
import com.awifi.toe.base.validator.Edit;

/**   
 * @Description:  皮肤--实体类
 * @Title: Theme.java 
 * @Package com.awifi.toe.system.model 
 * @author 许小满 
 * @date 2016年4月21日 下午3:29:50
 * @version V1.0   
 */
public class Theme implements Serializable{

    /** 序列号  */
    private static final long serialVersionUID = 6052368673059376175L;

    /** 主键id */
    @NotNull(message = "{theme.id.null}", groups = {Edit.class})
    private Long id;
    
    /** 皮肤唯一码 */
    private String code;
    
    /** 皮肤名称 */
    @NotBlank(message = "{theme.name.blank}", groups = {Add.class, Edit.class})
    private String name;
    
    /** 皮肤版本 */
    @NotBlank(message = "{theme.version.blank}", groups = {Add.class, Edit.class})
    private String version;
    
    /** 皮肤缩略图 */
    private String thumb;
    
    /** 皮肤包路径 */
    private String themePath;
    
    /**备注*/
    private String remark;
    
    /**创建时间*/
    private String createDate;
    
    /**更新时间*/
    private String updateDate;
    

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getThemePath() {
        return themePath;
    }

    public void setThemePath(String themePath) {
        this.themePath = themePath;
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

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
    
}