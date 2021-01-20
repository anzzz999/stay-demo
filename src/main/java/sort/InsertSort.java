package sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort implements IArraySort {
    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            // 记录要插入的数据
            int tmp = array[i];

            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < array[j-1]){
                array[j] = array[j-1];
                j--;
            }

            // 存在比其小的数，插入
            if (j != i){
                array[j] = tmp;
            }
        }
    }

    @Test
    public void Test(){
        int[] array = {1,5,6,7,8,4,0,9};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
