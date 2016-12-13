package com.awifi.toe.log.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.awifi.toe.base.model.Page;
import com.awifi.toe.log.mapper.RequestLogMapper;
import com.awifi.toe.log.model.RequestLog;
import com.awifi.toe.system.util.SysConfigUtil;

/**   
 * @Description:  
 * @Title: RequestLogService.java 
 * @Package com.awifi.toe.log.service 
 * @author 苏晨斌 
 * @date 2016年4月21日 上午11:05:57
 * @version V1.0   
 */
@Service("requestLogService")
public class RequestLogService {

    /**请求*/
    @Resource(name = "requestLogMapper")
    private RequestLogMapper requestLogMapper;
    
    /**
     * 插入一条请求日志
     * @param log 请求日志
     * @return 是否成功
     */
    public int saveRequest(RequestLog log) {
        requestLogMapper.insertRequestLog(log);
        return 1;
    }
    

    /**
     * 请求日志(分页)
     * @param page 分页实体
     * @param keywords 关键字
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @author 苏晨斌  
     * @date 2016年4月21日 上午11:15:57
     */
    @SuppressWarnings("unchecked")
    public void pageQuery(@SuppressWarnings("rawtypes") Page page, String keywords,String startDate,String endDate){
        if(page.getPageSize() == null){//每页记录数
            page.setPageSize(Integer.parseInt(SysConfigUtil.getParamValue("page.pageSize")));
        }
        //返回信息：序号、请求URL、记录时间
        int totalRecord =requestLogMapper.pageCount(keywords,startDate,endDate);//获取记录总数
        page.setTotalRecord(totalRecord);
        if(totalRecord > 0){//当没有数据时，不执行获取数据sql
            List< RequestLog>  requestLogList =requestLogMapper.pageQuery(page.getBegin(), page.getPageSize(), keywords,startDate,endDate);//获取记录列表
            page.setRecords( requestLogList);
        }
        
    }
    /**
     * 通过主键id获取请求信息
     * @param id 主键id
     * @return 详细信息
     * @author 苏晨斌  
     * @date 2016年4月21日 上午11:22:51
     */
    public  RequestLog findById(Long id){
        return  requestLogMapper.findById(id);
    }

    /**
     * 定时删除请求日志
     * @param time 时间
     * @author 苏晨斌  
     * @date 2016年4月27日 上午9:43:21
     */
    public void clearRequestLog(Long time) {
        requestLogMapper.delete(time);
    }

}
