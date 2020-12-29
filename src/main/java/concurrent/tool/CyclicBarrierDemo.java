package concurrent.tool;

import java.util.concurrent.*;

//循环栅栏CyclicBarrier
public class CyclicBarrierDemo {
/*    //等到所有的线程都到达指定的临界点
    await() throws InterruptedException, BrokenBarrierException

    //与上面的await方法功能基本一致，只不过这里有超时限制，阻塞等待直至到达超时时间为止
    await(long timeout, TimeUnit unit) throws InterruptedException,
            BrokenBarrierException, TimeoutException

    //获取当前有多少个线程阻塞等待在临界点上
    int getNumberWaiting()

    //用于查询阻塞等待的线程是否被中断
    boolean isBroken()*/
    private static CyclicBarrier cyclicBarrier= new  CyclicBarrier(6, () -> System.out.println("所有运动员入场，裁判员一声令下！！！！！"));

    public static void main(String[] args) {
        System.out.println("运动员准备进场，全场欢呼............");
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 6; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                    System.out.println(Thread.currentThread().getName() + "运动员准备进场~");
                        cyclicBarrier.await();
                        System.out.println(Thread.currentThread().getName() + "  运动员出发");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
    }
}
