package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:系统缓存管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: juehui.xie $
 * @version $Revision: 1.0.0 $
 */

@Service(version = "1.0.0")
public class HrdhCacheServiceImpl implements IHrdhCacheService {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void setCache(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void setCache(String key, String value, long timeout){
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    @Override
    public void setCacheTime(String key, long timeout){
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    @Override
    public String getCache(String key) {
        if(redisTemplate.opsForValue().get(key)!=null){
            return redisTemplate.opsForValue().get(key).toString();
        }
        return null;
    }

    @Override
    public void delCache(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void setHashCache(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public boolean hasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    @Override
    public Map<String, String> getHashCache(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public void delHashCache(String key, String hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    @Override
    public String getHashCacheValue(String key, String hashKey){
        String value = "";
        if(redisTemplate.opsForHash().get(key, hashKey) != null){
            value = redisTemplate.opsForHash().get(key, hashKey).toString();
        }
        return value;
    }

    @Override
    public Set<String> getVagueKey(String vagueKey) {
        return redisTemplate.keys(vagueKey);
    }

    @Override
    public void delVagueKey(String vagueKey) {
        Set<String> set = redisTemplate.keys(vagueKey);
        Iterator<String> it = set.iterator();
        if(it != null){
            while(it.hasNext()){
                String keyStr = it.next();
                redisTemplate.delete(keyStr);
            }
        }
    }

    @Override
    public long increase(String key){
        long value = 0;
            value = redisTemplate.opsForValue ().increment(key, 1);
        return value;
    }

    @Override
    public void setCache(String key, Integer value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public int getIntCache(String key) {
        if(redisTemplate.opsForValue().get(key)!=null){
            return Integer.parseInt(redisTemplate.opsForValue().get(key).toString());
        }
        return 0;
    }
}
