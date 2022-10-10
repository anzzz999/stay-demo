package designPatterns.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhanmingwei
 */
public class Demo {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");


        java.util.Iterator<String> iterator1 = names.iterator();
        java.util.Iterator<String> iterator2 = names.iterator();
        iterator1.next();
        iterator1.remove();
        iterator1.remove();
        iterator2.next(); // 运行结果？
    }
}
