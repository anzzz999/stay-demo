package designPatterns.template;

/**
 * @Author: zhanmingwei
 */
public class Tea extends CaffeineBeverage {
    @Override
    protected void brew() {
        System.out.println("浸泡" + getName());
    }

    @Override
    protected void addCondiments() {
        System.out.println("加柠檬");
    }

    @Override
    protected String getName() {
        return "茶叶";
    }

    @Override
    protected boolean customerWantsCondiments() {
        return false;
    }
}
