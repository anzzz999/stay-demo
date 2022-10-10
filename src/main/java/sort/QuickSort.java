package sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序
 */
public class QuickSort {

    /**
     * 1．i =L; j = R; 将基准数挖出形成第一个坑a[i]。
     *
     * 2．j--由后向前找比它小的数，找到后挖出此数填前一个坑a[i]中。
     *
     * 3．i++由前向后找比它大的数，找到后也挖出此数填到前一个坑a[j]中。
     *
     * 4．再重复执行2，3二步，直到i==j，将基准数填入a[i]中。
     */
    int adjustArray(int[] arr, int left, int right){
        int i = left , j = right;
        int temp = arr[left];
        while (i < j){
            while (i<j && arr[j]>=temp){
                j--;
            }
            if(i < j) {
                arr[i] = arr[j]; //将s[j]填到s[i]中，s[j]就形成了一个新的坑
                i++;
            }
            while (i<j && arr[i]<temp){
                i++;
            }
            if (i < j){
                arr[j] = arr[i]; //将s[j]填到s[i]中，s[j]就形成了一个新的坑
                j--;
            }
        }
        arr[i] = temp;
        return i;
    }

    void quickSort(int[] arr , int l, int r){
        if (l < r){
            int i = adjustArray(arr, l, r);
            quickSort(arr, l, i-1);
            quickSort(arr, i+1, r);
        }
    }


    @Test
    public void Test(){
        int[] num = new int[10000];
        Random random = new Random();
        for (int i = 0; i < num.length; i++) {
            num[i] = random.nextInt(10000);
        }
        System.out.println("orgin array:" + Arrays.toString(num));
        quickSort(num, 0 , num.length - 1);
        System.out.println("end   array:" + Arrays.toString(num));
    }
}
