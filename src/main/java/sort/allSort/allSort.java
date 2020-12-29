package sort.allSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排序问题
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 示例:
 *
 * 输入: [1,2,3]
 *
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class allSort {

    public static List<List<Integer>> ans = new ArrayList<>();

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
         permute(nums);
        System.out.println("结果：" + ans);
    }

    public  static List<List<Integer>> permute(int[] nums) {
        dfs(nums, new ArrayList<Integer>());
        return ans;
    }

    private static void dfs(int[] nums, List<Integer> tmp){
        System.out.println(Arrays.toString(nums) + "," + tmp);
        if (tmp.size() == nums.length){
            //创建新的list保存值，避免使用复用的 tmp ，存list是存指针
            ans.add(new ArrayList<>(tmp));
        }else {
            for (int num : nums) {
                if (!tmp.contains(num)) {
                    tmp.add(num);
                    dfs(nums, tmp);
                    //把上一次的选择结果撤销掉
                    tmp.remove(tmp.size() - 1);
                }
            }
        }
    }
}
