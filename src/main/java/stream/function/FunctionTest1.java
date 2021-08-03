package stream.function;

import java.util.function.Function;

public class FunctionTest1 {
    public static void main(String[] args) {
        FunctionTest1 functionTest1 = new FunctionTest1();
        int result = functionTest1.compute(1, v -> v + 10);
        System.out.println(result);
    }

    //Function接口的唯一抽象方法是apply，作用是接收一个指定类型的参数，返回一个指定类型的结果
    public int compute(int a, Function<Integer, Integer> function){
        //使用者在使用本方法时，需要去编写自己的apply，
        //传递的funtion是一个行为方法，而不是一个值
        return function.apply(a);
    }
}
