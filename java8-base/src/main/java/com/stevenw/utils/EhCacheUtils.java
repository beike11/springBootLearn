package com.stevenw.utils;

import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;

/**
 * @author stevenw
 * @date 2019/9/4
 */
public class EhCacheUtils {
    public static String getCacheValue(String cacheValue, String key, String managerName){
        EhCacheCacheManager cacheManager =(EhCacheCacheManager) SpringContextUtil.getBean(managerName);
        Cache cache =  cacheManager.getCache(cacheValue);
        Cache.ValueWrapper value =  cache.get(key);
        return  value.toString();
    }

    public static void removeCache(String cacheValue, String key, String managerName){
        EhCacheCacheManager cacheManager =(EhCacheCacheManager) SpringContextUtil.getBean(managerName);
        Cache cache =  cacheManager.getCache(cacheValue);
        cache.evict(key);
    }
}
