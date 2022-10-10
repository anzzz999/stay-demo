package concurrent;

import lombok.Data;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SynchronizedDemo implements Runnable {

    private static int count = 0;  // ！10000 跟预期不一致
    //    private static AtomicInteger count = new AtomicInteger(0);  //10000 跟预期一致
    private static Object object = new Object();

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
        synchronized (object) {   //锁住类对象
            for (int i = 0; i < 10000; i++) {
                count++;
//            count.getAndIncrement();
            }
        }

    }

    @Test
    public void test1() {
        SynchronizedClass synchronizedClass = new SynchronizedClass();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "[get]:" + synchronizedClass.getVal());
                System.out.println(Thread.currentThread().getName() + "[add]:" + synchronizedClass.addVal());
            }).start();
        }
    }

    public static class SynchronizedClass {
        public int val = 0;

        public int getVal() {
            return val;
        }

        public synchronized int addVal() {
            return val += 1;
        }
    }

    /**
     * 经典死锁
     */
    @Test
    public void test2() throws InterruptedException {
        String a = "a", b = "b";
        Thread thread1 = new Thread(new Lock(a, b));

        Thread thread2 = new Thread(new Lock(b, a));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    @Data
    static class Lock implements Runnable {

        Lock(Object a, Object b) {
            this.a = a;
            this.b = b;
        }

        private Object a;
        private Object b;


        @SneakyThrows
        @Override
        public void run() {
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + "获取" + a);
                Thread.sleep(1000);
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() + "获取" + b);
                }
            }
        }
    }

    /**
     * 引入中间方一次申请所有资源
     */
    @Test
    public void test3() throws Exception {
        Allocator allocator = new Allocator();
        Account account1 = new Account(allocator, 200);
        Account account2 = new Account(allocator, 200);
        Account account3 = new Account(allocator, 200);
        Thread thread1 = new Thread(() -> {
            account1.transfer1(account2, 100);
        });
        Thread thread2 = new Thread(() -> {
            account2.transfer1(account3, 100);
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);
    }

    @Data
    static class Allocator {
        private List<Object> als =
                new ArrayList<>();

        public synchronized boolean apply(Object a, Object b) {
            if (als.contains(a) || als.contains(b)) {
                return false;
            }
            als.add(a);
            als.add(b);
            return true;
        }

        public synchronized void free(Object a, Object b) {
            als.remove(a);
            als.remove(b);
        }

        /** wait/notify 版本 */
        public synchronized boolean apply1(Object a, Object b) {
            while (als.contains(a) || als.contains(b)) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            als.add(a);
            als.add(b);
            return true;
        }

        public synchronized void free1(Object a, Object b) {
            als.remove(a);
            als.remove(b);
            notifyAll();
        }

    }

    @Data
    static class Account {
        public Account(Allocator actr, int balance) {
            this.actr = actr;
            this.balance = balance;
        }

        private int id;
        // actr 应该为单例
        private Allocator actr;
        private int balance;

        // 转账
        void transfer1(Account target, int amt) {
            // 一次性申请转出账户和转入账户，直到成功
            while (!actr.apply(this, target)) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                // 锁定转出账户
                synchronized (this) {
//                    System.out.println(Thread.currentThread().getName() + "锁: " + this.hashCode());
                    Thread.sleep(1000);
                    // 锁定转入账户
                    synchronized (target) {
//                        System.out.println(Thread.currentThread().getName() + "锁: " + target.hashCode());
                        if (this.balance > amt) {
                            this.balance -= amt;
                            target.balance += amt;
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                actr.free(this, target);
            }
        }

        // 转账
        void transfer2(Account target, int amt) {
            Account left = this;
            Account right = target;
            if (this.id > target.id) {
                left = target;
                right = this;
            }
            // 锁定序号小的账户
            synchronized (left) {
                // 锁定序号大的账户
                synchronized (right) {
                    if (this.balance > amt) {
                        this.balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        }
    }
}
