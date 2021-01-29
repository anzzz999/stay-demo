package poxy.dynamic;

import poxy.Target;

import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 动态代理主要是通过反射机制，在运行时动态生成所需代理的class.
 */
public class ProxyTest {
    public static void main(String[] args) {
        TargetImpl target = new TargetImpl();
        DynamicProxyHandler dynamicProxyHandler = new DynamicProxyHandler(target);
        Target proxySubject = (Target)Proxy.newProxyInstance(TargetImpl.class.getClassLoader(), TargetImpl.class.getInterfaces(), dynamicProxyHandler );
        String result = proxySubject.execute();
        System.out.println(result);
    }
}
