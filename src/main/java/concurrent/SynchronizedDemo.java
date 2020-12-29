package concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedDemo implements Runnable {

    private static int count = 0;  // ！10000 跟预期不一致
//    private static AtomicInteger count = new AtomicInteger(0);  //10000 跟预期一致

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new SynchronizedDemo());
            thread.start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result: " + count);
    }


    public void run() {
        synchronized (SynchronizedDemo.class){   //锁住类对象
            for (int i = 0; i < 10000; i++) {
                count++;
//            count.getAndIncrement();
            }
        }

    }
}
