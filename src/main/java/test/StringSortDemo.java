package test;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @author zhanmingwei
 */
public class StringSortDemo {

    public static void main(String[] args) {
//        String s = sort(new String[]{"abc", "efg", "hij"});
//        String s1 = sort(new String[]{"abc", "", "efghij","efghijasdasdasdasdas"});
        String s2 = sortByQueue(new String[]{"zzzzzzabc", "", "eaergvxvhfghij", "efghijasdasdasdasdas"});
        String s3 = sortForOrderly(new String[]{"zzzzzzabc", "", "eaergvxvhfghij", "efghijasdasdasdasdas"});
//        System.out.println(s);
//        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }


    public static String sort(String[] arr) {
        // 思路：暴力法，找到最长的字符串，遍历
        if (arr.length == 0) {
            return "";
        }
        int maxLen = Arrays.stream(arr).mapToInt(String::length).max().getAsInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxLen; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i >= arr[j].length()) {
                    continue;
                }
                sb.append(arr[j].charAt(i));
            }
        }
        return sb.toString();
    }

    public static String sortByQueue(String[] arr) {
        // 思路：将字符串放入队列中
        if (arr.length == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        Queue<StringBuilder> queue = new ArrayDeque<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() == 0) {
                continue;
            }
            StringBuilder sb = new StringBuilder(arr[i]);
            queue.add(sb);
        }
        while (!queue.isEmpty()) {
            // 弹出第一个字符串并拿到其第一个字符，若未为空则继续放入队列
            StringBuilder sb = queue.poll();
            result.append(sb.charAt(0));
            sb.deleteCharAt(0);
            if (sb.length() > 0) {
                queue.add(sb);
            }
        }
        return result.toString();
    }


    public static String sortForOrderly(String[] arr){
        if (arr.length == 0){
            return "";
        }
        int[] letter = new int[26];
        StringBuilder result = new StringBuilder();
        Queue<StringBuilder> queue = new ArrayDeque<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() == 0) {
                continue;
            }
            StringBuilder sb = new StringBuilder(arr[i]);
            queue.add(sb);
        }
        while (!queue.isEmpty()) {
            // 一组字符串
            for (int i = 0; i < queue.size(); i++) {
                StringBuilder poll = queue.poll();
                char c = poll.charAt(0);
                letter[c-'a']++;
                poll.deleteCharAt(0);
                if (poll.length() > 0) {
                    queue.add(poll);
                }
            }
            String periodResult=  getPeriodResult(letter);
            letter = new int[26];
            result.append(periodResult);
        }
        return result.toString();
    }

    private static String getPeriodResult(int[] letter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < letter.length; i++) {
            for (int j = 0; j < letter[i]; j++) {
                sb.append((char)('a' + i));
            }
        }
        return sb.toString();
    }
}
