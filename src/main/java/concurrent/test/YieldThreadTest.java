package concurrent.test;

//测试yield与优先级：当某个线程调用了yield()方法暂停之后，只有优先级与当前线程相同，或者优先级比当前线程更高的处于就绪状态的线程才会获得执行机会
class Yield implements Runnable{
    int i=0;
    @Override
    public void run(){
        for(;i<50;i++){
            System.out.println(Thread.currentThread().getName()+"->"+i);
            //当i等于20时，当前线程让步，让线程调度器重新调度
            if(i==10){
                Thread.yield();
            }
        }
    }
}
public class YieldThreadTest extends Thread{
    public YieldThreadTest(Runnable runnable,String name){
        super(runnable,name);
    }
    public static void main(String[] args) {
        Yield yd=new Yield();

        YieldThreadTest ytt1=new YieldThreadTest(yd,"高优先级线程");
        ytt1.setPriority(Thread.MAX_PRIORITY);//设置优先级最高
        ytt1.start();

        YieldThreadTest ytt2=new YieldThreadTest(yd,"低优先级线程");
        ytt2.setPriority(Thread.MIN_PRIORITY);//设置优先级最低
        ytt2.start();


    }

}
