package com.awifi.toe.log.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Service;

import com.awifi.toe.log.model.MicShopLog;

/**   
 * @Description:  
 * @Title: MicShopLogMapper.java 
 * @Package com.awifi.toe.log.mapper 
 * @author ZhouYing 
 * @date 2016年7月12日 下午2:29:21
 * @version V1.0   
 */
@Service("micShopLogMapper")
public interface MicShopLogMapper {

    /**
     * 需要踢下线的用户（十三迭代去掉 component_type='unauth'）
     * @param time 超3分钟踢下线
     * @return 需要踢下线的用户
     * @author ZhouYing 
     * @date 2016年7月12日 下午3:16:54
     */
    @Select("select redis_key,redis_value from toe_app_micro_shop_log where force_attention=1 and result=2 "
            + "and FROM_UNIXTIME( create_date, '%Y-%m-%d %H:%i:%S' ) <= date_add(CURRENT_TIMESTAMP, interval - #{time} minute)")
    @Results(value = {
            @Result(property = "redisKey", column = "redis_key", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "redisValue", column = "redis_value", javaType = String.class, jdbcType = JdbcType.VARCHAR)
            })
    List<MicShopLog> getOfflineList(Integer time);

    /**
     * 踢下线后更新状态
     * @param redisKey 
     * @param result 结果 5踢下线失败 6 踢下线成功
     * @param attentionFlag -1 未关注 1关注
     * @author ZhouYing 
     * @date 2016年7月12日 下午4:04:05
     */
    @Update("update toe_app_micro_shop_log set result=#{result},attention_flag=#{attentionFlag} where redis_key=#{redisKey}")
    void update(@Param("redisKey") String redisKey,@Param("result") Integer result,@Param("attentionFlag") Integer attentionFlag);
}