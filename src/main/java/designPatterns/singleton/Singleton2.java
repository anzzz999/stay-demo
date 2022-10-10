package designPatterns.singleton;

/**
 * 单例
 * 饿汉式，线程安全
 */
public class Singleton2 {
    private static  Singleton2 instance = new Singleton2();

    public static  Singleton2 getInstance(){
        return instance;
    }

}
