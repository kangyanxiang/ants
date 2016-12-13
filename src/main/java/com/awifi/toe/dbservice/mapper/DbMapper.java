package com.awifi.toe.dbservice.mapper;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Service;

/**   
 * @Description:  
 * @Title: DbMapper.java 
 * @Package com.awifi.toe.dbservice.mapper 
 * @author kangyanxiang 
 * @date 2016年12月5日 下午3:07:27
 * @version V1.0   
 */
@Service("dbMapper")
public interface DbMapper {

    @Insert("insert into toe_c3p0_test_table(a) values (#{bodyString}) ")
    void insertData(String bodyString);
    
    
}
