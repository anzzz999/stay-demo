package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
        String pattern ="FROM\\s+\\b(.+?)\\b";
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher("select * FROM a");
        if (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
        }
    }
}
