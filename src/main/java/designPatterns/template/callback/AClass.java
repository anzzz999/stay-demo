package designPatterns.template.callback;

/**
 * @Author: zhanmingwei
 *
 * 相对于普通的函数调用来说，替代是一种双向调用关系。
 * A类预先注册某个函数F到B类，A类在调用B类的P函数的时候，B类反过来调用A类注册给它的F函数。这里的F函数就是“回调函数”。
 * A调用B，B反过来又调用A，这种调用机制就叫作“回调”。
 */
public class AClass {
    public static void main(String[] args) {
        BClass bClass = new BClass();
        bClass.process(new ICallBack() {
            @Override
            public void methodToCallBack() {
                System.out.println("Call back me ");
            }
        });
    }
}
