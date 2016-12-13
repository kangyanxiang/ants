package com.awifi.toe.log.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.awifi.toe.base.model.Page;
import com.awifi.toe.log.mapper.OperationLogMapper;
import com.awifi.toe.log.model.OperationLog;
import com.awifi.toe.system.util.SysConfigUtil;

/**   
 * @Description:  
 * @Title: OperationLogService.java 
 * @Package com.awifi.toe.log.service 
 * @author ZhouYing 
 * @date 2016年7月7日 下午3:34:36
 * @version V1.0   
 */
@Service("operationLogService")
public class OperationLogService {
    
    /***/
    @Resource(name = "operationLogMapper")
    private OperationLogMapper operationLogMapper;

    /**
     * 操作日志列表
     * @param page 页面
     * @param keywords 模块关键字
     * @param userName 账号名称
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @author ZhouYing 
     * @date 2016年7月7日 下午4:33:06
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void pageQuery(Page page, String keywords, String userName, String startDate, String endDate) {
        if (page.getPageSize() == null) {// 每页记录数
            page.setPageSize(Integer.parseInt(SysConfigUtil.getParamValue("page.pageSize")));
        }
        int totalRecord = operationLogMapper.pageCount(keywords,userName,startDate,endDate);// 获取记录总数
        page.setTotalRecord(totalRecord);
        if (totalRecord > 0) {// 当没有数据时，不执行获取数据sql
            List<OperationLog> operationLogList = operationLogMapper.pageQuery(keywords,userName,startDate,endDate,page.getBegin(),page.getPageSize());// 获取记录列表
            page.setRecords(operationLogList);
        }
    }

    /**
     * 查看操作日志详情
     * @param id 主键
     * @return 操作日志
     * @author ZhouYing 
     * @date 2016年7月7日 下午5:01:41
     */
    public OperationLog show(Long id) {
        return operationLogMapper.show(id);
    }
    
    /**
     * 新增操作日志
     * @param operationLog 操作日志
     * @author 许小满  
     * @date 2016年7月12日 下午4:05:59
     */
    public void insert(OperationLog operationLog) {
        operationLogMapper.insert(operationLog);
    }
}