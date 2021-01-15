package sort.allSort;

/**
 * 一般二分查找的过程分为：预处理 - 二分查找 - 后处理，上面的代码，就没有后处理的过程，因为在每一步中，你都检查了元素，如果到达末尾，也已经知道没有找到元素。
 *
 * 总结一下一般实现的几个条件：
 *
 * 初始条件：left = 0, right = length-1
 * 终止：left > right
 * 向左查找：right = mid-1
 * 向右查找：left = mid +1
 *
 */
public class binarySort {
    public static void main(String[] args) {
        int[] array= new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        int i = binarySearch(array, 55);
        System.out.println(i);
    }


    public static  int binarySearch(int[] array, int des) {
        int left = 0;
        int right = array.length -1;
        int count = 0;
        while (left <= right){
            count++;
            int mid = left + (right -left)/2;   //防止溢出
            if (array[mid] == des){
                System.out.println("次数:" + count);
                return mid;
            }else if (array[mid] > des){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
