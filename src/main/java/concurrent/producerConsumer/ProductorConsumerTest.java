package concurrent.producerConsumer;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductorConsumerTest {


    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        ExecutorService pool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 3; i++) {
            pool.execute(new Producter(queue, 8));
        }
        for (int i = 0; i < 3; i++) {
            pool.execute(new Customer(queue));
        }
    }

    static class Producter implements Runnable{
        private ConcurrentLinkedQueue<Integer> queue;
        private int maxNumber;

        Producter(ConcurrentLinkedQueue<Integer> queue, int maxNumber ){
            this.queue = queue;
            this.maxNumber = maxNumber;
        }

        @Override
        public void run() {
            while (true){
                synchronized(queue){
                    try {
                        while (queue.size() == maxNumber){
                            queue.wait();
                        }
                        Random random = new Random();
                        int i = random.nextInt();
                        queue.add(i);
                        System.out.println("生产者" + Thread.currentThread().getName() + " 生产数据" + i);
                        queue.notifyAll();
                        Thread.sleep(500);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }

        }
    }

    static class Customer implements Runnable{

        private ConcurrentLinkedQueue<Integer> queue;

        Customer(ConcurrentLinkedQueue queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true){
                synchronized (queue){
                    try {
                        while (queue.isEmpty()){
                            queue.wait();
                        }
                        Integer element = queue.poll();
                        System.out.println("消费者" + Thread.currentThread().getName() + "  消费数据：" + element);
                        queue.notifyAll();
                        Thread.sleep(500);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
