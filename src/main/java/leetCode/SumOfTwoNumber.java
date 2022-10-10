package leetCode;

public class SumOfTwoNumber {

    //给你两个整数 a 和 b ，不使用 运算符 + 和 - ，计算并返回两整数之和。
    // -1000 <= a, b <= 1000
    public int getSum(int a, int b) {
        // 思路： 位运算模拟
        int result = 0;
        // carray 进位
        for (int i = 0, carry = 0; i < 32; i++) {
            // 判断对应位置上的值
            int w1 = (a >> i) & 1, w2 = (b >> i) & 1;
            // 值都为1则进位的值放到对应位置上，然后进位置为1
            if (w1 == 1 && w2 == 1) {
                result |= (carry << i);
                carry = 1;
                // 值只有1个为1，与进相^,进位不变（为了进位为1的时候继续进位）
            } else if (w1 == 1 || w2 == 1) {
                result |= ((carry ^ 1) << i);
                // 值都为0，把对应进位放到相应位置上，进位置为0
            } else {
                result |= (carry << i);
                carry = 0;
            }
        }
        return result;
    }

}






