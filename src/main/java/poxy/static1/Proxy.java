package poxy.static1;

import poxy.Target;

/**
 * 静态代理
 * 静态代理需要针对被代理的方法提前写好代理类，
 * 如果被代理的方法非常多则需要编写很多代码，
 * 因此，对于上述缺点，通过动态代理的方式进行了弥补。
 */
public class Proxy implements Target {

    private Target target;
    Proxy(Target target){
        this.target = target;
    }
    @Override
    public String execute() {
        System.out.println("perProcess");
        String execute = target.execute();
        System.out.println("postProcess");
        return execute;
    }
}
