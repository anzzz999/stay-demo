package poxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * cglib代理
 *
 * 无论是动态代理还是静态带领，都需要定义接口，然后才能实现代理功能。
 * 这同样存在局限性，因此，为了解决这个问题，出现了第三种代理方式：cglib代理。
 *
 * CGLib采用了非常底层的字节码技术，其原理是通过字节码技术为一个类创建子类，
 * 并在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。
 * JDK动态代理与CGLib动态代理均是实现Spring AOP的基础。
 */
public class CglibTest {
    public static void main(String[] args) {
        Target target = new Target();
        CglibTest cglibTest = new CglibTest();
        Target proxyTarget = (Target)cglibTest.createProxy(target.getClass());
        String reslut = proxyTarget.execute();
        System.out.println(reslut);
    }

    /**
     * 代理对象的生成过程由Enhancer类实现，大概步骤如下：
     *
     * 生成代理类Class的二进制字节码；
     *
     * 通过Class.forName加载二进制字节码，生成Class对象；
     *
     * 通过反射机制获取实例构造，并初始化代理类对象。
     */

    public Object createProxy(Class targetClass) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(new MyMethodInterceptor());
        return enhancer.create();
    }
}
