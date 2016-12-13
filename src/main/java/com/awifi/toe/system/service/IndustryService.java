package com.awifi.toe.system.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.awifi.toe.auth.mapper.UserMapper;

/**   
 * @Description:  行业业务层
 * @Title: IndustryService.java 
 * @Package com.awifi.toe.system.service 
 * @author 亢燕翔 
 * @date 2015年11月23日 下午4:58:52
 * @version V1.0   
 */
@Service("industryService")
public class IndustryService {

    /** 用户dao */
    @Resource(name = "userMapper")
    private UserMapper userMapper;
    
    /** 日志  */
//    private static final Log logger = LogFactory.getLog(IndustryService.class);
    
    /**
     * 通过行业ID获取行业名称
     * @param industryId 行业ID
     * @return name
     * @author 亢燕翔 
     * @throws Exception 异常
     * @date 2015年12月22日 下午2:06:37
     */
    public String queryIndustryNameById(String industryId) throws Exception {
//        return IndustryClient.queryIndustryNameById(industryId);
        return null;
    }

}
