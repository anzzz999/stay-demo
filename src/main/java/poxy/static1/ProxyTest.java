package poxy.static1;

import poxy.Target;

public class ProxyTest {

    public static void main(String[] args) {
        Target target = new TargetImpl();
        Proxy proxy = new Proxy(target);
        System.out.println(proxy.execute());
    }
}
