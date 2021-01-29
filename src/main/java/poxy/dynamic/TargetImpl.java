package poxy.dynamic;

import poxy.Target;

public class TargetImpl implements Target {
    @Override
    public String execute() {
        System.out.println("dynamicProxy...");
        return "execute";
    }
}
