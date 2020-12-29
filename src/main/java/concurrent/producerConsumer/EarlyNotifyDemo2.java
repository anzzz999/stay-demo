package concurrent.producerConsumer;

/**
 * 这段代码只是增加了一个isWait状态变量，NotifyThread调用notify方法后会对状态变量进行更新，在WaitThread中调用wait方法之前会先对状态变量进行判断，
 * 在该示例中，调用notify后将状态变量isWait改变为false，
 * 因此，在WaitThread中while对isWait判断后就不会执行wait方法，从而避免了Notify过早通知造成遗漏的情况。
 */

public class EarlyNotifyDemo2 {
    private static String lockObject = "";
    private static boolean isWait = true;

    public static void main(String[] args) {
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
                    System.out.println(isWait);
                    while (isWait){
                        System.out.println(Thread.currentThread().getName() + " 进入代码块");
                        System.out.println(Thread.currentThread().getName() + " 开始wait");
                        lock.wait();
                        System.out.println(Thread.currentThread().getName() + " 结束wait，被唤醒");
                    }
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
                lock.notifyAll();
                isWait = false;
                System.out.println(isWait);
                System.out.println(Thread.currentThread().getName() + "   结束开始notify");
            }
        }
    }
}
