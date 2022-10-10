package designPatterns.chainOfResponsibility.template;


/**
 * @Author: zhanmingwei
 */
public class Demo {
    public static void main(String[] args) {
        HandlerChain chain = new HandlerChain();
        chain.addHandler(new HandlerA());
        chain.addHandler(new HandlerB());
        chain.handle();
    }

}
