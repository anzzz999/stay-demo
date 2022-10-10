package test;

import cn.hutool.core.util.StrUtil;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class StringDemo {
    public static void main(String[] args) {
        String[] arr = {"zzzzzzabc", "", "eaergvxvhfghij", "efghijasdasdasdasdas"};
        int arrLen = arr.length;
        //初始化长度
        char[] data = new char[arrLen * 5];
        for (int i = 0; i < arrLen; i++) {
            int end = arrLen - i - 1;
            //数组首尾向中间推进
            if (i > end) {
                break;
            }
            for (int j = 0; j < arr[i].length(); j++) {
                data[arrLen * j + i] = arr[i].charAt(j);
            }
            if (i == end) {
                break;
            }
            for (int j = 0; j < arr[end].length(); j++) {
                data[arrLen * j + end] = arr[end].charAt(j);
            }
        }
        //输出
        StringBuilder result = new StringBuilder();
        for (char datum : data) {
            if(datum!=0){
                result.append(datum);
            }
        }
        System.out.println(result);
    }
//    public static void main(String[] args) {
//
//        BigDecimal bigDecimal = BigDecimal.valueOf(0.0600000);
//        System.out.println(bigDecimal.toString());
//        System.out.println(bigDecimal.toPlainString());
//
//        System.out.println( Integer.toBinaryString(100));
//        int count = 0;
//        String s = Integer.toBinaryString(100);
//        for (int i = 0; i < s.length(); i++) {
//            if ('1' == s.charAt(i)) {
//                count++;
//            }
//        }
//        System.out.println(count);
//
//        HashMap<String, String> map = new HashMap<>(3);
//        map.put("1","2");
//        String a = "abc";
//        String b = a + "d";
//        if ("abcd" == b) {
//            System.out.println(111);
//        }
//    }


    @Test
    public void Test1(){
//        System.out.println(removeItemWithComma("1446302909366255617,1442741972653051906,1442747459939426306", "1442741972653051906"));
    }


//    public static String removeItemWithComma(String items, String item){
//        return Arrays.stream(StrUtil.split(items, ","))
//                .filter(value -> !value.equals(item))
//                .collect(Collectors.joining(","));
//    }
    public String countAndSay(int n) {
        // 思路：记录上一层元素，然后翻译成新元素
        if (n == 1){
            return "1";
        }
        if (n == 2){
            return "11";
        }
        String pre = "11";
        for (int i = 3; i <= n; i++) {

            int curValue = Integer.parseInt(pre.charAt(0) + "");
            int curLength = 1;
            String cur = "";
            for (int j = 1; j < pre.length(); j++) {
                if (pre.charAt(j) == pre.charAt(j - 1)) {
                    curLength++;
                } else {
                    cur = cur + curLength + "" + curValue;
                    curValue = Integer.parseInt(pre.charAt(j) + "");
                    curLength = 1;
                }
            }
            cur = cur + curLength + "" + curValue;
            pre = cur;
        }
        return pre;
    }
}

