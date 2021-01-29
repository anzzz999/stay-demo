package poxy.static1;

import poxy.Target;

public class TargetImpl implements Target {

    @Override
    public String execute() {
        System.out.println("staticTargetImpl execute！");
        return "execute";
    }
}
