package concurrent.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Semaphore可以理解为信号量，用于控制资源能够被并发访问的线程数量，以保证多个线程能够合理的使用特定资源。
 * Semaphore就相当于一个许可证，线程需要先通过acquire方法获取该许可证，该线程才能继续往下执行，否则只能在该方法出阻塞等待。
 */
public class SemaphoreDemo {
    private static Semaphore semaphore = new Semaphore(5,false);  //最多五个并发访问，并且不是公平锁
//    public static Semaphore semaphore = new Semaphore(5,true);  //最多五个并发访问，并且是公平锁

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
//            int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
//                        Thread.sleep(100 * finalI);  //按顺序
                        System.out.println("学生" + Thread.currentThread().getName() + " 上台");
                        semaphore.acquire();  //获取资源
                        System.out.println("学生" + Thread.currentThread().getName() + " 拿到粉笔");
                        System.out.println("学生" + Thread.currentThread().getName() + " 填表格ing...");
                        Thread.sleep(3000);
                        semaphore.release();//释放资源
                        System.out.println("学生" + Thread.currentThread().getName() + " 填写完表格，归还了笔！" );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
    }
}
