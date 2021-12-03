package designPatterns.template;

/**
 * @Author: zhanmingwei
 * <p>
 * 模板方法模式在一个方法中定义一个算法骨架，并将某些步骤推迟到子类中实现
 * 在模板模式经典的实现中，模板方法定义为final，可以避免被子类重写。需要子类重写的方法定义为abstract，可以强迫子类去实现。
 * 不过，在实际项目开发中，模板模式的实现比较灵活，以上两点都不是必须的。
 * <p>
 * 模板模式有两大作用：复用和扩展。
 * 其中，复用指的是，所有的子类可以复用父类中提供的模板方法的代码。
 * 扩展指的是，框架通过模板模式提供功能扩展点，让框架用户可以在不修改框架源码的情况下，基于扩展点定制化框架的功能。
 */
public class Test {
    public static void main(String[] args) {
        Coffee coffee = new Coffee();
        Tea tea = new Tea();
        coffee.prepareRecipe();
        tea.prepareRecipe();
    }
}
