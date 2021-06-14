package test;

import org.junit.jupiter.api.Test;

public class StaticDemo {

    public static void staticMethod() {
        // 先创建实例对象，再调用非静态方法
        StaticDemo demo = new StaticDemo();
        demo.instanceMethod();
    }
    public void instanceMethod() {
        System.out.println("非静态方法");
    }

    @Test
    public void Test(){
        StaticDemo.staticMethod();
    }
}
