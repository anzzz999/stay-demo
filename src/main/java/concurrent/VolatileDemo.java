package concurrent;

public class VolatileDemo {

    private static volatile boolean isOver = false;

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (!isOver){
                    System.out.println("hello~");
                }
            }
        });
        thread.start();
        try {
            System.out.println("睡眠1");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isOver = true;
        System.out.println("子线程停了吗？");
        try {
            System.out.println("睡眠2");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束");
    }
}
