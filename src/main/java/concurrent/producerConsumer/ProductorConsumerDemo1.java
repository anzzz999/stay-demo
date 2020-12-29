package concurrent.producerConsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * wait/notifyAll实现生产者-消费者
 */
public class ProductorConsumerDemo1 {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 3; i++) {
            executorService.submit(new Productor(list, 8));
        }
        for (int i = 0; i < 3; i++) {
            executorService.submit(new Customer(list));
        }
    }

    static class Productor implements Runnable {

        private List<Integer> list;
        private int maxLength;

        Productor(List<Integer> list, int maxLength) {
            this.list = list;
            this.maxLength = maxLength;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (list) {
                    try {
                        while (list.size() == maxLength) {
                            System.out.println(Thread.currentThread().getName() + " list已经达到最大容量");
                            list.wait();
                            System.out.println("生产者" + Thread.currentThread().getName() + "  退出wait");

                        }
                        Random random = new Random();
                        int i = random.nextInt();
                        System.out.println("生产者" + Thread.currentThread().getName() + " 生产数据" + i);
                        list.add(i);
                        list.notifyAll();
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Customer implements Runnable {
        List<Integer> list;

        Customer(List list) {
            this.list = list;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    synchronized (list) {
                        while (list.isEmpty()) {
                            System.out.println("消费者" + Thread.currentThread().getName() + "  list为空，进行wait");
                            list.wait();
                            System.out.println("消费者" + Thread.currentThread().getName() + "  退出wait");
                        }
                        Integer element = list.remove(0);
                        System.out.println("消费者" + Thread.currentThread().getName() + "  消费数据：" + element);
                        list.notifyAll();
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}