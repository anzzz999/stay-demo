package concurrent.executor;

public class MyRunner implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "正在运行中...");
    }
}
