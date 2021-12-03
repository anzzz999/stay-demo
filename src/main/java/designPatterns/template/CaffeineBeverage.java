package designPatterns.template;

/**
 * 咖啡因饮料是一个抽象类
 **/
public abstract class CaffeineBeverage {

    final void prepareRecipe() {
        System.out.println("---" + getName() + "---");
        // 煮水
        boilWater();
        // 热水处理饮料
        brew();
        // 饮料倒入杯子
        pourInCup();
        // 在饮料中加入调料
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }


    protected void boilWater() {
        System.out.println("将水煮沸");
    }

    protected abstract void brew();

    protected void pourInCup() {
        System.out.println("将饮料倒入杯中");
    }

    protected abstract void addCondiments();

    protected abstract String getName();

    protected abstract boolean customerWantsCondiments();


}
