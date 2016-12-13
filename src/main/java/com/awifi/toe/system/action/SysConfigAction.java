package com.awifi.toe.system.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awifi.toe.base.action.BaseAction;
import com.awifi.toe.base.constants.ParamName;
import com.awifi.toe.base.http.HttpRequest;
import com.awifi.toe.base.model.Page;
import com.awifi.toe.base.util.ErrorUtil;
import com.awifi.toe.base.util.MessageUtil;
import com.awifi.toe.base.util.ValidateUtil;
import com.awifi.toe.base.util.rules.Required;
import com.awifi.toe.base.util.rules.Rule;
import com.awifi.toe.base.validator.Add;
import com.awifi.toe.base.validator.Edit;
import com.awifi.toe.system.model.SysConfig;
import com.awifi.toe.system.service.SysConfigService;

/**
 * @Description: 系统参数操作层
 * @Title: SysConfigAction.java
 * @Package com.awifi.toe.system.action
 * @author ZhouYing
 * @date 2015年6月26日 下午2:04:41
 * @version V1.0
 */
@Controller
@SuppressWarnings("rawtypes")
@Scope("prototype")
@RequestMapping("/sysconfig")
public class SysConfigAction extends BaseAction {


    /** 参数配置业务 */
    @Resource(name = "sysConfigService")
    private SysConfigService sysConfigService;

