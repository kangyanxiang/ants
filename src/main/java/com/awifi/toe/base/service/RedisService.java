package com.awifi.toe.base.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedisPool;

/**   
 * @Description:  Redis--增删改查
 * @Title: RedisService.java 
 * @Package com.awifi.toe.redis.service 
 * @author kangyanxiang
 * @date 2016年3月16日 下午5:12:02
 * @version V1.0   
 */
@Service("redisService")
public class RedisService {
    
    /**
     * redis连接DB
     */
    @Value("${redis.node.database.index}")
    private int index;
    
    /**
     * 注入ShardedJedisPool
     * required = false  非强制注入
     */
//    @Autowired(required = false)
    private ShardedJedisPool shardedJedisPool;
    
    /** 日志  */
    private static final Log logger = LogFactory.getLog(RedisService.class);
    
    /**
     * 公共方法
     * @param function 方法
     * @param <T> 泛型
     * @return jedis对象
     * @author kangyanxiang
     * @date 2016年3月29日 下午12:14:31
     */
//    private <T> T execute(RedisFunction<T, ShardedJedis> function){
//        ShardedJedis shardedJedis = null;
//        try {
//            //获取jedis对象
//            shardedJedis = shardedJedisPool.getResource();
//            Collection<Jedis> collection = shardedJedis.getAllShards();//获取shard pool
//            Iterator<Jedis> jedis = collection.iterator();//遍历pool
//            while(jedis.hasNext()){//获取每个shard
//                jedis.next().select(index);//为每个shard set DB
//            }
//            //logger.debug("提示：redis连接DB成功---->DB="+index);
//            return function.callBack(shardedJedis);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            //检查连接是否有效，有效则放回连接池，无效则关闭。
//            if(null != shardedJedis){
//                shardedJedis.close(); 
//            }
//        }
//        return null;
//    }
    
    /**
     * set 操作
     * @param key 键
     * @param value 值
     * @return 结果
     * @author 亢燕翔 
     * @date 2016年3月16日 下午5:27:09
    public String set(final String key, final String value){
        return this.set(key, value, null);
    }
    */
    
    /**
     * 设置值以及生存时间
     * @param key 键
     * @param value 值
     * @param seconds 单位（秒）
     * @return 结果
     * @author 亢燕翔 
     * @date 2016年3月16日 下午5:27:09
     */
//    public String set(final String key, final String value, final Integer seconds){
//        return this.execute(new RedisFunction<String, ShardedJedis>() {
//            @Override
//            public String callBack(ShardedJedis e) {
//                boolean secondsNotNull = seconds != null;
//                String result = e.set(key, value);
//                if(secondsNotNull){
//                    e.expire(key, seconds);
//                }
//                logger.debug("提示：redis-"+ index +"-set操作： key="+key+"&value=" + value + (secondsNotNull ? ("&expire="+seconds + " s.") : StringUtils.EMPTY));
//                return result;
//            }
//        });
//    }
    
    /**
     * get 操作
     * @param key 键
     * @return 结果
     * @author 亢燕翔 
     * @date 2016年3月16日 下午5:29:53
     */
//    public String get(final String key){
//        return this.execute(new RedisFunction<String, ShardedJedis>() {
//            @Override
//            public String callBack(ShardedJedis e) {
//                String value = e.get(key);
//                logger.debug("提示：redis-"+ index +"-get操作： " + key + "=" + value);
//                return value;
//            }
//        });
//    }
    
    /**
     * Hash键值设置操作
     * @param key 键
     * @param map 存储MAP
     * @return 结果
     * @author 亢燕翔 
     
    public String hmset(final String key,final Map<String, String> map){
        return this.hmset(key, map, null);
    }
    */
    
    /**
     * Hash键值设置操作
     * @param key 键
     * @param map 存储MAP
     * @param seconds 单位（秒）
     * @return 结果
     * @author kangyanxiang 
     * @date 2016年6月16日 下午3:45:28
     */
//    public String hmset(final String key,final Map<String, String> map, final Integer seconds){
//        return this.execute(new RedisFunction<String, ShardedJedis>() {
//            @Override
//            public String callBack(ShardedJedis e) {
//                boolean secondsNotNull = seconds != null;
//                String result = e.hmset(key, map);
//                if(secondsNotNull){
//                    e.expire(key, seconds);
//                }
//                logger.debug("提示：redis-"+ index +"-hmset操作： key="+key+"&value=" + map + (secondsNotNull ? ("&expire="+seconds + " s.") : StringUtils.EMPTY));
//                return result;
//            }
//        });
//    }
    
    /**
     * Hash键值 字段 get操作
     * @param key 键
     * @param fields 字段(可多个)
     * @return 结果
     * @author kangyanxiang 
     * @date 2016年6月16日 下午3:45:28
     */
//    public List<String> hmget(final String key,final String... fields){
//        return this.execute(new RedisFunction<List<String>, ShardedJedis>() {
//            @Override
//            public List<String> callBack(ShardedJedis e) {
//                List<String> filedValueList = e.hmget(key, fields);
//                logger.debug("提示：redis-"+ index +"-hmget操作：key=" + key + "&fields=" + StringUtils.join(fields, ",") + "&value=" + filedValueList);
//                return filedValueList;
//            }
//        });
//    }
    
    /**
     * Hash键值get操作
     * @param key 键
     * @return map
     * @author 亢燕翔
     */
//    public Map<String, String> hgetAll(final String key){
//        return this.execute(new RedisFunction<Map<String, String>, ShardedJedis>() {
//            @Override
//            public Map<String, String> callBack(ShardedJedis e) {
//                Map<String, String> resultMap = e.hgetAll(key);
//                logger.debug("提示：redis-"+ index +"-hgetAll操作：key=" + key + "&value="+resultMap);
//                return resultMap;
//            }
//        });
//    }
    
    /**
     * 删除 操作
     * @param key 键
     * @return 结果
     * @author 亢燕翔 
     * @date 2016年3月16日 下午5:31:08
     */
//    public Long del(final String key){
//        return this.execute(new RedisFunction<Long, ShardedJedis>() {
//            @Override
//            public Long callBack(ShardedJedis e) {
//                logger.debug("提示：redis-"+ index +"-del操作：key="+key);
//                return e.del(key);
//            }
//        });
//    }
    
    /**
     * 单独设置时间
     * @param key 键
     * @param seconds 单位（秒）
     * @return 结果
     * @author 亢燕翔 
     * @date 2016年3月16日 下午5:27:09
     
    public Long setSeconds(final String key, final Integer seconds){
        return this.execute(new RedisFunction<Long, ShardedJedis>() {
            @Override
            public Long callBack(ShardedJedis e) {
                logger.debug("提示：redis设置时间操作---->key="+key+"&时间seconds="+seconds);
                return e.expire(key, seconds);
            }   
        });
    }
    */
    
}