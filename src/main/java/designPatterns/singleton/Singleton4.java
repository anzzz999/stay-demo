package designPatterns.singleton;

/**
 * 单例
 * 内部静态类
 */
public class Singleton4 {

    private Singleton4() {}

    private static class SingletonHolder {
        private static Singleton4 instance = new Singleton4();
    }

    public static Singleton4 getInstance() {
        return SingletonHolder.instance;
    }


    public static void main(String[] args) {
        Singleton4 instance = Singleton4.getInstance();
        System.out.println(instance);
    }

}
