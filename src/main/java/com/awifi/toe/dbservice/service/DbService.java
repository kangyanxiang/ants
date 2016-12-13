package com.awifi.toe.dbservice.service;

import javax.annotation.Resource;

import com.awifi.toe.base.util.BeanUtil;
import com.awifi.toe.dbservice.mapper.DbMapper;

/**   
 * @Description:  
 * @Title: DbService.java 
 * @Package com.awifi.toe.dbservice.service 
 * @author kangyanxiang 
 * @date 2016年12月5日 下午3:06:00
 * @version V1.0   
 */
public class DbService {

    
    /** 用户dao */
    private static DbMapper dbMapper;
    
    private String body;
    
    public DbService(String body) {
        this.body = body;
        insertData(body);
    }

    private static DbMapper getDbMapper(){
        if(dbMapper == null){
            dbMapper = (DbMapper) BeanUtil.getBean("dbMapper");
        }
        return dbMapper;
    }
    
    
    private void insertData(String bodyString) {
        
        formatData(bodyString);
        
        getDbMapper().insertData("j");
    }

    private void formatData(String bodyString) {
        // TODO Auto-generated method stub
        
    }

    
    
}
