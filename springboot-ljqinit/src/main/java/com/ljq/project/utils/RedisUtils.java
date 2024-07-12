package com.ljq.project.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * <p>
 * Redis 方法
 * </p>
 *
 * @author wlj
 * @since 2021/3/9
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ValueOperations<String, Object> valueOperations;
    /** 不设置过期时长 */
    public final static long NOT_EXPIRE = -1;

    /**
     * 设置值 过期时间
     * 
     * @param key
     * @param value
     * @param expire
     */
    public void set(String key, Object value, long expire) {
        valueOperations.set(key, value);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void setJson(String key, Object value, long expire) {
        valueOperations.set(key, JSON.toJSONString(value));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 获取值
     * 
     * @param key
     * @return
     */
    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public String get(String key, long expire) {
        Object val = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        String value = val == null ? null : val.toString();
        return value;
    }

    /**
     * 获取对象JSON 刷新过期时间
     * 
     * @param key
     * @param expire
     * @return
     */
    public Object getObj(String key, long expire) {
        String value = valueOperations.get(key).toString();
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : JSON.parseObject(value);
    }

    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 删除
     * 
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void increment(String key, long diff) {
        valueOperations.increment(key, diff);
    }

    public void decrement(String key, long diff) {
        valueOperations.decrement(key, diff);
    }

    /**
     * 更新过期时间
     * 
     * @param key
     */
    public Boolean updateExpire(String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    // region set(集合)相关操作

    /**
     * sAdd
     * <p>
     * 集合添加元素
     * </p>
     * 
     * @param key set名
     * @param values 值
     * @return java.lang.Long
     */
    public Long sAdd(String key, String... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * sRemove
     * <p>
     * 集合移除元素
     * </p>
     * 
     * @param key 集合名
     * @param values 值
     * @return java.lang.Long
     */
    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * sSize
     * <p>
     * 获取集合的大小
     * </p>
     * 
     * @param key 集合名
     * @return java.lang.Long
     */
    public Long sSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * sIsMember
     * <p>
     * 判断集合是否包含某个值
     * </p>
     * 
     * @param key 集合名
     * @param value 值
     * @return java.lang.Boolean
     */
    public Boolean sIsMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    // endregion

    // region hash相关操作

    /**
     * 获取存储在哈希表中指定字段的值
     *
     * @param key
     * @param field
     * @return
     */
    public Object hGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 获取所有给定字段的值
     *
     * @param key
     * @param fields
     * @return
     */
    public List<Object> hMultiGet(String key, Collection<Object> fields) {
        return redisTemplate.opsForHash().multiGet(key, fields);
    }

    /**
     * 添加字段
     *
     * @Param: key
     * @Param: hashKey
     * @Param: value
     * @return: void
     */
    public void hPut(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 删除一个或多个哈希表字段
     *
     * @param key
     * @param fields
     * @return
     */
    public Long hDelete(String key, Object... fields) {
        return redisTemplate.opsForHash().delete(key, fields);
    }

    // endregion
}