    /**
     * 增加系统参数
     * @param sysconfig 系统参数
     * @param error 错误信息
     * @return resultMap
     * @author ZhouYing
     * @date 2015年6月26日 下午5:15:42
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Map add(@Validated({ Add.class }) SysConfig sysconfig, Errors error) {
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
            if (error.hasErrors()) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", ErrorUtil.getErrorMessage(error));
                return resultMap;
            }
            // 参数键唯一性 校验
            if (sysConfigService.isParamKeyExit(sysconfig.getId(), sysconfig.getParamKey())) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("sysconfig.paramKey.exit"));
                return resultMap;
            }
            sysConfigService.setConfig(sysconfig);// 插入一条系统参数记录
            resultMap.put("result", "OK");
            resultMap.put("message", "");
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode);// 系统异常！异常编号：
            saveExceptionLog(interfaceCode, "sysconfig", sysConfigService.getClass().toString(), e);// 记录异常
        }
        return resultMap;
    }

    /**
     * 编辑系统参数
     * @param sysconfig 系统参数
     * @param error 错误信息
     * @return resultMap
     * @author ZhouYing
     * @date 2015年6月26日 下午5:14:46
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Map edit(@Validated({ Edit.class }) SysConfig sysconfig, Errors error) {
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
            if (error.hasErrors()) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", ErrorUtil.getErrorMessage(error));
                return resultMap;
            }
            // 参数键唯一性校验
            if (sysConfigService.isParamKeyExit(sysconfig.getId(), sysconfig.getParamKey())) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("sysconfig.paramKey.exit"));// 参数键已存在
                return resultMap;
            }
            sysConfigService.updateSysConfig(sysconfig);// 更新一条记录
            resultMap.put("result", "OK");
            resultMap.put("message", "");
        } catch (Exception e) {
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode);// 系统异常！异常编号：
            ErrorUtil.printException(e, logger);
            saveExceptionLog(interfaceCode, "sysconfig", sysConfigService.getClass().toString(), e);// 记录异常
        }
        return resultMap;
    }

    /**
     * 通过id查看系统参数详情
     * @param id 主键
     * @return resultMap
     * @author ZhouYing
     * @date 2015年6月26日 下午4:13:32
     */
    @RequestMapping(value = "/getinfo")
    @ResponseBody
    public Map getSysConfig(String id) {
        String interfaceCode = "";
        resultMap = new HashMap<String, Object>();
        try {
            // 请求方法类型 校验
            if (request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));// 非法请求！
                return resultMap;
            }
            // 请求参数校验
            ValidateUtil vu = new ValidateUtil();
            vu.add("id", id, ParamName.ID, new Rule[] { new Required() });
            String validStr = vu.validateString();
            if (StringUtils.isNotBlank(validStr)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            SysConfig sysconfig = sysConfigService.getSysConfig(id);// 通过别名查看对应的记录
            resultMap.put("result", "OK");
            resultMap.put("data", sysconfig);
            resultMap.put("message", "");
        } catch (Exception e) {
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode);// 系统异常！异常编号：
            ErrorUtil.printException(e, logger);
            saveExceptionLog(interfaceCode, "sysconfig", sysConfigService.getClass().toString(), e);// 记录异常
        }
        return resultMap;
    }

    /**
     * 获取系统参数列表
     * @param page 页面基础数据
     * @param error 错误信息
     * @param keywords 关键字
     * @return resultMap
     * @author ZhouYing
     * @date 2015年6月29日 上午9:22:19
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map getAllList(@Validated() Page page, Errors error, String keywords) {
        String interfaceCode = "";
        resultMap = new HashMap<String, Object>();
        try {
            // 请求方法类型 校验
            if (request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));// 非法请求！
                return resultMap;
            }
            // 参数校验
            if (error.hasErrors()) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", ErrorUtil.getErrorMessage(error));
                return resultMap;
            }
            sysConfigService.pageQuery(page, keywords);
            this.setPageInfo(page);
            resultMap.put("result", "OK");
            resultMap.put("message", "");
            saveOperationLog("sysconfig",sysConfigService.getClass().toString(),"参数配置分页查询");//记录操作日志
        } catch (Exception e) {
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode);// 系统异常！异常编号：
            ErrorUtil.printException(e, logger);
            saveExceptionLog(interfaceCode, "sysconfig", sysConfigService.getClass().toString(), e);// 记录异常
        }
        return resultMap;
    }

    /**
     * 根据key值修改对应的value
     * @param paramKey 参数键
     * @param paramValue 参数值
     * @return resultMap
     * @author 许小满
     * @date 2015年8月11日 下午2:05:21
     */
    @RequestMapping(value = "/setvaluebykey")
    @ResponseBody
    public Map setValueByKey(String paramKey, String paramValue) {
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
            vu.add("paramKey", paramKey, ParamName.PARAM_KEY, new Rule[] { new Required() });
            vu.add("paramValue", paramValue, ParamName.PARAM_VALUE, new Rule[] { new Required() });
            String validStr = vu.validateString();
            if (StringUtils.isNotBlank(validStr)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            sysConfigService.setParamValue(paramKey, paramValue);
            resultMap.put("result", "OK");
            resultMap.put("message", "");
        } catch (Exception e) {
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode);// 系统异常！异常编号：
            ErrorUtil.printException(e, logger);
            saveExceptionLog(interfaceCode, "sysconfig", sysConfigService.getClass().toString(), e);// 记录异常
        }
        return resultMap;
    }

    /**
     * 根据key值修改对应的value
     * @param paramKey 参数键
     * @return resultMap
     * @author 许小满
     * @date 2015年8月21日 下午4:31:21
     */
    @RequestMapping(value = "/getvaluebykey")
    @ResponseBody
    public Map getValueByKey(String paramKey) {
        String interfaceCode = "";
        resultMap = new HashMap<String, Object>();
        try {
            // 请求方法类型 校验
            if (request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));// 非法请求！
                return resultMap;
            }
            // 请求参数校验
            ValidateUtil vu = new ValidateUtil();
            vu.add("paramKey", paramKey, ParamName.PARAM_KEY, new Rule[] { new Required() });
            String validStr = vu.validateString();
            if (StringUtils.isNotBlank(validStr)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            String paramValue = sysConfigService.getParamValue(paramKey);// 通过key获取value
            resultMap.put("result", "OK");
            resultMap.put("paramValue", paramValue);
            resultMap.put("message", "");
        } catch (Exception e) {
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode);// 系统异常！异常编号：
            ErrorUtil.printException(e, logger);
            saveExceptionLog(interfaceCode, "sysconfig", sysConfigService.getClass().toString(), e);// 记录异常
        }
        return resultMap;
    }

    /**
     * 删除系统参数配置
     * @param id 主键
     * @return resultMap
     * @author ZhouYing
     * @date 2015年8月27日 上午9:46:32
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map delete(String id) {
        String interfaceCode = "";
        resultMap = new HashMap<String, Object>();
        try {
            // 请求方法类型 校验
            if (request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));// 非法请求！
                return resultMap;
            }
            // 请求参数校验
            ValidateUtil vu = new ValidateUtil();
            vu.add("id", id, ParamName.ID, new Rule[] { new Required() });
            String validStr = vu.validateString();
            if (StringUtils.isNotBlank(validStr)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            sysConfigService.delete(Long.parseLong(id));// 删除该配置
            resultMap.put("result", "OK");
            resultMap.put("message", "");
        } catch (Exception e) {
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode);// 系统异常！异常编号：
            ErrorUtil.printException(e, logger);
            saveExceptionLog(interfaceCode, "sysconfig", sysConfigService.getClass().toString(), e);// 记录异常
        }
        return resultMap;
    }
}