package com.awifi.toe.device.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.awifi.toe.base.util.FormatUtil;

/**   
 * @Description: 热点地图设备实体类 
 * @Title: HeatmapDevice.java 
 * @Package com.awifi.toe.device.model 
 * @author 牛华凤
 * @date 2016年4月21日 上午10:49:03
 * @version V1.0   
 */
public class HeatmapDevice implements Serializable{

    /** 序列化 */
    private static final long serialVersionUID = 3800255947684386954L;

    /** 主键id */
    private Long id;

    /** 设备类型(1、瘦AP  2、胖AP  3、光猫) */
    private Integer type;

    /** mac */
    private String mac;

    /** 创建时间 */
    private String createDate;

    /** 热点id(外键) */
    private Long heatmapId;

    /** 备注 */
    private String remark;

    /** 设备状态(1在线，0离线) */
    private String deviceState;

    /** 客户id(外键) */
    private Long customerId;

    /** 客户层级 */
    private String cascadeLabel;

    /** 关联/解除关联标识(0:未关联     1:解除关联) */
    private String mark;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeviceState() {
        return deviceState;
    }

    public void setDeviceState(String deviceState) {
        this.deviceState = deviceState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 将热点地图设备类型转换成中文
     * @return 皮肤策略类型
     */
    public String getHeatmapDeviceTypeDsp(){
        if(type == null){
            return StringUtils.EMPTY;
        }
        return FormatUtil.formatHeatmapDeviceName(type);
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getHeatmapId() {
        return heatmapId;
    }

    public void setHeatmapId(Long heatmapId) {
        this.heatmapId = heatmapId;
    }

}
