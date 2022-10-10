package concurrent.semaphore;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Function;

/**
 * @author zhanmingwei
 */
public class SemaphoreDemo {

    // 对象池
    static class ObjPool<T, R> {
        final List<T> pool;
        final Semaphore semaphore;

        // 构造函数
        ObjPool(int size, T t) {
            pool = new Vector<T>() {
            };
            for (int i = 0; i < size; i++) {
                pool.add(t);
            }
            semaphore = new Semaphore(size);
        }

        // 利用对象池的对象，调用func
        R exec(Function<T, R> fun) {
            T t = null;
            try {
                semaphore.acquire();
                t = pool.remove(0);
                return fun.apply(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                pool.add(t);
                semaphore.release();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        ObjPool<Integer, String> objPool = new ObjPool<>(5, 2);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 11; i++) {
            executorService.execute(() ->{
                objPool.exec(
                        t -> {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(t + " " + Thread.currentThread().getName());
                            return t.toString();
                        }
                );
            });
        }
        executorService.shutdown();
    }


}
