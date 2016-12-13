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
import com.awifi.toe.log.model.RequestLog;
import com.awifi.toe.log.service.RequestLogService;

/**   
 * @Description:  
 * @Title: RequestLogAction.java 
 * @Package com.awifi.toe.log.action 
 * @author 苏晨斌 
 * @date 2016年4月21日 上午11:01:18
 * @version V1.0   
 */
@Controller
@Scope("prototype")
@RequestMapping("/requestlog")
@SuppressWarnings("rawtypes")
public class RequestLogAction extends BaseAction{

    /**请求日志业务层 */
    @Resource(name = "requestLogService")
    private RequestLogService requestLogService;
    
    /**
     * 请求日志列表
     * @param page 页面基本数据
     * @param error 错误信息
     * @return resultMap
     * @author 苏晨斌  
     * @date 2016年4月21日 上午11:09:42
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map list(@Validated()Page page, Errors error){
        String interfaceCode = "";
        resultMap = new HashMap<String, Object>();
        try{
            // 请求方法类型 校验
            if(request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));//非法请求！
                return resultMap;
            }
            // 请求参数 校验
            if(error.hasErrors()){
                resultMap.put("result", "FAIL");
                resultMap.put("message", ErrorUtil.getErrorMessage(error));
                return resultMap;
            }
            String keywords = StringUtils.isNotBlank(request.getParameter("keywords")) ? request.getParameter("keywords") : null;
            String startDate = StringUtils.isNotBlank(request.getParameter("startDate")) ? request.getParameter("startDate") : null;
            String endDate = StringUtils.isNotBlank(request.getParameter("endDate")) ? request.getParameter("endDate") : null;
            requestLogService.pageQuery(page, keywords,startDate,endDate);
            this.setPageInfo(page);
            resultMap.put("result", "OK");
            resultMap.put("message", "");
        }catch(Exception e){
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode); //系统异常！异常编号：
            saveExceptionLog(interfaceCode, "requestLog", requestLogService.getClass().toString(), e);
        }
        return resultMap;
    }
    /**
     * 查看
     * @return resultMap
     * @author 苏晨斌  
     * @date 2016年4月21日 上午11:11:57
     */
    @RequestMapping(value = "/show")
    @ResponseBody
    public Map show(){
        String interfaceCode = "";
        resultMap = new HashMap<String, Object>();
        try{
            // 请求方法类型 校验
            if(request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));//非法请求！
                return resultMap;
            }
            // 请求参数 校验
            String id = request.getParameter("id");
            ValidateUtil vu = new ValidateUtil();
            vu.add("id", id, ParamName.REQUEST_ID,new Rule[] {new Required(),new Numeric()});
            String validStr = vu.validateString();
            if(StringUtils.isNotBlank(validStr)){
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            RequestLog requestLog = requestLogService.findById(Long.parseLong(id));
            resultMap.put("result", "OK");
            resultMap.put("message", "");
            resultMap.put("data", requestLog);
        }catch(Exception e){
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode); //系统异常！异常编号：
            saveExceptionLog(interfaceCode, "request", requestLogService.getClass().toString(), e);
        }
        return resultMap;
    }
    
}
