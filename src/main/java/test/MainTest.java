package test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhanmingwei
 *
 * https://mp.weixin.qq.com/s/KDUccdLALWdjNBrFjVR74Q 真是绝了！这段被JVM动了手脚的代码！
 *
 * 所以方法调用、循环跳转、异常跳转这些位置都可能会设置有安全点，但是HotSpot虚拟机为了避免安全点过多带来过重的负担，
 * 对循环还有一项优化措施，认为循环次数较少的话，执行时间应该也不会太长，
 * 所以使用int类型或范围更小的数据类型作为索引值的循环默认是不会被放置安全点的。
 * 这种循环被称为可数循环（Counted Loop）。
 * 相对应地，使用 long 或者范围更大的数据类型作为索引值的循环就被称为不可数循环（Uncounted Loop），将会被放置安全点。
 * 通常情况下这个优化措施是可行的，但循环执行的时间不单单是由其次数决定，如果循环体单次执行就特别慢，那即使是可数循环也可能会耗费很多的时间。
 *
 * HotSpot原本提供了-XX:+UseCountedLoopSafepoints 参数去强制在可数循环中也放置安全点，
 * 不过这个参数在 JDK 8 下有 Bug，有导致虚拟机崩溃的风险，所以就不得不找到 RpcServer 线程里面的缓慢代码来进行修改。
 *
 */
public class MainTest {

    public static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
//            for (int i = 0; i < 1000000000; i++) {
            for (long i = 0; i < 1000000000; i++) {
                num.getAndAdd(1);
            }
            System.out.println(Thread.currentThread().getName() + "执行结束!");
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        TimeInterval timer = DateUtil.timer();
        timer.start();
        Thread.sleep(1000);

        System.out.println("num = " + num+ " times ="+ timer.interval());
    }
}
