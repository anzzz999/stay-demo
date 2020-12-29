package generic;

import java.util.List;

/**
 * 泛型擦除
 * 泛型-当泛型遇到重载
 * 这段代码，有两个重载的函数，因为他们的参数类型不同，List<String>，另一个是List<Integer>，但是，这段代码是编译通不过的。
 * 因为我们前面讲过，参数List<String>和List<Integer>编译之后都被擦除了，变成了一样的原生类型List，擦除动作导致这两个方法的特征签名变得一模一样。
 */
public class GenericTypes {

    public static void method(List<String> list){
        System.out.println(list);
    }

//    public static void method(List<Integer> list){
//        System.out.println(list);
//    }
}
