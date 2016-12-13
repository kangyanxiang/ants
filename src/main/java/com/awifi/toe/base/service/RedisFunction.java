package com.awifi.toe.base.service;
/**   
 * @Description:  
 * @Title: RedisServiceImpl.java 
 * @Package com.awifi.toe.redis.service.impl 
 * @author kangyanxiang
 * @date 2016年3月16日 下午5:13:18
 * @version V1.0   
 */
public interface RedisFunction<T, E> {
    
    /**
     * @param e 泛型
     * @return E
     * @author kangyanxiang
     * @date 2016年3月29日 下午12:13:22
     */
    T callBack(E e);
    
}
