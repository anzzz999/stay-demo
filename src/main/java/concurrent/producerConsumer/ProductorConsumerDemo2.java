package concurrent.producerConsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock中Condition的await/signalAll实现生产者-消费者
 */
public class ProductorConsumerDemo2 {
    //lock
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition full = lock.newCondition();
    private static Condition empty = lock.newCondition();
    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 3; i++) {
            executorService.submit(new Productor(list, 8, lock));
        }
        for (int i = 0; i < 3; i++) {
            executorService.submit(new Customer(list, lock));
        }
    }

    static class Productor implements Runnable {

        private List<Integer> list;
        private int maxLength;
        private Lock lock;

        Productor(List<Integer> list, int maxLength, Lock lock) {
            this.list = list;
            this.maxLength = maxLength;
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                    try {
                        while (list.size() == maxLength) {
                            System.out.println(Thread.currentThread().getName() + " list已经达到最大容量");
                            full.await();
                            System.out.println("生产者" + Thread.currentThread().getName() + "  退出wait");

                        }
                        Random random = new Random();
                        int i = random.nextInt();
                        System.out.println("生产者" + Thread.currentThread().getName() + " 生产数据" + i);
                        list.add(i);
                        empty.signalAll();
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        lock.unlock();
                    }

            }
        }
    }

    static class Customer implements Runnable {
        List<Integer> list;
        Lock lock;

        Customer(List list, Lock lock) {
            this.list = list;
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                        while (list.isEmpty()) {
                            System.out.println("消费者" + Thread.currentThread().getName() + "  list为空，进行wait");
                            empty.await();
                            System.out.println("消费者" + Thread.currentThread().getName() + "  退出wait");
                        }
                        Integer element = list.remove(0);
                        System.out.println("消费者" + Thread.currentThread().getName() + "  消费数据：" + element);
                        full.signalAll();
                        Thread.sleep(500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }
}
