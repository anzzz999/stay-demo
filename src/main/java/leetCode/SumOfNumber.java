package leetCode;
/*续n个数的和
        求 1 2 ... n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
        示例 1：

        输入: n = 3 输出: 6*/
public class SumOfNumber {
    public static void main(String[] args) {
        sumNums(5);
    }

    public static int sumNums(int n) {
        System.out.println("start:"+ n);
//        boolean b = n > 0 && ((n & sumNums(n - 1))> 0);
        boolean b = n > 0 && ((n  += sumNums(n - 1)) > 0);
        System.out.println("end:"+ n);
        return n;
    }
}
