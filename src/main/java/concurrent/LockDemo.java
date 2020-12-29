package concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName());
                    //System.out.println(finalI);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
            thread.start();
        }
    }
}