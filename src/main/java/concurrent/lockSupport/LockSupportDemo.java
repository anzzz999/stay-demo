package concurrent.lockSupport;

import java.util.Date;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupprot是线程的阻塞原语，用来阻塞线程和唤醒线程。
 * 每个使用LockSupport的线程都会与一个许可关联，如果该许可可用，并且可在线程中使用，则调用park()将会立即返回，否则可能阻塞。
 * 如果许可尚不可用，则可以调用 unpark 使其可用。但是注意许可不可重入，也就是说只能调用一次park()方法，否则会一直阻塞。
 */
public class LockSupportDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() ->{
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "被唤醒");
            System.out.println(new Date());
        });
        thread.start();
        try {
            System.out.println(new Date());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LockSupport.unpark(thread);
    }
}
