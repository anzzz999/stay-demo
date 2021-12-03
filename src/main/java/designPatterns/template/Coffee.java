package designPatterns.template;

import java.util.Scanner;

/**
 * @Author: zhanmingwei
 */
public class Coffee extends CaffeineBeverage {
    @Override
    protected void brew() {
        System.out.println("冲泡" + getName());
    }

    @Override
    protected void addCondiments() {
        System.out.println("加糖");
        System.out.println("加牛奶");

    }

    @Override
    protected String getName() {
        return "咖啡";
    }

    @Override
    protected boolean customerWantsCondiments() {
        System.out.println("您想要在" + getName() + "中加入牛奶或糖吗（y/n）？");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        return "y".equals(s);
    }
}
