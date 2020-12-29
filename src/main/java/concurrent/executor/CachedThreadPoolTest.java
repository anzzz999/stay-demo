package concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolTest {
    public static void main(String[] args) {
        //CachedThreadPool 用于并发执行大量短期的小任务，或者是负载较轻的服务器。
        //CachedThreadPool 没有核心线程，非核心线程数无上限，也就是全部使用外包，但是每个外包空闲的时间只有 60 秒，超过后就会被回收。
        ExecutorService executorService = Executors.newCachedThreadPool();
        MyRunner myRunner = new MyRunner();
        for (int i = 0; i < 5; i++) {
            executorService.execute(myRunner);
        }
        System.out.println(Thread.currentThread().getName() + "线程任务开始执行");
        executorService.shutdown();
    }
}
