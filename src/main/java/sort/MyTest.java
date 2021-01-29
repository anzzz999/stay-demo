package sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public class MyTest {

    /**
     * 挖坑
     */
    int adjustArray(int[] arr, int left, int right){
        int temp = arr[left];
        while (left <right){
            while (left <right && arr[right]>temp){
                right--;
            }
            if (left<right){
                arr[left] = arr[right];
                left++;
            }
            while (left<right && arr[left]<=temp){
                left++;
            }
            if (left<right){
                arr[right] = arr[left];
                right--;
            }
        }
        arr[left] = temp;
        return left;
    }


    /**
     *分治法
     */
    void quickSort(int[] arr, int left, int right){
        if (left < right){
            int i = adjustArray(arr, left, right);
            quickSort(arr, left ,i-1);
            quickSort(arr, i+1 ,right);
        }
    }

    @Test
    public void quickTest(){
        int[] num = new int[10];
        Random random = new Random();
        for (int i = 0; i < num.length; i++) {
            num[i] = random.nextInt(1000);
        }
        System.out.println("orgin array:" + Arrays.toString(num));
        quickSort(num, 0 , num.length - 1);
        System.out.println("end   array:" + Arrays.toString(num));
    }
}
