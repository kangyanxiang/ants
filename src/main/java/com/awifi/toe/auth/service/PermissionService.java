package com.awifi.toe.auth.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.awifi.toe.auth.mapper.PermissionMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 权限业务层
 * @Title: PermissionService.java
 * @Package com.awifi.toe.auth.service
 * @author kangyanxiang
 * @date 2015年8月12日 上午10:04:21
 * @version V1.0
 */
@Service("permissionService")
public class PermissionService {

    // private static final Log logger = LogFactory.getLog(PermissionService.class);

    /** 权限 */
    @Resource(name = "permissionMapper")
    private PermissionMapper permissionMapper;

    /**
     * 判断是否有权限
     * 
     * @param userId
     *            用户id
     * @param code
     *            权限编号
     * @return true 表示有权限、false 表示 无权限
     * @author kangyanxiang
     * @date 2015年8月12日 上午10:14:53
     */
    public boolean hasPermission(Long userId, String code) {
        int count = permissionMapper.findNumByUserAndCode(userId, code);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * 通过账号查找已有权限的code集合
     * 
     * @param userId
     *            用户id
     * @return 权限code
     * @author kangyanxiang
     * @date 2015年10月23日 下午7:17:52
     */
    public List<String> findCodeByUserId(Long userId) {
        if (userId == null) {
            return new ArrayList<String>();
        }
        return permissionMapper.findCodeByUserId(userId);
    }

    /**
     * 判断客户是否有权限
     * @param customerId 客户id
     * @param permissionId 权限id
     * @return true 有权限
     * @author kangyanxiang 
     * @date 2016年7月13日 上午9:48:28
     */
    public boolean hasPermission(String customerId, String permissionId) {
        int count = permissionMapper.findByCustomerAndId(customerId,permissionId);
        return count>0 ? true : false;
    }
}