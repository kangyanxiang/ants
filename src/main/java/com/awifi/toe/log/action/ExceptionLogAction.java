package com.awifi.toe.log.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awifi.toe.base.action.BaseAction;
import com.awifi.toe.base.constants.ParamName;
import com.awifi.toe.base.http.HttpRequest;
import com.awifi.toe.base.model.Page;
import com.awifi.toe.base.util.MessageUtil;
import com.awifi.toe.base.util.ValidateUtil;
import com.awifi.toe.base.util.rules.Numeric;
import com.awifi.toe.base.util.rules.Required;
import com.awifi.toe.base.util.rules.Rule;
import com.awifi.toe.log.model.ExceptionLog;
import com.awifi.toe.log.service.ExceptionLogService;

/**
 * 异常控制层
 * @author niuhuafeng
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/exceptionlog")
@SuppressWarnings("rawtypes")
public class ExceptionLogAction extends BaseAction{

    /**异常业务*/
    @Resource(name = "exceptionLogService")
    private ExceptionLogService exceptionLogService;
	
	/**
	 * 分页查询
	 * @param page 页面基本数据
	 * @return json
	 * @author 苏晨斌  
	 * @date 2015年8月18日 下午4:06:51
	 */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map list(Page page) {
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
            String pageNo = page != null ? page.getPageNo().toString() : null;//页码
            String startDate = request.getParameter("startDate");//开始时间
            String endDate = request.getParameter("endDate");//结束时间
            String moduleName = request.getParameter("moduleName");//模块名称
            String serviceName = request.getParameter("serviceName");//service 名称
            String errorMessage = request.getParameter("errorMessage");//异常信息
            String interfaceUrl = request.getParameter("interfaceUrl");//接口地址
            String interfaceParam = request.getParameter("interfaceParam");//接口参数
            String interfaceReturnValue = request.getParameter("interfaceReturnValue");//接口返回值
            
            ValidateUtil vu = new ValidateUtil();
            vu.add("pageNo", pageNo, ParamName.PAGE_NO, new Rule[] { new Required() });//页码
            vu.add("startDate", startDate, ParamName.STARDATE, new Rule[] { new Required() });//开始时间
            vu.add("endDate", endDate, ParamName.ENDDATE, new Rule[] { new Required() });//结束时间
            String validStr = vu.validateString();
            if (StringUtils.isNotBlank(validStr)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            String keywords = null;
            startDate = StringUtils.isNotBlank(startDate) ? startDate + ":00 " : null;
            endDate = StringUtils.isNotBlank(endDate) ? endDate + ":59 " : null;
            keywords = StringUtils.isNotBlank(keywords) ? keywords : null;
            exceptionLogService.pageQuery(page, startDate, endDate, moduleName, serviceName, errorMessage, interfaceUrl, interfaceParam, interfaceReturnValue);
            this.setPageInfo(page);
            resultMap.put("result", "OK");
            resultMap.put("message", "");
        } catch (Exception e) {
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode); // 系统异常！异常编号：
            saveExceptionLog(interfaceCode, "exceptionlog", exceptionLogService.getClass().toString(), e);
        }
        return resultMap;
    }
    
	/**
	 * 查看异常
	 * @param id 主键
	 * @return json
	 * @author 苏晨斌  
	 * @date 2015年8月19日 下午3:25:06
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
            vu.add("id", id, ParamName.EXCEPTION_ID, new Rule[] { new Required(), new Numeric() });
            String validStr = vu.validateString();
            if (StringUtils.isNotBlank(validStr)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            ExceptionLog exceptionLog = exceptionLogService.findById(Long.parseLong(id));
            resultMap.put("result", "OK");
            resultMap.put("message", "");
            resultMap.put("data", exceptionLog);
        } catch (Exception e) {
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode); // 系统异常！异常编号：
            saveExceptionLog(interfaceCode, "exceptionlog", exceptionLogService.getClass().toString(), e);
        }
        return resultMap;
    }
}