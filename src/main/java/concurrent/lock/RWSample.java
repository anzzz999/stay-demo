package concurrent.lock;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWSample {
    private final Map<String, String> m = new TreeMap<>();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock read = rwl.readLock();
    private final Lock write = rwl.writeLock();

    public String get(String key) throws InterruptedException {
        Thread.sleep(900);
        read.lock();
        System.out.println("读锁锁定！");
        Thread.sleep(5000);
        try {
            return m.get(key);
        } finally {
            read.unlock();
        }
    }
    public String put(String key, String entry) throws InterruptedException {
        Thread.sleep(1000);
        write.lock();
        System.out.println("写锁锁定！");
        Thread.sleep(5000);
        try {
            return m.put(key, entry);
        } finally {
            write.unlock();
        }
    }
    // …
    public static void main(String[] args) {
        try {
            RWSample rwSample = new RWSample();
            Thread thread = new Thread(() -> {
                try {
                    System.out.println(rwSample.put("1", "1"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread thread1 = new Thread(() -> {
                try {
                    System.out.println(rwSample.get("1"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            thread1.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void test(){
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    }
}