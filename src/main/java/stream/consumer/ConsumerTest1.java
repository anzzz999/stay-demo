package stream.consumer;

import java.util.function.Consumer;

public class ConsumerTest1 {

    //Consumer接口中accept方法的作用是接收指定参数类型，无返回值，重点在于内部消费
    public static void main(String[] args) {
        Consumer<String> consumer1 = s -> System.out.println("Hello " + s);
        Consumer<String> consumer2 = s -> System.out.println("nice to meet you " + s);
        consumer1.andThen(consumer2).accept("an_zzz");

    }
}
