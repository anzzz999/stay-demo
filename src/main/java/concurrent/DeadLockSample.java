package concurrent;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description: 死锁示例
 */
public class DeadLockSample extends Thread {
    private String first;
    private String second;

    public DeadLockSample(String name, String first, String second){
        super(name);
        this.first = first;
        this.second = second;
    }
    @Override
    public void run() {
        try {
            synchronized (first) {
                System.out.println(this.getName() + " obtained: " + first);
                Thread.sleep(1000L);
                synchronized (second) {
                    System.out.println(this.getName() + " obtained: " + second);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // ------ThreadMXBean死锁检测------

        ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
        Runnable dlCheck = new Runnable() {
            @Override
            public void run() {
                long[] threadIds = mbean.findDeadlockedThreads();
                if (threadIds != null) {
                    ThreadInfo[] threadInfos = mbean.getThreadInfo(threadIds);
                    System.out.println("Detected deadlock threads:");
                    for (ThreadInfo threadInfo : threadInfos) {
                        System.out.println(threadInfo.getThreadName());
                    }
                }
            }
        };
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        // 稍等5秒，然后每10秒进行一次死锁扫描
        scheduler.scheduleAtFixedRate(dlCheck, 5L, 10L, TimeUnit.SECONDS);

        // ------ThreadMXBean死锁检测------

        String lockA = "lockA";
        String lockB = "lockB";
        DeadLockSample thread1 = new DeadLockSample("Thread1", lockA, lockB);
        DeadLockSample thread2 = new DeadLockSample("Thread2", lockB, lockA);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }


}
