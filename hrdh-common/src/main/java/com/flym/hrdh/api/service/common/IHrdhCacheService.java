package com.flym.hrdh.api.service.common;

import java.util.Map;
import java.util.Set;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:系统缓存管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface IHrdhCacheService {

    /**
     * 设置缓存-String
     * @param key
     * @param value
     */
    void setCache(String key, String value);

    /**
     * 设置缓存以及时间-String
     * @param key
     * @param value
     * @param timeout 单位：秒
     */
    void setCache(String key, String value, long timeout);

    /**
     * 延长缓存时间
     * @param key
     * @param timeout 单位：秒
     */
    void setCacheTime(String key, long timeout);

    /**
     * 获取缓存-String
     * @param key
     * @return
     */
    String getCache(String key);

    /**
     * 删除缓存-String
     * @param key
     */
    void delCache(String key);

    /**
     * 设置缓存-Hash
     * @param key
     * @param hashKey
     * @param value
     */
    void setHashCache(String key, String hashKey, String value);

    /**
     * 判断缓存key是否存在-Hash
     * @param key
     * @param hashKey
     * @return
     */
    boolean hasKey(String key, String hashKey);

    /**
     * 获取缓存-
     * @param key
     * @return
     */
    Map<String,String> getHashCache(String key);

    /**
     * 删除缓存
     * @param key key
     * @param hashKey hash的key
     * @return
     */
    void delHashCache(String key, String hashKey);

    /**
     * 获取缓存-根据key
     * @param key key
     * @param hashKey hash的key
     * @return
     */
    String getHashCacheValue(String key, String hashKey);

    /**
     * 根据模糊key获取缓存集合
     * @param vagueKey
     * @return
     */
    Set<String> getVagueKey(String vagueKey);

    /**
     * 根据模糊建删除缓存
     * @param vagueKey
     */
    void delVagueKey(String vagueKey);

    /**
     * 自增
     * @param key
     * @return
     */
    long increase(String key);

    /**
     * 保存
     * @param key
     * @param value
     */
    void setCache(String key, Integer value);

    /**
     * 获取缓存-int
     * @param key
     * @return
     */
    int getIntCache(String key);

}
