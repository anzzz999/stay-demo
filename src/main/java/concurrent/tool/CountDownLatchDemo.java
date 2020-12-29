package concurrent.tool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

//倒计时器CountDownLatch
public class CountDownLatchDemo {
    /**
     * CountDownLatch的方法不是很多，将它们一个个列举出来：
     *
     * await() throws InterruptedException：调用该方法的线程等到构造方法传入的N减到0的时候，才能继续往下执行；
     * await(long timeout, TimeUnit unit)：与上面的await方法功能一致，只不过这里有了时间限制，调用该方法的线程等到指定的timeout时间后，不管N是否减至为0，都会继续往下执行；
     * countDown()：使CountDownLatch初始值N减1；
     * long getCount()：获取当前CountDownLatch维护的值；
     */
    private static CountDownLatch startLatch = new CountDownLatch(1);

    private static CountDownLatch endLatch = new CountDownLatch(6);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        System.out.println("比赛准备开始，请运动员进行准备");
        for (int i = 0; i < 6; i++) {
            executorService.execute(() -> {
                try {
                System.out.println(Thread.currentThread().getName() + " 上赛道,等待裁判员响哨");
                    // 确保所有运动员准备完毕
                    Thread.sleep(1000);
                    // 等待吹哨（等startLatch的值变成0）
                    startLatch.await();

                    System.out.println(Thread.currentThread().getName() + "正在全力冲刺");
                    //减endLatch值
                    endLatch.countDown();
                    System.out.println(Thread.currentThread().getName() + "到达终点");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        Thread.sleep(2000);
        //将executorService转换为ThreadPoolExecutor,ThreadPoolExecutor有方法 getActiveCount()可以得到当前活动线程数
        int activeCount = ((ThreadPoolExecutor) executorService).getActiveCount();
        if (activeCount == 6){
            System.out.println("裁判员吹响口哨");
            //让运动员开始跑
            startLatch.countDown();
            //等运动员跑完（等endLatch的值变成0）
            endLatch.await();
            System.out.println("比赛结束！");
        }

        executorService.shutdown();
    }
}
