package stream.function;

import java.util.function.Function;

public class FunctionTest2 {
    public static void main(String[] args) {
        FunctionTest2 functionTest2 = new FunctionTest2();
        //调用compose
        //先+8，然后将得到的值*3
        System.out.println(functionTest2.compute(2, v -> v * 3, v -> v + 8));
    }


    //默认方法compose作用是传入参数后，首先执行compose方法内的Function的apply方法，
    // 然后将其返回值作为本Function方法的入参，调用apply后得到最后返回值
    public int compute(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        //将function2先接收入参a，调用apply后，将返回值作为新的入参，传入function1，调用apply返回最后结果
        return function1.compose(function2).apply(a);
    }

}
