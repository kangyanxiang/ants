package com.awifi.toe.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.awifi.toe.base.model.Page;
import com.awifi.toe.system.mapper.SysConfigMapper;
import com.awifi.toe.system.model.SysConfig;
import com.awifi.toe.system.util.SysConfigUtil;

/**   
 * @Description: 系统参数业务层 
 * @Title: SysConfigService.java 
 * @Package com.awifi.toe.system.service 
 * @author ZhouYing
 * @date 2015年6月26日 下午2:13:36
 * @version V1.0   
 */
@Service("sysConfigService")
public class SysConfigService {

    /** */
    @Resource(name = "sysConfigMapper")
	private SysConfigMapper sysConfigMapper;

	/**
	 * 增加系统参数
	 * @param sysconfig 系统参数
	 * @author ZhouYing
	 * @date 2015年6月26日 下午4:35:08
	 */
    public void setConfig(SysConfig sysconfig) {
        sysConfigMapper.insertSysConfig(sysconfig);
    }
 
	/**
	 * 编辑系统参数
	 * @param sysconfig 系统参数
	 * @author ZhouYing 
	 * @date 2015年6月26日 下午4:36:08
	 */
    public void updateSysConfig(SysConfig sysconfig) {
//        RedisUtil.del(RedisConstants.SYSTEM_CONFIG+sysconfig.getParamKey());
        sysConfigMapper.updateSysConfig(sysconfig);
    }

    /**
     * 判断是否已经存在参数键
     * @param id 主键
     * @param paramKey 参数键
     * @return true为存在 false为不存在
     * @author ZhouYing 
     * @date 2015年6月30日 下午2:24:02
     */
    public boolean isParamKeyExit(Long id, String paramKey) {
        int num = sysConfigMapper.findNumByIdAndParamKey(id, paramKey);
        if (num > 0) {
            return true;
        }
        return false;
    }

	/**
	 * 通过参数键获得参数值
	 * @param paramKey 参数键
	 * @return 参数值
	 * @author ZhouYing 
	 * @date 2015年6月30日 下午4:57:03
	 */
    public String getParamValue(String paramKey) {
        //先从redis中获取value
        String valueRedis = null;//RedisUtil.get(RedisConstants.SYSTEM_CONFIG+paramKey);
        //如果redis中存在直接return
        if(StringUtils.isNotBlank(valueRedis)){
            return valueRedis;
        }
        //反之从数据库中查找
        String valueSql = sysConfigMapper.getParamValue(paramKey);
        if(StringUtils.isNotBlank(valueSql)){
            //查到的结果放到redis中（有效时间1天）
//            RedisUtil.set(RedisConstants.SYSTEM_CONFIG+paramKey, valueSql, RedisConstants.SYSTEM_CONFIG_TIME);
        }
        return valueSql;
    }
	/**
	 * 通过参数键设置参数值
	 * @param paramKey 参数键
	 * @param paramValue 参数值
	 * @author 许小满  
	 * @date 2015年7月2日 下午7:40:22
	 */
    public void setParamValue(String paramKey, String paramValue) {
        //redis缓存中update
//        RedisUtil.set(RedisConstants.SYSTEM_CONFIG+paramKey, paramValue, RedisConstants.SYSTEM_CONFIG_TIME);
        //mysql更新
        sysConfigMapper.setParamValue(paramKey, paramValue);
    }

	/**
	 * 通过主键查找系统参数配置详情
	 * @param id 主键
	 * @return 系统参数
	 * @author ZhouYing 
	 * @date 2015年8月17日 下午5:09:36
	 */
    public SysConfig getSysConfig(String id) {
        return sysConfigMapper.getSysConfig(id);
    }

	/**
	 * 获取系统参数配置列表（分页）
	 * @param page 页面基本数据
	 * @param keywords 关键字
	 * @author ZhouYing 
	 * @date 2015年8月17日 下午5:10:20
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void pageQuery(Page page, String keywords) {
        if (page.getPageSize() == null) {// 每页记录数
            page.setPageSize(Integer.parseInt(SysConfigUtil.getParamValue("page.pageSize")));
        }
        int totalRecord = sysConfigMapper.pageCount(keywords);// 获取记录总数
        page.setTotalRecord(totalRecord);
        if (totalRecord > 0) {// 当没有数据时，不执行获取数据sql
            List<SysConfig> sysConfigs = sysConfigMapper.pageQuery(page.getBegin(), page.getPageSize(), keywords);// 获取记录列表
            page.setRecords(sysConfigs);
        }
    }

	/**
	 * 删除系统参数配置
	 * @param id 主键
	 * @author ZhouYing 
	 * @date 2015年8月27日 上午9:55:03
	 */
    public void delete(Long id) {
        sysConfigMapper.delete(id);
    }
}