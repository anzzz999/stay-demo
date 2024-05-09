package concurrent.aqs.demo;

/**
 * @author zhanmingwei
 */
public class MutexDemo {
    private static Mutex mutex = new Mutex();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                mutex.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " get the lock");
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + " release the lock");
                    mutex.unlock();
                }
            });
            thread.start();
        }
    }
}
