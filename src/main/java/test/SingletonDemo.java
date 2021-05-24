package test;

public class SingletonDemo {

    //饿汉式
//    private SingletonDemo(){}
//
//    private static SingletonDemo  singletonDemo = new SingletonDemo();
//
//    public static SingletonDemo getInstance(){
//        return singletonDemo;
//    }

    //懒汉式
    private SingletonDemo(){}

    //volatile 防止指令重排序
    private static volatile SingletonDemo singletonDemo;

    public static SingletonDemo getInstance(){
        if (singletonDemo == null){
            //synchronized 保证只有单线程可以访问
            synchronized(SingletonDemo.class){
                if (singletonDemo == null){
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }
}
