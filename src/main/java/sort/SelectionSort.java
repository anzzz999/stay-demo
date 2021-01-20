package sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectionSort implements IArraySort {
    @Override
    public void sort(int[] array) {
        for (int i = 0; i < array.length -1; i++) {
            int min = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j] < array[min]){
                    min = j;
                }
            }
            if (i != min) {
                array[i] = array[i]^array[min];
                array[min] = array[i]^array[min];
                array[i] = array[i]^array[min];
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
