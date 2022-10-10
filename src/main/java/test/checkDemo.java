package test;

import clazz.ClazzReflection;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class checkDemo {
//    public static void main(String[] args) {
////        reverseVowels(",.");
//        HashMap<Object, Object> map = new HashMap<>();
//        for (Map.Entry<Object, Object> objectObjectEntry : map.entrySet()) {
//            System.out.println(objectObjectEntry);
//        }
//        System.out.println(11);
//    }

    @Test
    public void TestStringLength() {
        String b = "1";
        System.out.println(b.length());
        b = "1";
        System.out.println(b.codePointCount(0, b.length()));
        b = "𝄞";
        System.out.println(b.length());
        b = "𝄞";
        System.out.println(b.codePointCount(0, b.length()));
    }

    @Test
    public void TestDateCompare() {
        List<Date> list = new ArrayList<>();
        list.add(DateUtil.offsetHour(DateUtil.date(), 1));
        list.add(DateUtil.offsetHour(DateUtil.date(), -1));
        list.add(DateUtil.date());
        list.add(null);
        List<Date> tids =
                list.stream()
                        .sorted((o1, o2) -> DateUtil.compare(o1, o2))
                        .collect(Collectors.toList());
        System.out.println(tids);
    }

    private static String reverseVowels(String s) {
        //思路：双指针
        HashSet<Character> characters = new HashSet<>();
        characters.add('a');
        characters.add('e');
        characters.add('i');
        characters.add('o');
        characters.add('u');
        characters.add('A');
        characters.add('E');
        characters.add('I');
        characters.add('O');
        characters.add('U');
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            while (left < chars.length && !characters.contains(chars[left])) {
                left++;
            }
            while (right > 0 && !characters.contains(chars[right])) {
                right--;
            }
            if (left < right) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
        }
        return new String(chars);
    }


