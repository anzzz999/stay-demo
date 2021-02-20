package test;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 多线程断点调试
 *
 * 断点上直接右键设置：把 All 改为 Thread。如果点了 Make Default ，那么后续加上的断点都是 Thead 设置，之前加上的断点不影响。
 */
public class TestDemo {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread1 = new Thread(myRunnable, "线程1");
        Thread thread2 = new Thread(myRunnable, "线程2");
        Thread thread3 = new Thread(myRunnable, "线程3");

        thread1.start();
        thread2.start();
        thread3.start();
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread.getName() + "-------------进入");

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(currentThread.getName() + "-------------离开");
            }

        }
    }
}
