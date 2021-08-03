package stream.supplier;

import java.util.function.Supplier;

public class SupplierTest1 {
    //Supplier意为供应，只有一个方法get，不接收任何参数，只返回指定类型结果
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "hello world~";
        System.out.println(supplier.get());
    }
}
