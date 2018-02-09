package com.qzdsoft.eshop.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yang
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     *
     * @param key
     * @param value
     */
    public void set(final String key, final String value) {
        this.set(key, value, -1);
    }

    /**
     * setex操作
     *
     * @param key
     *      键
     * @param value
     *      值
     * @param cacheSeconds
     *      超时时间，0为不超时
     * @return
     */
    public void set(final String key, final String value, final Integer cacheSeconds) {
        ValueOperations<String, String> vo = redisTemplate.opsForValue();
        if(null != cacheSeconds && cacheSeconds.intValue() > 0){
            vo.set(key, value, cacheSeconds, TimeUnit.SECONDS);
        } else {
            vo.set(key, value);
        }
    }

    /**
     * get操作
     *
     * @param key
     *      键
     * @return 值
     */
    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }


    /**
     * 删除
     *
     * @param key
     */
    public void del(String key){
        redisTemplate.delete(key);
    }


}
