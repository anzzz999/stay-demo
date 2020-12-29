package concurrent.producerConsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ProductorConsumerDmoe3 {

    private static BlockingQueue<Integer> queue = new LinkedBlockingQueue(10);

    private static ExecutorService pool = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            pool.submit(new Productor(queue));
        }
        for (int i = 0; i < 3; i++) {
            pool.submit(new Customer(queue));
        }
    }

    static class Productor implements Runnable {

        BlockingQueue<Integer> queue;

        Productor(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Random random = new Random();
                    int i = random.nextInt();
                    queue.put(i);
                    System.out.println("生产者" + Thread.currentThread().getName() + " 生产数据" + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Customer implements Runnable {

        BlockingQueue<Integer> queue;

        Customer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Integer element = queue.take();
                    System.out.println("消费者" + Thread.currentThread().getName() + "  消费数据：" + element);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
