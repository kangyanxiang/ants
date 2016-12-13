package com.awifi.toe.system.model;

import java.io.Serializable;

/**   
 * @Description:  行业实体
 * @Title: Industry.java 
 * @Package com.awifi.toe.system.model 
 * @author 亢燕翔 
 * @date 2015年11月23日 下午4:26:59
 * @version V1.0   
 */
public class Industry implements Serializable{

    /** 流化实体  */
    private static final long serialVersionUID = 793752677574854331L;
    
    /** 行业ID  */
    private String industryId;
    
    /** 行业名称  */
    private String industryName;

    /** 行业级别  */
    private String industryLevel;
    
    
    public String getIndustryLevel() {
        return industryLevel;
    }

    public void setIndustryLevel(String industryLevel) {
        this.industryLevel = industryLevel;
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

}
