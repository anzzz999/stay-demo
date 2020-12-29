package concurrent;

/**
 * 要求创建三个线程，输出1-75，
 * 最开始第一个线程输出1-5，第二个输出6-10，第三个输出11-15
 * 接着再第一个线程输出16-20...就这样循环下去，直到打印出75个数
 *
 * @author qiaoxueshi
 */
public class print1To75Demo {

    static class Printer implements Runnable {

        static int start = 1;
        static final int END = 75;
        int id;
        Object object;

        //锁 Printer.class
        Printer(int id) {
            this.id = id;
        }

        //锁 object 对象
        Printer(int id ,Object object) {
            this.id = id;
            this.object = object;
        }

        @Override
        public void run() {
            //锁住Printer类
            synchronized (Printer.class) {
//            synchronized (object) {
                //判断哪个线程进入
                while (start <= END) {
                    if (start / 5 % 3 == id) {
                        //打印数字
                        System.out.print(id + ": ");
                        for (int i = 0; i < 5; i++) {
                            System.out.print(start++ + " ");
                        }
                        System.out.println(" ");
                        //唤醒所有等待Printer的线程
                        Printer.class.notifyAll();
//                        object.notifyAll();
                    } else {
                        try {
                            //抢到cpu的线程 不符合条件就让出cpu
                            Printer.class.wait();
//                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }

        public static void main(String[] args) {
            new Thread(new Printer(0)).start();
            new Thread(new Printer(1)).start();
            new Thread(new Printer(2)).start();

            //使用Object
//            Object o = new Object();
//            new Thread(new Printer(0 ,o)).start();
//            new Thread(new Printer(1 ,o)).start();
//            new Thread(new Printer(2 ,o)).start();
        }

        /**
         * 0: 1 2 3 4 5
         * 1: 6 7 8 9 10
         * 2: 11 12 13 14 15
         * 0: 16 17 18 19 20
         * 1: 21 22 23 24 25
         * 2: 26 27 28 29 30
         * 0: 31 32 33 34 35
         * 1: 36 37 38 39 40
         * 2: 41 42 43 44 45
         * 0: 46 47 48 49 50
         * 1: 51 52 53 54 55
         * 2: 56 57 58 59 60
         * 0: 61 62 63 64 65
         * 1: 66 67 68 69 70
         * 2: 71 72 73 74 75
         */
    }
}
