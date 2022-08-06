package other.bigDecimal;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author zhanmingwei
 * <p>
 * 不掌握这些坑，你敢用BigDecimal吗？
 * https://juejin.cn/post/7121852516228136996
 */
public class BigDecimalTest {


    /**
     * 浮点类型的坑
     */
    @Test
    public void test1() {

        float a = 1;
        float b = 0.9f;
        System.out.println(a - b);
        // 结果为 0.100000024
        // 原因：之所以产生这样的结果，是因为0.1的二进制表示是无限循环的。由于计算机的资源是有限的，所以是没办法用二进制精确的表示 0.1，
        //      只能用「近似值」来表示，就是在有限的精度情况下，最大化接近 0.1 的二进制数，于是就会造成精度缺失的情况。
        // 结论：浮点数计算会有精度缺失问题

        BigDecimal a1 = new BigDecimal(0.01);
        BigDecimal b1 = BigDecimal.valueOf(0.01);
        System.out.println(a1); // 结果为 0.01000000000000000020816681711721685132943093776702880859375
        System.out.println(b1); // 结果为 0.1
        // 原因：new BigDecimal时，传入的0.1已经是浮点类型了，鉴于上面说的这个值只是近似值，在使用new BigDecimal时就把这个近似值完整的保留下来了。
        // 结论：第一，在使用BigDecimal构造函数时，尽量传递字符串而非浮点类型；第二，如果无法满足第一条，则可采用BigDecimal#valueOf方法来构造初始化值。
    }

    /**
     * 浮点精度的坑
     */
    @Test
    public void test2() {
        BigDecimal a = new BigDecimal("0.01");
        BigDecimal b = new BigDecimal("0.010");
        System.out.println(a.equals(b));  // 比较内容，equals方法不仅比较了值是否相等，还比较了精度是否相同
        System.out.println(a.compareTo(b)); // 比较值大小
        // 结论：通常情况，如果比较两个BigDecimal值的大小，采用其实现的compareTo方法；如果严格限制精度的比较，那么则可考虑使用equals方法。
    }

    /**
     * 设置精度的坑
     */
    @Test
    public void test3() {
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("3.0");
//        a.divide(b);
// ArithmeticException 如果在除法（divide）运算过程中，如果商是一个无限小数（0.333…），而操作的结果预期是一个精确的数字，那么将会抛出ArithmeticException异常
        System.out.println(a.divide(b, 2, RoundingMode.HALF_UP));
        // 结论：在使用BigDecimal进行（所有）运算时，一定要明确指定精度和舍入模式。
    }

    /**
     * 三种字符串输出的坑
     */
    @Test
    public void test4() {
        BigDecimal a = BigDecimal.valueOf(35634535255456719.22345634534124578902);
        System.out.println(a.toString()); // 科学计数法
        System.out.println(a.toPlainString()); // 不使用科学计数法
        System.out.println(a.toEngineeringString()); // 工程师计数法 类似于科学计数法，只不过指数的幂都是3的倍数
        // 结论：根据数据结果展示格式不同，采用不同的字符串输出方法，通常使用比较多的方法为toPlainString() 。


    }
}


