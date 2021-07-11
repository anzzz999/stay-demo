package stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


public class TestAPI {

    private static List<String> strs = Arrays.asList("a", "a", "b", "b", "c");

    //stream的count，anyMatch，allMatch，noneMatch操作，我们先看下函数的定义
    @Test
    public void count(){
        //count方法，跟List接口的size一样，返回的都是这个集合流的元素的长度，
        // 不同的是，流是集合的一个高级工厂，中间操作是工厂里的每一道工序，我们对这个流操作完成后，可以进行元素的数量的和；
        System.out.println(strs.size());
        System.out.println(strs.stream().filter("a"::equals).count());
    }

    @Test
    public void match(){
//        anyMatch表示，判断的条件里，任意一个元素成功，返回true
//        allMatch表示，判断条件里的元素，所有的都是，返回true
//        noneMatch跟allMatch相反，判断条件里的元素，所有的都不是，返回true

        System.out.println(strs.stream().anyMatch("a"::equals));
        System.out.println(strs.stream().allMatch("a"::equals));
        System.out.println(strs.stream().noneMatch("a"::equals));
    }
}
