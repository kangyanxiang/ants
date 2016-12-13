package com.awifi.toe.device.model;

import java.io.Serializable;

/**   
 * @Description:  设备类型统计 实体类
 * @Title: DeviceTypeStatistics.java 
 * @Package com.awifi.toe.device.model 
 * @author 郭海勇 
 * @date 2016年4月11日 下午4:11:26
 * @version V1.0   
 */
public class DeviceTypeStatistics implements Serializable{
    
    /**
     * 序列化
     */
    private static final long serialVersionUID = 3800255947684386954L;

    /**
     * ac数量
     */
    private String ac;
    
    /**
     * bas数量
     */
    private String bas;
    
    /**
     * ac下的Ap数量
     */
    private String acAp;
    
    /**
     * 热点下的Ap数量
     */
    private String hotAp;
    
    /**
     * 胖Ap数量
     */
    private String fatAp;
    
    /**
     * 光猫数量
     */
    private String lightCat;
    
    /**
     * 小计
     */
    private String total;

    /**
     * 地区id
     */
    private String locationId;
    
    /**
     * 地区名称
     */
    private String locationName;
    
    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getBas() {
        return bas;
    }

    public void setBas(String bas) {
        this.bas = bas;
    }

    public String getAcAp() {
        return acAp;
    }

    public void setAcAp(String acAp) {
        this.acAp = acAp;
    }

    public String getHotAp() {
        return hotAp;
    }

    public void setHotAp(String hotAp) {
        this.hotAp = hotAp;
    }

    public String getFatAp() {
        return fatAp;
    }

    public void setFatAp(String fatAp) {
        this.fatAp = fatAp;
    }

    public String getLightCat() {
        return lightCat;
    }

    public void setLightCat(String lightCat) {
        this.lightCat = lightCat;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    
}
