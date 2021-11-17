package cache.caffeine;

import cn.hutool.core.collection.CollUtil;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CaffeineDemo {

    /**
     * 手动加载
     **/
    @Test
    public void test() {
        Cache<String, String> manualCache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .maximumSize(100_000)
                .build();
        ;

        String key = "an_zzz";
        // 根据key查询一个缓存，如果没有返回NULL
        Object value = manualCache.getIfPresent(key);
        log.info("当前value值为：{}", value);

        // 根据Key查询一个缓存，如果没有调用createExpensiveGraph方法，并将返回值保存到缓存。
        value = manualCache.get(key, k -> createExpensiveGraph(k));
        log.info("当前value值为：{}", value);

        // 将一个值放入缓存，如果以前有值就覆盖以前的值
        manualCache.put(key, "bad");
        log.info("当前value值为：{}", manualCache.getIfPresent(key));

        // 删除一个缓存
        manualCache.invalidate(key);
        log.info("当前value值为：{}", manualCache.getIfPresent(key));

        // 可以使用Cache.asMap() 方法获取ConcurrentMap进而对缓存进行一些更改
        ConcurrentMap<String, String> map = manualCache.asMap();
        map.put("hello", " is map");

        log.info("当前value值为：{}", manualCache.getIfPresent("hello"));
        manualCache.invalidate(key);
    }


    /**
     * 同步加载
     **/
    @Test
    public void test1() {
        LoadingCache<String, String> loadingCache = Caffeine.newBuilder()
                .maximumSize(10_000)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .build(this::createExpensiveGraph);
        String key = "name1";
        // 采用同步方式去获取一个缓存和上面的手动方式是一个原理。在build Cache的时候会提供一个createExpensiveGraph函数。
        // 查询并在缺失的情况下使用同步的方式来构建一个缓存
        String value = loadingCache.get(key);
        log.info("当前key为：{}, value值为：{}", key, value);

        // 获取组key的值返回一个Map
        List<String> keys = new ArrayList<>();
        keys.add("hello");
        keys.add("hello1");

        // 默认情况下，getAll将会对缓存中没有值的key分别调用CacheLoader.load方法来构建缓存的值。
        // 我们可以重写CacheLoader.loadAll方法来提高getAll的效率。
        Map<String, String> graphs = loadingCache.getAll(keys); // name1的key消失了
        log.info("map中值为：{}", graphs);
    }

    public String createExpensiveGraph(String k) {
        return "good";
    }

    /**
     * 异步加载
     * AsyncLoadingCache是继承自LoadingCache类的，异步加载使用Executor去调用方法并返回一个CompletableFuture。
     * 异步加载缓存使用了响应式编程模型。
     * 默认使用ForkJoinPool.commonPool()来执行异步线程，但是我们可以通过Caffeine.executor(Executor) 方法来替换线程池。
     **/
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        AsyncLoadingCache<String, String> asyncLoadingCache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .maximumSize(10_000)
                .buildAsync(this::createExpensiveGraph);

        String key = "name";

        // 查询并在缺失的情况下使用异步的方式来构建缓存
        CompletableFuture<String> stringCompletableFuture = asyncLoadingCache.get(key);
        log.info("当前key为：{}, value值为：{}", key, stringCompletableFuture.get());

        // 查询一组缓存并在缺失的情况下使用异步的方式来构建缓存
        List<String> keys = new ArrayList<>();
        keys.add("hello1");
        keys.add("hello2");
        CompletableFuture<Map<String, String>> cache = asyncLoadingCache.getAll(keys);
        log.info("当前value值为：{}", cache.get());

        // 异步转同步
        LoadingCache<String, String> synchronousCache = asyncLoadingCache.synchronous();
        log.info("当前cache值为：{}", synchronousCache);
    }

}
