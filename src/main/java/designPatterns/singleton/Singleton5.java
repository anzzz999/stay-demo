package designPatterns.singleton;

/**
 * 单例
 * 枚举
 */
public enum Singleton5 {

    Instance;


    public static void main(String[] args) {
        Singleton5 instance = Singleton5.Instance;
        System.out.println(instance);
    }

}
