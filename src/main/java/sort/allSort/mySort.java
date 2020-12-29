package sort.allSort;

import java.util.Arrays;

public class mySort {
    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 7, 5, 3};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void sort(int[] nums){
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0 ; j < i; j++){
                if (nums[j] > nums[i]){
                    int temp = nums[j ];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
            }
        }
    }
}
