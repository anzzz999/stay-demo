package concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        //ScheduledThreadPoolExecutor 用于需要多个后台线程执行周期任务，同时需要限制线程数量的场景。
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        MyRunner myRunner = new MyRunner();
        for (int i = 0; i < 5; i++) {
        // 参数1:目标对象,参数2:隔多长时间开始执行线程,参数3:执行周期,参数4:时间单位
            scheduledExecutorService.scheduleAtFixedRate(myRunner, 1, 2, TimeUnit.SECONDS);
        }
        System.out.println(Thread.currentThread().getName() + "线程任务开始执行");
    }
}
