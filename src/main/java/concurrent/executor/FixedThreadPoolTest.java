package concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTest {
    public static void main(String[] args) {
        //FixedThreadPool 用于负载比较重的服务器，为了资源的合理利用，需要限制当前线程数量。
        //FixedThreadPool 的核心线程数和最大线程数都是指定值，也就是说当线程池中的线程数超过核心线程数后，任务都会被放到阻塞队列中。
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        MyRunner myRunner = new MyRunner();
        for (int i = 0; i < 5; i++) {
            executorService.execute(myRunner);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "线程任务开始执行");
        executorService.shutdown();
    }
}
