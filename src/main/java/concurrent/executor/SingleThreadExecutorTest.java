package concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorTest {
    public static void main(String[] args) {
        //SingleThreadExecutor 用于串行执行任务的场景，每个任务必须按顺序执行，不需要并发执行。
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        MyRunner myRunner = new MyRunner();
        for (int i = 0; i < 5; i++) {
            executorService.execute(myRunner);
        }
        //Thread.sleep(1000);
        System.out.println("线程任务开始执行");

        executorService.shutdown();
        //Thread.sleep(1000);
        System.out.println("!!");


    }
}
