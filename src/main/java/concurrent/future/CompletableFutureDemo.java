package concurrent.future;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zhanmingwei
 *
 * 从Java 8开始引入了CompletableFuture，它针对Future做了改进，
 * 可以传入回调对象，当异步任务完成或者发生异常时，自动调用回调对象的回调方法。
 * 除了anyOf()可以实现“任意个CompletableFuture只要一个成功”，allOf()可以实现“所有CompletableFuture都必须成功”，
 * 这些组合操作可以实现非常复杂的异步流程控制。
 *
 * 最后我们注意CompletableFuture的命名规则：
 * xxx()：表示该方法将继续在已有的线程中执行；
 * xxxAsync()：表示将异步在线程池中执行。
 *
 * 小结：
 * CompletableFuture可以指定异步处理流程：
 * thenAccept()处理正常结果；
 * exceptional()处理异常结果；
 * thenApplyAsync()用于串行化另一个CompletableFuture；
 * anyOf()和allOf()用于并行化多个CompletableFuture。
 *
 */
public class CompletableFutureDemo {

    private ExecutorService threadPool = Executors.newFixedThreadPool(10);

    // 同时从新浪和网易查询证券代码，只要任意一个返回结果，就进行下一步查询价格，查询价格也同时从新浪和网易查询，只要任意一个返回结果
    @Test
    public void Test1() throws InterruptedException {
        // 两个CompletableFuture执行异步查询:
        CompletableFuture<String> cfQueryFromSina = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油", "https://finance.sina.com.cn/code/");
        }, threadPool);

        CompletableFuture<String> cfQueryFrom163  = CompletableFuture
                .supplyAsync(() -> queryCode("中国石油", "https://money.163.com/code/"), threadPool);

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfQueryFromSina, cfQueryFrom163);

        // 两个CompletableFuture执行异步查询:
        CompletableFuture<Double> cfFetchFromSina = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://finance.sina.com.cn/price/");
        }, threadPool);

        CompletableFuture<Object> cfFetchFrom163 = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://money.163.com/price/");
        }, threadPool);


        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(cfFetchFromSina, cfFetchFrom163);


        // 最终结果:
        cfFetch.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }


    @Test
    public void Test2() throws InterruptedException, ExecutionException {
        // 两个CompletableFuture执行异步查询:
        CompletableFuture<String> cfQueryFromSina = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油", "https://finance.sina.com.cn/code/");
        }, threadPool);

        CompletableFuture<String> cfQueryFrom163  = CompletableFuture
                .supplyAsync(() -> queryCode("中国石油", "https://money.163.com/code/"), threadPool);

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Void> cfQuery = CompletableFuture.allOf(cfQueryFromSina, cfQueryFrom163);
        System.out.println(cfQueryFromSina.get() + " " + cfQueryFrom163.get());

        // 两个CompletableFuture执行异步查询:
        CompletableFuture<Double> cfFetchFromSina = cfQueryFromSina.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://finance.sina.com.cn/price/");
        }, threadPool);

        CompletableFuture<Object> cfFetchFrom163 = cfQueryFrom163.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://money.163.com/price/");
        }, threadPool);


        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(cfFetchFromSina, cfFetchFrom163);


        // 最终结果:
        cfFetch.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    static String queryCode(String name, String url) {
        System.out.println("query code from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice(String code, String url) {
        System.out.println("query price from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }
}


