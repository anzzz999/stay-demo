package test;

import java.math.BigDecimal;
import java.util.HashMap;

public class StringDemo {
    public static void main(String[] args) {

        BigDecimal bigDecimal = BigDecimal.valueOf(0.0600000);
        System.out.println(bigDecimal.toString());
        System.out.println(bigDecimal.toPlainString());

        System.out.println( Integer.toBinaryString(100));
        int count = 0;
        String s = Integer.toBinaryString(100);
        for (int i = 0; i < s.length(); i++) {
            if ('1' == s.charAt(i)) {
                count++;
            }
        }
        System.out.println(count);
        
        HashMap<String, String> map = new HashMap<>(3);
        map.put("1","2");
        String a = "abc";
        String b = a + "d";
        if ("abcd" == b) {
            System.out.println(111);
        }
    }
}
