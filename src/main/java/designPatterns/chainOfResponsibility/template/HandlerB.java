package designPatterns.chainOfResponsibility.template;

/**
 * @Author: zhanmingwei
 */
public class HandlerB implements IHandler {
    @Override
    public boolean handle() {
        boolean handled = false;
        //...
        return handled;
    }
}
