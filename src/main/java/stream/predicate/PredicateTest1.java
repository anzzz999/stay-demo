package stream.predicate;

import java.util.function.Predicate;

public class PredicateTest1 {
//    Predicate中的test方法，传入指定类型参数，返回布尔类型的结果，用于判断，断言
    //判断一个数是否是偶数
//    默认方法and顾名思义，将本Predicate和and参数中的Predicate对同一入参进行test的结果进行【与】操作。
//    negate方法对test的结果进行【非】操作
//    or方法对两个Predicate的test结果进行【或】操作
//    静态方法isEqual将其入参与test方法的入参进行equals比较

    public static void main(String[] args) {
        Predicate<Integer> predicate = b -> b % 2 == 0;
        System.out.println(predicate.test(3));//false
        System.out.println(Predicate.isEqual(1).test(1));//true
        System.out.println(Predicate.isEqual(2).test(1));
    }


}
