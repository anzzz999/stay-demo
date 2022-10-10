package test;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: zhanmingwei
 */

@Slf4j
public class MyTest {

    @Test
    public void Test1() {
        List<MyUser> list = new ArrayList<>();
//        Set<Long> idSet = list.stream().map(MyUser::getId).collect(Collectors.toSet());
//        log.info("idset:{}", idSet);

    }

    @Test
    public void Test2() {
        Thread thread = new Thread(() -> {
            System.out.println("hello");
        });
    }


    @Test
    public void Test3() {
        String s = "A man, a plan, a canal: Panama";
        int left = 0;
        int right = s.length() - 1;
        s = s.toLowerCase();
        while (left < right) {

            while (left < right && !inchar(s.charAt(left))) {
                left++;
            }
            while (right > left && !inchar(s.charAt(right))) {
                right--;
            }
            if (left < right && (s.charAt(left) == s.charAt(right))) {
                return;
            }
            left++;
            right--;
        }
//        return true;

    }

    boolean inchar(char leftChar) {
        if ((leftChar >= '0' && leftChar <= '9') || (leftChar >= 'a' && leftChar <= 'z')) {
            return true;
        }
        return false;
    }


    public String longestPalindrome(String s) {
        // 思路：中心回文探测，中间往两边走
//        if (s == null || s.length() < 1) {
//            return "";
//        }
        int start = 0, end = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > (end - start + 1)) {
                // 这里要画图一下，（len -1）对奇数无影响，主要是为了偶数的情况进行处理 -> 左部分把自己排除掉
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    @Test
    public void Test() {
//        String s = "acacaccccc";
//        System.out.println(longestPalindrome(s));;
        reverse(-2147483648);
    }


    public int reverse(int x) {
        long n = 0;
        while (x != 0) {
            n = n * 10 + x % 10;
            x = x / 10;
        }
        return (int) n == n ? (int) n : 0;

    }


    @Test
    public void Test11() {
        shortestCompletingWord("1s3 PSt", new String[]{"step", "steps", "stripe", "stepple"});
    }

    public String shortestCompletingWord(String licensePlate, String[] words) {
        // 思路：将字符串处理，建立一个数组，不符合的word就标位-1，然后之后对其跳过，最后遍历数组找到words最短的。
        String[] result = Arrays.copyOf(words, words.length);
        int[] flags = new int[words.length];
        String s = licensePlate.toLowerCase().replaceAll("[^a-z]", "");
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < words.length; j++) {
                if (flags[j] == -1) {
                    continue;
                }
                int index = words[j].indexOf(s.charAt(i));
                if (index == -1) {
                    flags[j] = -1;
                } else {
                    words[j] = words[j].substring(0, index) + words[j].substring(index + 1);
                }
            }
        }
        int wordLocation = -1;
        int wordMinLength = -1;
        for (int i = 0; i < flags.length; i++) {
            if (flags[i] == -1) {
                continue;
            }
            if (wordLocation == -1) {
                wordLocation = i;
                wordMinLength = words[i].length();
                continue;
            }
            if (words[i].length() < wordMinLength) {
                wordLocation = i;
                wordMinLength = words[i].length();
            }
        }

