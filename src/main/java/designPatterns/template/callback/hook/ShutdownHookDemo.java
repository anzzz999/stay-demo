package designPatterns.template.callback.hook;

/**
 * @Author: zhanmingwei
 *
 * JVM提供了Runtime.addShutdownHook(Thread hook)⽅法，可以注册⼀个JVM关闭的Hook。
 * 当应⽤程序关闭的时候，JVM会⾃动调⽤Hook代码
 */
public class ShutdownHookDemo {

    public static class ShutdownHook extends Thread {
        @Override
        public void run() {
            System.out.println("I am called during shutting down.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(1000);
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());
    }
}
