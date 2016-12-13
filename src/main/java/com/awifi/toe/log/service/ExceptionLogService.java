package com.awifi.toe.log.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.awifi.toe.base.model.Page;
import com.awifi.toe.log.mapper.ExceptionLogMapper;
import com.awifi.toe.log.model.ExceptionLog;
import com.awifi.toe.system.util.SysConfigUtil;

/**
 * 异常业务层
 * 
 * @author niuhuafeng
 *
 */
@Service("exceptionLogService")
public class ExceptionLogService {

    /**异常mapper*/
    @Resource(name = "exceptionLogMapper")
    private ExceptionLogMapper exceptionLogMapper;

    /**
     * 记录异常
     * @param log 异常
     * @return 是否保存成功
     * @throws Exception 异常
     */
    public int saveException(ExceptionLog log) throws Exception {
        return exceptionLogMapper.saveExceptionLog(log);
    }

    /**
     * 分页查询
     * @param page 页面基本数据
     * @param startDate 创建时间
     * @param endDate 结束时间
     * @param moduleName 模块名称
     * @param serviceName service 名称
     * @param errorMessage 异常信息
     * @param interfaceUrl 接口地址
     * @param interfaceParam 接口参数
     * @param interfaceReturnValue 接口返回值
     * @author 苏晨斌
     * @date 2015年8月18日 下午4:26:51
     */
    @SuppressWarnings("unchecked")
    public void pageQuery(@SuppressWarnings("rawtypes") Page page, 
            String startDate, String endDate, String moduleName, String serviceName, String errorMessage,
            String interfaceUrl, String interfaceParam, String interfaceReturnValue) {
        if (page.getPageSize() == null) {// 每页记录数
            page.setPageSize(Integer.parseInt(SysConfigUtil.getParamValue("page.pageSize")));
        }
        int totalRecord = exceptionLogMapper.pageCount(startDate, endDate, moduleName, serviceName, errorMessage, interfaceUrl, interfaceParam, interfaceReturnValue);// 获取记录总数
        page.setTotalRecord(totalRecord);
        if (totalRecord > 0) {// 当没有数据时，不执行获取数据sql
            List<ExceptionLog> exceptionLogList = exceptionLogMapper.pageQuery(page.getBegin(), page.getPageSize(),
                    startDate, endDate, moduleName, serviceName, errorMessage, interfaceUrl, interfaceParam, interfaceReturnValue);// 获取记录列表
            page.setRecords(exceptionLogList);
        }

    }

    /**
     * 通过主键id获取异常信息
     * @param id 主键id
     * @return 异常对象
     * @author 苏晨斌
     * @date 2015年8月19日 下午3:25:41
     */
    public ExceptionLog findById(Long id) {
        return exceptionLogMapper.findById(id);
    }
}