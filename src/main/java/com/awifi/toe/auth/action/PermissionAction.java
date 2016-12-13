package com.awifi.toe.auth.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awifi.toe.auth.model.SessionUser;
import com.awifi.toe.auth.service.PermissionService;
import com.awifi.toe.auth.service.UserService;
import com.awifi.toe.base.action.BaseAction;
import com.awifi.toe.base.constants.ParamName;
import com.awifi.toe.base.http.HttpRequest;
import com.awifi.toe.base.util.ErrorUtil;
import com.awifi.toe.base.util.MessageUtil;
import com.awifi.toe.base.util.SessionUtil;
import com.awifi.toe.base.util.ValidateUtil;
import com.awifi.toe.base.util.rules.Required;
import com.awifi.toe.base.util.rules.Rule;

/**
 * @Description: 权限控制层
 * @Title: PermissionAction.java
 * @Package com.awifi.toe.auth.action
 * @author kangyanxiang
 * @date 2015年8月12日 上午10:03:55
 * @version V1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/permission")
@SuppressWarnings("rawtypes")
public class PermissionAction extends BaseAction {

    /** 权限业务 */
    @Resource(name = "permissionService")
    private PermissionService permissionService;

    /** 用户业务 */
    @Resource(name = "userService")
    private UserService userService;

    /**
     * 判断是否有权限
     * 
     * @param code
     *            权限编号
     * @return json : 其中 yes 表示有权限、no 表示 无权限
     * @author kangyanxiang
     * @date 2015年8月12日 上午10:11:21
     */
    @RequestMapping(value = "/haspermission")
    @ResponseBody
    public Map hasPermission(String code) {
        String interfaceCode = "";
        resultMap = new HashMap<String, Object>();
        try {
            // 请求方法类型 校验
            if (request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));// 非法请求！
                return resultMap;
            }
            // 请求参数 校验
            ValidateUtil vu = new ValidateUtil();
            vu.add("code", code, ParamName.PERMISSION_CODE, new Rule[] { new Required() });
            String validStr = vu.validateString();
            if (StringUtils.isNotBlank(validStr)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            Long curUserId = SessionUtil.getCurUserId();
            boolean hasPerm = permissionService.hasPermission(curUserId, code);
            resultMap.put("result", "OK");
            resultMap.put("message", "");
            resultMap.put("hasPermission", hasPerm ? "yes" : "no");
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode); // 系统异常！异常编号：
            saveExceptionLog(interfaceCode, "user", permissionService.getClass().toString(), e);
        }
        return resultMap;
    }

    /**
     * 获取当前用户已有权限的code集合
     * 
     * @return resultMap
     * @author kangyanxiang
     * @date 2015年10月23日 下午7:17:52
     */
    @RequestMapping(value = "/getcuruserpermissioncode")
    @ResponseBody
    public Map getCurUserPermissionCode() {
        String interfaceCode = "";
        resultMap = new HashMap<String, Object>();
        try {
            // 请求方法类型 校验
            if (request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));// 非法请求！
                return resultMap;
            }
            SessionUser user = SessionUtil.getCurSessionUser();//session中的用户信息
            Long curUserId = user.getId();//用户表主键id
            resultMap.put("result", "OK");
            resultMap.put("message", "");
            resultMap.put("data", permissionService.findCodeByUserId(curUserId));//权限编号集合
            resultMap.put("userType", user.getUserType());//用户类型
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode); // 系统异常！异常编号：
            saveExceptionLog(interfaceCode, "user", permissionService.getClass().toString(), e);
        }
        return resultMap;
    }
}