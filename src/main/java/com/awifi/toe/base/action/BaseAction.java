package com.awifi.toe.base.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.awifi.toe.auth.model.SessionUser;
import com.awifi.toe.base.exception.InterfaceException;
import com.awifi.toe.base.model.Page;
import com.awifi.toe.base.util.ErrorUtil;
import com.awifi.toe.base.util.FormatUtil;
import com.awifi.toe.base.util.IPUtil;
import com.awifi.toe.base.util.SessionUtil;
import com.awifi.toe.log.model.ExceptionLog;
import com.awifi.toe.log.model.OperationLog;
import com.awifi.toe.log.service.ExceptionLogService;
import com.awifi.toe.log.service.OperationLogService;
import com.google.gson.Gson;

/**
 * @Description: 作用：为所有action提供公共的服务
 * @Title: BaseAction.java
 * @Package com.awifi.toe.base.action
 * @author kangyanxiang
 * @date 2015年6月23日 下午4:57:25
 * @version V1.0
 */
public class BaseAction {

    /** 日志 */
    protected Log logger = LogFactory.getLog(this.getClass());

    /** 成功 */
    public static final String SUCCESS = "success";
    /** 错误 */
    public static final String ERROR = "error";

    /** 异常日志业务 */
    @Resource(name = "exceptionLogService")
    private ExceptionLogService exceptionLogService;
    
    /** 操作日志业务 */
    @Resource(name = "operationLogService")
    private OperationLogService operationLogService;

    /** 请求 */
    @Autowired
    protected HttpServletRequest request;

    // @Autowired
    // protected HttpServletResponse response;

    /** session */
    @Autowired
    protected HttpSession session;

    /** gson */
    protected Gson gson = new Gson();

    /**
     * 返回json数据的map
     */
    protected Map<String, Object> resultMap;

    /**
     * 设置分页信息
     * @param page 分页
     * @throws Exception 异常
     * @author kangyanxiang
     * @date 2015年12月7日 下午3:18:01
     */
    @SuppressWarnings({ "rawtypes" })
    public void setPageInfo(Page page) throws Exception {
        resultMap.put("pageNo", page.getPageNo());
        resultMap.put("pageSize", page.getPageSize());
        resultMap.put("totalRecord", page.getTotalRecord());
        resultMap.put("totalPage", page.getTotalPage());
        resultMap.put("begin", page.getBegin());
        if (page.getRecords() != null) {
            // resultMap.put("totalResult", page.getRecords().size());
            resultMap.put("records", page.getRecords());
        }
    }

    /**
     * 获取异常堆栈信息
     * 
     * @param e 异常
     * @return str
     */
    private String getExceptionStackTrace(Exception e) {
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer, true));
        return writer.toString();
    }


    /**
     * 记录异常信息
     * @param errorCode 错误代码
     * @param moduleName moduleName 模块名称
     * @param serviceName serviceName 服务名称
     * @param ex 异常
     */
    public void saveExceptionLog(String errorCode, String moduleName, String serviceName, Exception ex) {
        try {
            ExceptionLog log = new ExceptionLog();
            log.setErrorCode(errorCode);
            log.setModuleName(moduleName);
            log.setServiceName(serviceName);
            log.setParameter(FormatUtil.formatRequestParam(request));
            log.setErrorMessage(ex.toString() + "   " + getExceptionStackTrace(ex));
            // 接口异常时，需要保存额外的信息
            if(ex instanceof InterfaceException){
                InterfaceException iex = (InterfaceException)ex;
                log.setInterfaceUrl(iex.getInterfaceUrl());//接口地址
                log.setInterfaceParam(iex.getInterfaceParam());//接口参数
                log.setInterfaceReturnValue(iex.getInterfaceReturnValue());//接口返回值
            }
            exceptionLogService.saveException(log);

        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
        }
    }

    /**
     * 记录操作日志
     * @param moduleName 模块名称
     * @param serviceName 模块名称
     * @param content 日志内容
     * @author kangyanxiang
     * @date 2016年7月13日 下午11:16:28
     */
    public void saveOperationLog(String moduleName, String serviceName, String content){
        SessionUser user = SessionUtil.getCurSessionUser();
        OperationLog log = new OperationLog();
        log.setModuleName(moduleName);//模块名称
        log.setServiceName(serviceName);//服务名称
        log.setRequestIp(IPUtil.getIpAddr(request));//请求ip
        log.setRequestPort(IPUtil.getRemotePort(request));//请求端口
        log.setContent(content);//内容
        log.setUserId(user.getId());//主键id
        log.setUserName(user.getUserName());//账号名称
        operationLogService.insert(log);
    }
}