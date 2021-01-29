package designPatterns.singletonPattern;

/**
 * 双检锁/双重校验锁 + volatile关键字
 */
public class Singleton3 {

    //使用volatile关键字防止重排序，因为 new Instance()是一个非原子操作，可能创建一个不完整的实例
    private static volatile Singleton3 instance;

    public static Singleton3 getInstance(){

        // Double-Check
        if (instance == null){
            synchronized (Singleton3.class){
                if (instance == null){
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
