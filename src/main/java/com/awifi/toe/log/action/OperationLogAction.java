package com.awifi.toe.log.action;

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
import com.awifi.toe.base.util.rules.Numeric;
import com.awifi.toe.base.util.rules.Required;
import com.awifi.toe.base.util.rules.Rule;
import com.awifi.toe.log.model.OperationLog;
import com.awifi.toe.log.service.OperationLogService;

/**   
 * @Description:  操作日志
 * @Title: OperationLogAction.java 
 * @Package com.awifi.toe.log.action 
 * @author ZhouYing 
 * @date 2016年7月7日 下午3:21:05
 * @version V1.0   
 */
@Controller 
@Scope("prototype")
@RequestMapping("/operationlog")
@SuppressWarnings("rawtypes")
public class OperationLogAction extends BaseAction {

    /**操作日志*/
    @Resource(name = "operationLogService")
    private OperationLogService operationLogService;
   
    /**
     * 列表
     * @param page 页 面
     * @param error 错误
     * @return map
     * @author ZhouYing 
     * @date 2016年7月7日 下午3:35:25
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map list(@Validated() Page page, Errors error) {
        String interfaceCode = "";
        resultMap = new HashMap<String, Object>();
        String date = request.getParameter("date");//时间
        String userName = request.getParameter("userName");//账号名称
        String keywords = request.getParameter("keywords");//模块关键字
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
            if(StringUtils.isBlank(date)){
                resultMap.put("result", "FAIL");
                resultMap.put("message", "时间不允许为空！");
                return resultMap;
            }
            String startDate = date + " 00:00:00 ";
            String endDate = date + " 23:59:59 ";
            operationLogService.pageQuery(page, keywords,userName, startDate, endDate);
            this.setPageInfo(page);
            resultMap.put("result", "OK");
            resultMap.put("message", "");
        } catch (Exception e) {
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode); // 系统异常！异常编号：
            saveExceptionLog(interfaceCode, "operationlog", operationLogService.getClass().toString(), e);
        }
        return resultMap;
    }
    
    /**
     * 详情
     * @param id 主键
     * @return map
     * @author ZhouYing 
     * @date 2016年7月7日 下午5:09:01
     */
    @RequestMapping(value = "/show")
    @ResponseBody 
    public Map show(String id) {
        String interfaceCode = "";
        resultMap = new HashMap<String, Object>();
        try {
            // 请求方法类型 校验
            if (request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));// 非法请求！
                return resultMap;
            }
            ValidateUtil vu = new ValidateUtil();
            vu.add("id", id, ParamName.OPERATION_ID, new Rule[] { new Required(), new Numeric() });
            String validStr = vu.validateString();
            if (StringUtils.isNotBlank(validStr)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            OperationLog operationLog = operationLogService.show(Long.parseLong(id));
            resultMap.put("result", "OK");
            resultMap.put("message", "");
            resultMap.put("data", operationLog);
        } catch (Exception e) {
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode); // 系统异常！异常编号：
            saveExceptionLog(interfaceCode, "operationlog", operationLogService.getClass().toString(), e);
        }
        return resultMap;
    }
}