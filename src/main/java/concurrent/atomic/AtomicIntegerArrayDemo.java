package concurrent.atomic;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerArray;

//原子更新数组类型
public class AtomicIntegerArrayDemo {

    private static int[] value = new int[]{1, 2, 3};
    private static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        //对数组中索引为1的位置的元素加5
        int result = atomicIntegerArray.getAndAdd(1, 5);
        System.out.println(result);     // 2
        System.out.println(atomicIntegerArray.get(1));   // 7
        System.out.println(Arrays.toString(value));  // 1 2 3
        System.out.println(atomicIntegerArray);  // 1  7  3
    }
}
