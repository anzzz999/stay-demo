package concurrent.producerConsumer;

/**
 * 示例中开启了两个线程，一个是WaitThread，另一个是NotifyThread。
 * NotifyThread会先启动，先调用notify方法。
 * 然后WaitThread线程才启动，调用wait方法，但是由于通知过了，wait方法就无法再获取到相应的通知，因此WaitThread会一直在wait方法出阻塞，这种现象就是通知过早的现象。
 */
public class EarlyNotifyDemo1 {
    private static String lockObject = "";

    public static void main(String[] args) {
//        String lock = "";
        WaitThread waitThread = new WaitThread(lockObject);
        NotifyThread notifyThread = new NotifyThread(lockObject);
        notifyThread.start();
//        waitThread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitThread.start();
//        notifyThread.start();
    }

    static class WaitThread extends Thread{
        private String lock;

        WaitThread(String lock){
            this.lock = lock;
        }
        @Override
        public void run(){
            synchronized(lock){
                try {
                System.out.println(Thread.currentThread().getName() + " 进入代码块");
                System.out.println(Thread.currentThread().getName() + " 开始wait");
                    lock.wait();
                    System.out.println(Thread.currentThread().getName() + " 结束wait，被唤醒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class NotifyThread extends Thread{
        private String lock;

        NotifyThread(String lock){
            this.lock = lock;
        }

        @Override
        public void run(){
            synchronized(lock){
                System.out.println(Thread.currentThread().getName() + "  进去代码块");
                System.out.println(Thread.currentThread().getName() + "  开始notify");
                lock.notify();
                System.out.println(Thread.currentThread().getName() + "   结束开始notify");
            }
        }
    }
}
