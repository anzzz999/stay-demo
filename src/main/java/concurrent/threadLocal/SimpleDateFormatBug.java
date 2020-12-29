package concurrent.threadLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleDateFormatBug {

    static ExecutorService pool = Executors.newFixedThreadPool(10);
    //会产生多线程问题
//    public static void main(String[] args) {
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyyMMdd");
//        for (int i = 0; i < 20; i++) {
//            pool.execute(()->{
//                try {
//                    Date date = simpleDateFormat.parse("20201227");
//                    System.out.println(Thread.currentThread()+"  "+date);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//    pool.shutdown();
//    }

    //如果当前线程不持有SimpleDateformat对象实例，那么就新建一个并把它设置到当前线程中，如果已经持有，就直接使用。
    private static ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            pool.submit(new DateUtil("2019-11-25 09:00:" + i % 60));
        }
        pool.shutdown();
    }

    static class DateUtil implements Runnable{
        private String date;

        public DateUtil(String date) {
            this.date = date;
        }
        @Override
        public void run() {
            if (sdf.get() == null){
                sdf.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            }else {
                try {
                    Date date = sdf.get().parse(this.date);
                    System.out.println(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
