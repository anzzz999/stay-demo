package test;


import org.apache.commons.lang3.StringUtils;


public class StringTest {
    public static void main(String[] args) {
        String s = "  ";
        System.out.println(" s isNotEmpty()：" + StringUtils.isNotEmpty(s));
        System.out.println(" s isNotBlank()：" + StringUtils.isNotBlank(s));
    }
}
