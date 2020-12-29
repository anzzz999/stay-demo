package concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

// 原子更新基本类型
        //AtomicBoolean：以原子更新的方式更新boolean；
        //AtomicInteger：以原子更新的方式更新Integer;
        //AtomicLong：以原子更新的方式更新Long；
public class AtomicIntegerDemo {
    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        System.out.println(atomicInteger.getAndAdd(1));
        System.out.println(atomicInteger.addAndGet(1));
        System.out.println(atomicInteger.get());
    }
}