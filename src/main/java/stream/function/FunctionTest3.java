package stream.function;

import java.util.function.Function;

public class FunctionTest3 {
    public static void main(String[] args) {
        FunctionTest3 ft = new FunctionTest3();
        //调用andThen
        //先*3，然后将得到的值+8
        System.out.println(ft.compute(2, v -> v * 3, v -> v + 8));//14
    }

    //默认方法andThen与compose正好相反，先执行本Function的apply，
    // 然后将结果作为andThen方法参数内的Function的入参，调用apply后返回最后结果
    public int compute(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        //将function2先接收入参a，调用apply后，将返回值作为新的入参，传入function1，调用apply返回最后结果
        return function1.andThen(function2).apply(a);
    }

}
