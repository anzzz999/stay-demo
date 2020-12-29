package concurrent.tool;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *Exchanger是一个用于线程间协作的工具类，用于两个线程间交换数据。它提供了一个交换的同步点，在这个同步点两个线程能够交换数据。
 * 交换数据是通过exchange方法来实现的，如果一个线程先执行exchange方法，那么它会同步等待另一个线程也执行exchange方法，这个时候两个线程就都达到了同步点，两个线程就可以交换数据。
 * 当两个线程都到达调用exchange方法的同步点的时候，两个线程就能交换彼此的数据。
 */
public class ExchangerDemo {

    private static Exchanger<String> exchanger = new Exchanger<>();

    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        executorService.execute(() ->{
            try {
                String grils = exchanger.exchange("我其实暗恋你很久了......");
                System.out.println("女孩说:" + grils);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() ->{
            try {
                System.out.println("女孩缓缓走出教室...");
                Thread.sleep(3000);
                String boys = exchanger.exchange("我也很喜欢你......");
                System.out.println("男孩说:" + boys);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }
}
