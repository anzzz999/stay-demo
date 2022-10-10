package test;

/**
 * @Author: zhanmingwei
 */
public class Test7 {
    public static void main(String[] args) {
        System.out.print(A.PARAM);
        A.test();
        new A().print();
    }
}
interface I {
    public static final String PARAM = "1";
    default void print() {
        System.out.print(this.getClass().getSimpleName());
    }
}
class A implements I {
    static {
        System.out.print("a");
    }
    public A() {
        System.out.print("b");
    }
    public static void test() {
        System.out.print("c");
    }
}
