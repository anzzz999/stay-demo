package generic;

import org.junit.jupiter.api.Test;

/**
 * @author zhanmingwei
 */
public class TypeTest {

    @Test
    public void test1(){
        long a = 1;
        long b = 1;
        double val = getVal(a, b);
        System.out.println(val);
    }

    private double getVal(double a, double b){
        return a + b ;
    }
}
