package concurrent.future;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " call()方法执行中...");
        Thread.sleep(1000);
        return 1;
    }
}
