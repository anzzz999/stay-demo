package concurrent.test;


/**
 * 编写一个有两个线程的程序，第一个线程用来计算2～100000之间的素数的个数，第二个线程用来计算100000～200000之间的素数的个数，最后输出结果
 */
public class primeNumber {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            int count = 0;

            for (int i = 2; i < 10000; i++) {
                boolean is = true;
                for (int j = 2; j < i; j++){
                    if (i%j == 0){
                        if (i%j==0){
                            is=false;
                            break;
                        }
                    }
                }
                if (is){
                    count++;
                }
            }
            System.out.println(Thread.currentThread().getName()+":0到100000之间有"+count+"个素数");
            System.out.println(System.currentTimeMillis());
        });
        Thread thread2 = new Thread(() -> {
            int count = 0;
            for (int i = 10000; i < 20000; i++) {
                boolean is = true;
                for (int j = 2; j < i; j++){
                    if (i%j == 0){
                        if (i%j==0){
                            is=false;
                            break;
                        }
                    }
                }
                if (is){
                    count++;
                }
            }
            System.out.println(Thread.currentThread().getName()+":100000到200000之间有"+count+"个素数");
            System.out.println(System.currentTimeMillis());
        });
        System.out.println(System.currentTimeMillis());
        thread1.start();
        thread2.start();
//        thread1.run();
//        thread2.run();
    }
}