        return result[wordLocation];

    }

    @Test
    public void Test12() {
        int[][] array = new int[][]{{0, 0}};
        uniquePathsWithObstacles(array);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 思路：首先模拟出没有障碍物的路径数，再减掉会阻挡的路径数(起点到障碍物的路径数*障碍物到终点的路径数)
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // 找到障碍物
        int zi = -1;
        int zj = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    zi = i;
                    zj = j;
                    break;
                }
            }
        }

        // 填充数据
        Arrays.fill(obstacleGrid[0], 1);
        for (int i = 1; i < n; i++) {
            obstacleGrid[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
            }
        }
        if (zi != -1 && zj != -1) {
            return obstacleGrid[m - 1][n - 1] - obstacleGrid[zi][zj] * obstacleGrid[m - zi - 1][n - zj - 1];
        }
        return obstacleGrid[m - 1][n - 1];

    }

    @Test
    public void Test101() {
        modifyString("xxx");
    }

    public String modifyString(String s) {
        // 思路：判断首尾，再判断中间位置
        char[] array = s.toCharArray();
        int length = array.length;
        if (length == 1) {
            if (array[0] == '?') {
                return "a";
            }
        } else {
            if (array[0] == '?') {
                array[0] = getChar(' ', array[1]);
            }
            if (array[length - 1] == '?') {
                array[length - 1] = getChar(array[length - 2], ' ');
            }
        }
        for (int i = 1; i < length - 2; i++) {
            if (array[i] == '?') {
                array[i] = getChar(array[i - 1], array[i + 1]);
            }
        }
        return String.valueOf(array);

    }

    private char getChar(char pre, char next) {
        for (char i = 'a'; i < 'c'; i++) {
            if (i == pre || i == next) {
                continue;
            }
            return i;
        }
        return 'd';
    }

    @Test
    public void Test102() {
        System.out.println(grayCode(2));
    }

    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        while (n-- > 0) {
            int m = ans.size();
            for (int i = m - 1; i >= 0; i--) {
                ans.set(i, ans.get(i) << 1);
                ans.add(ans.get(i) + 1);
            }
        }

        return ans;
    }

    @Test
    public void Test103() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CountDownLatch countDownLatch = new CountDownLatch(20);
        AtomicInteger a = new AtomicInteger(0);
        for (int i = 0; i < 40; i++) {
            int finalI = i;
            executorService.execute(() -> {
                int andIncrement = a.getAndIncrement();
                if (andIncrement >= 20) {
                    log.info("endend----------------");
                    executorService.shutdownNow();
                    return;
                }
                log.info("{}:{}:{}", Thread.currentThread().getName(), finalI, andIncrement);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        log.info("end----------------");
        List<Runnable> runnables = executorService.shutdownNow();
        log.info(String.valueOf(runnables));
    }

    @Test
    public void Test13() throws InterruptedException {
        System.out.println(handlerSensitiveWords("心灵大麻"));
    }

    public String handlerSensitiveWords(String word) {
        if (StringUtils.isBlank(word)) {
            return "";
        }
        char[] charArray = word.toCharArray();
        int length = charArray.length;
        if (length <= 2) {
            int i = new Random().nextInt(length);
            charArray[i] = '*';
        } else {
            for (int i = 1; i < length - 1; i++) {
                charArray[i] = '*';
            }
        }
        return String.valueOf(charArray);
    }


    @Test
    public void Test104() {
        System.out.println(kSmallestPairs(new int[]{1, 2, 4, 5, 6}, new int[]{3, 5, 7, 9}, 4));
    }

    static boolean flag = true;

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums1.length, m = nums2.length;
        if (n > m && !(flag = false)) return kSmallestPairs(nums2, nums1, k);
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> (nums1[a[0]] + nums2[a[1]])));
        for (int i = 0; i < Math.min(n, k); i++) {
            q.add(new int[]{i, 0});
        }
        while (ans.size() < k && !q.isEmpty()) {
            int[] poll = q.poll();
            int a = poll[0];
            int b = poll[1];
            ans.add(Arrays.asList(
                    flag ? nums1[a] : nums2[b],
                    flag ? nums1[b] : nums2[a]
            ));
            if (b + 1 < m) {
                q.add(new int[]{a, b + 1});
            }

        }
        return ans;
    }


    @Test
    public void Test105() {
        DateTime endTime = DateUtil.endOfDay(DateUtil.tomorrow());
        DateTime beginTime = DateUtil.beginOfDay(DateUtil.offsetDay(endTime, -9));
        Set<String> dateSet = DateUtil.rangeToList(beginTime, endTime, DateField.HOUR_OF_DAY).stream()
                .map(DateUtil::formatDate).collect(Collectors.toSet());
        System.out.println(dateSet);
        System.out.println(dateSet.size());
//        Maps.newHashMapWithExpectedSize();
    }

    @Test
    public void Test106() {
        System.out.println(maxArea(new int[]{1, 2, 3, 5, 1, 2, 8}));
    }

    public int maxArea(int[] height) {
        int ans = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int val = (j - i) * (Math.min(height[i], height[j]));
                ans = Math.max(ans, val);
            }
        }
        return ans;

    }

    @Test
    public void Test107() {
        System.out.println(addDigits(78954));
    }

    public int addDigits(int num) {
        int ans = num;
        while (ans > 10) {
            int val = 0;
            while (ans != 0) {
                val += ans % 10;
                ans /= 10;
            }
            ans = val;
        }
        return ans;

    }

    @Test
    public void Test108() {
        new Thread(new Printer(0)).start();
        new Thread(new Printer(1)).start();
    }

    static class Printer implements Runnable {

        private int id;

        private static int start = 1;
        private static int end = 100;

        Printer(int id) {
            this.id = id;
        }

        @SneakyThrows
        @Override
        public void run() {
            synchronized (Printer.class) {
                while (start <= end) {
                    if (start % 2 == id) {
                        System.out.println(Thread.currentThread().getName() + "---" + start++);
                        Printer.class.notifyAll();
                    } else {
                        Printer.class.wait();
                    }
                }
            }

        }
    }


    @Test
    public void test() {
        asteroidCollision(new int[]{10, 2, -1});

    }

    public int[] asteroidCollision(int[] asteroids) {
        // 正数入栈等踢馆
        Deque<Integer> ans = new ArrayDeque<>();
        Deque<Integer> zs = new ArrayDeque<>();
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] > 0) {
                zs.addLast(asteroids[i]);
            } else {
                boolean flag = true;
                while (flag && zs.size() > 0) {
                    if (zs.getLast() + asteroids[i] > 0) {
                        flag = false;
                    } else if (zs.getLast() == asteroids[i]) {
                        flag = false;
                        zs.removeLast();
                    } else {
                        zs.removeLast();
                    }
                }
                if (flag) {
                    ans.add(asteroids[i]);
                }
            }
        }
        while (zs.size() != 0) {
            ans.add(zs.pollFirst());
        }
        int[] newAsteroids = new int[ans.size()];
        for (int i = 0; i < newAsteroids.length; i++) {
            newAsteroids[i] = ans.removeFirst();
        }
        return newAsteroids;
    }

    private void setValue(ArrayList<Integer> list) {
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
    }

    @Test
    public void test2() {
        EnumAuditStatus status = null;
        switch (status){
            case 审核中:
            default:
                System.out.println("1");
                break;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum EnumAuditStatus {
        审核中(1),
        审核成功(2),
        审核失败(3);
        private Integer status;

        private static final Map<Integer, EnumAuditStatus> code2Enum =
                Stream.of(EnumAuditStatus.values())
                        .collect(Collectors.toMap(EnumAuditStatus::getStatus, Function.identity()));

        public static EnumAuditStatus fromCode(Integer code) {
            return code2Enum.get(code);
        }
    }


    @Test
    public void test1() {
        String s = FileUtil.readUtf8String("D:\\WeChat\\WeChat Files\\wxid_atv0z18jinry22\\FileStorage\\File\\2022-09\\maAppId_res(1).json");
        String jsonStr = JSONUtil.toJsonStr(s);
//        Map<String, List<Object>> myUser = myUsers.stream().findFirst().get();
//        Map.Entry<String, List<Object>> entry = myUser.entrySet().iterator().next();
//        log.info("{}, {} ", entry.getValue().size(), entry.getKey());
        List<Map<String, List<Object>>> list = JSONUtil.toBean(jsonStr, new TypeReference<List<Map<String, List<Object>>>>() {
        }, true);
        for (Map<String, List<Object>> stringListMap : list) {
            if (CollUtil.isEmpty(stringListMap)){
                continue;
            }
            if (stringListMap.entrySet().iterator().next().getValue().size() == 0) {
                continue;
            }
            log.info("update my_table set out_poi_count = {} where maAppId = '{}'; ", stringListMap.entrySet().iterator().next().getValue().size() , stringListMap.entrySet().iterator().next().getKey());
        }
    }

    @Data
    static class MyUser {
        private String maAppId;
        private List<Object> list = Lists.newArrayList();
    }

}