//    public static void main(String[] args) {
//        int i = 2;
//        int result = 0;
//        switch (i) {
//            case 1:
//                result = result + i++;
//            case 2:
//                result = result + i++ * 2;
//            case 3:
//                result = result + i++ * 3;
//        }
//        System.out.println(result);
//    }

    @SneakyThrows
    @Test
    public void TestExecption() {
        List<Exception> list = Lists.newArrayListWithExpectedSize(5);
        list.add(new IOException());
        list.add(new SQLException());
        list.add(new RuntimeException());
        for (Exception exception : list) {
            throw exception;
        }

    }

    @Test
    public void Test1() {
        int[][] matrix = new int[][]{{1, 10, 4, 2}, {9, 3, 8, 7}, {15, 16, 17, 12}};
        System.out.println(luckyNumbers(matrix));
    }

    // 在同一行的所有元素中最小
    // 在同一列的所有元素中最大
    public List<Integer> luckyNumbers(int[][] matrix) {
        // 思路：使用两个数组保存预处理的值，然后再进行遍历判断
        int[] row = new int[matrix[0].length];
        int[] col = new int[matrix.length];
        Arrays.fill(col, Integer.MAX_VALUE);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                col[i] = Math.min(matrix[i][j], col[i]);
                row[j] = Math.max(matrix[i][j], row[j]);
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (row[j] == col[i]) {
                    list.add(matrix[i][j]);
                }
            }
        }
        return list;
    }

    @Test
    public void Test() {
        int[][] ints = new int[][]{{0, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}};
        numEnclaves(ints);
    }

    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {0, 1}};

    public int numEnclaves(int[][] grid) {
        //思路：探测 m*n外围一圈的值，遇到值为1的就进行dfs，将对应值置为0，剩余的即为飞地数量
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1) {
                    dfs(grid, i, j);
                } else if (j == 0 || j == n - 1) {
                    dfs(grid, i, j);
                }
            }
        }
        int ans = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1) {
                    ans++;
                }
            }
        }
        return ans;

    }

    void dfs(int[][] grid, int i, int j) {
        if (grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        for (int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            if (ni < grid.length && ni >= 0 && nj < grid[0].length && nj >= 0) {
                dfs(grid, ni, nj);
            }
        }

    }

    @Test
    public void Test11() {
        System.out.println(convert("PAYPALISHIRING", 3));
    }

    public String convert(String s, int numRows) {


        if (numRows == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        String[] arr = new String[numRows];
        Arrays.fill(arr, "");

        int mod = 2 * (numRows - 1);
        for (int i = 0; i < chars.length; i++) {
            int index = i % mod;
            if (index >= numRows) {
                index = mod - index;
            }
            arr[index] += chars[i];
        }
        StringBuilder sb = new StringBuilder();
        for (String s1 : arr) {
            sb.append(s1);
        }
        return sb.toString();
    }


    public int lengthOfLongestSubstring0(String s) {
        if (s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    public int lengthOfLongestSubstring(String s) {
        // 思路：滑动窗口记录
        int start = 0, length = 0;
        int tempStart = 0, tempLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (check(s, start, start + length, i)) {
                tempLength++;
                if (start == 0 && tempStart == 0) {
                    length++;
                }
            } else {
                if (tempLength > length) {
                    length = tempLength;
                    start = tempStart;
                }
                tempStart = start + 1;
            }
        }
        return Math.max(length, tempLength);
    }

    private boolean check(String s, int start, int end, int i) {
        for (int j = start; j < end; j++) {
            if (s.charAt(i) == s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public String complexNumberMultiply(String num1, String num2) {
        // 复数的乘法法则 (a+bi)(c+di)=(ac-bd)+(bc+ad)i 2i = -1
        String[] value1 = num1.split("\\+|i");
        String[] value2 = num2.split("[+i]");
        Integer a = Integer.valueOf(value1[0]);
        Integer b = Integer.valueOf(value1[1]);
        Integer c = Integer.valueOf(value2[0]);
        Integer d = Integer.valueOf(value2[1]);
        int A = a * c - b * d, B = b * c + a * d;
        return A + "+" + B + "i";

    }


    @Test
    public void Test13() {
        List<String> list = letterCombinations("2597");
        System.out.println(list.size());
        System.out.println(list);
    }

    private String arr[] = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    private List<String> ans;

    public List<String> letterCombinations(String digits) {
        // 利用递归处理
        ans = new ArrayList<>();
        findCombinations(digits, 0, "");
        return ans;

    }

    void findCombinations(String digits, int index, String s) {
        if (index == digits.length()) {
            ans.add(s);
            return;
        }
        String val = arr[digits.charAt(index) - '0'];
        for (int i = 0; i < val.length(); i++) {
            findCombinations(digits, index + 1, s + val.charAt(i));
        }
    }


    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>(list1.length);
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        int indexSum = 100;
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < list2.length; i++) {
            Integer index1 = map.get(list2[i]);
            if (index1 == null) {
                continue;
            }
            int curIndexSum = i + index1;
            if (indexSum > curIndexSum) {
                ans = new ArrayList<>();
                ans.add(list2[i]);
            } else if (indexSum == curIndexSum) {
                ans.add(list2[i]);
            }
        }
        return ans.toArray(new String[]{});
    }



    @Test
    public void Test14() {
        System.out.println(DateUtil.isIn(DateUtil.parseDateTime("2022-04-28 01:00:00"), DateUtil.parseDate("2022-03-22"), DateUtil.parseDate("2022-04-29")));
    }

    @Test
    public void Test16() {
        String s = "你好111";
        System.out.println(s.length());
        List<String> list = new ArrayList<>();
        System.out.println(list.stream().findFirst().orElse("1"));
    }

    @Test
    public void Test15() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User().setId(11L).setName("李子奇"));
        users.add(new User().setId(12L));

        Map<Long, String>  map = new HashMap<>();


        for (User user : users) {
            map.put(user.getId(), user.getName());
        }
        System.out.println(map);

        Map<Long, String> streamMap = users.stream().collect(Collectors.toMap(User::getId, User::getName));
        System.out.println(streamMap);
    }

    @Test
    public void Test17() {

    }

    @Data
    @Accessors(chain = true)
    public static class User{
        private Long id;
        private String  name;
    }


    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                // 核心线程数
                Runtime.getRuntime().availableProcessors(),
                // 最大线程数
                Runtime.getRuntime().availableProcessors(),
                // 非核心线程数的存活时间
                10L,
                // 时间单位
                TimeUnit.SECONDS,
                // 任务队列
                new ArrayBlockingQueue<>(16),
                // 线程工厂
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "outer-thread");
                    }
                },
                // 拒绝策略
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(">>> Thread Reject, TaskCount: " + executor.getTaskCount()
                                + ", CompletedTaskCount: " + executor.getCompletedTaskCount()
                                + ", ActiveCount: " + executor.getActiveCount());
                    }
                }
        );
        for (int i = 0; i < 1; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    ThreadPoolExecutor innerPool = new ThreadPoolExecutor(
                            // 核心线程数
                            Runtime.getRuntime().availableProcessors(),
                            // 最大线程数
                            Runtime.getRuntime().availableProcessors(),
                            // 非核心线程数的存活时间
                            10L,
                            // 时间单位
                            TimeUnit.SECONDS,
                            // 任务队列
                            new ArrayBlockingQueue<>(16),
                            // 线程工厂
                            new ThreadFactory() {
                                @Override
                                public Thread newThread(Runnable r) {
                                    return new Thread(r, "inner-thread");
                                }
                            },
                            // 拒绝策略
                            new RejectedExecutionHandler() {
                                @Override
                                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                                    System.out.println(">>> Thread Reject, TaskCount: " + executor.getTaskCount()
                                            + ", CompletedTaskCount: " + executor.getCompletedTaskCount()
                                            + ", ActiveCount: " + executor.getActiveCount());
                                }
                            }
                    );
                    for (int j = 0; j < 10; j++) {
                        int finalJ = j;
                        innerPool.execute(() -> {
                            System.out.println(Thread.currentThread().getName() + ": task is finished, id: " + finalJ);
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            }
                        });
                    }
                }
            });
        }
        executor.shutdown();
    }

}
