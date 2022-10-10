package regex;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import test.checkDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: an_zzz
 * @Description: 使用正则表达式的好处有哪些？一个正则表达式就是一个描述规则的字符串，所以，只需要编写正确的规则，我们就可以让正则表达式引擎去判断目标字符串是否符合规则。
 * 正则表达式是一套标准，它可以用于任何语言。Java标准库的java.util.regex包内置了正则表达式引擎，在Java程序中使用正则表达式非常简单。
 * @Date: 2021/11/8 16:02
 */
@Slf4j
public class RegexTest {
    @Test
    public void Test1() {
        String pattern = "FROM\\s+\\b(.+?)\\b";
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher("select * FROM a");
        if (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
        }
    }

    /**
     * 把正则表达式转换为Java字符串就变成了20\\d\\d，注意Java字符串用\\表示\。
     */
    @Test
    public void test2() {
        // 其中\d表示任意一个数字。
        String pattern = "20\\d\\d";
        System.out.println("2019".matches(pattern));
        System.out.println("2109".matches(pattern));
        System.out.println(isValidMobileNumber("13112681000"));
        System.out.println(isValidMobileNumber("3112681472"));
        System.out.println(isValidMobileNumber("23112681472"));

        // 匹配非ASCII字符，例如中文，那就用  的十六进制表示
        pattern = "a\\u548cc";
        System.out.println("a和c".matches(pattern));
        System.out.println("a&c".matches(pattern));

        // 可以用.匹配一个任意字符
        pattern = "a.c";
        System.out.println("a和c".matches(pattern));
        System.out.println("a&c".matches(pattern));

        // 匹配开头和结尾，用^表示开头，$表示结尾
        pattern = "^A\\d{3}$";
        System.out.println("A001".matches(pattern));
        System.out.println("A380".matches(pattern));
        System.out.println("A3800".matches(pattern));


        // 匹配指定范围  匹配6位十六进制数
        pattern = "[0-9a-fA-F]{6}";
        System.out.println("0123a5".matches(pattern));
        System.out.println("0123a56".matches(pattern));
        System.out.println("12345g".matches(pattern));

        System.out.println("--------------");

        // 不包含指定范围的字符  3个不包含0
        pattern = "[^0]{3}";
        System.out.println("123456".matches(pattern));
        System.out.println("012345".matches(pattern));
        System.out.println("1234567".matches(pattern));
        System.out.println("ABC".matches(pattern));
        System.out.println("a11".matches(pattern));

        System.out.println("--------------");

        // 或规则匹配
        pattern = "learn\\s(java|php)";
        System.out.println("learn java".matches(pattern));
        System.out.println("learn Java".matches(pattern));
        System.out.println("learn php".matches(pattern));
        System.out.println("learn Go".matches(pattern));


    }

    @Test
    public void Test3() {
        String time = "23:01:59";
        Pattern pattern = Pattern.compile("(\\d{2}):(\\d{2}):(\\d{2})");
        Matcher matcher = pattern.matcher(time);
        if (matcher.matches()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));

        }
    }

    /**
     * 给定一个字符串表示的数字，判断该数字末尾0的个数。
     * 正则表达式默认使用贪婪匹配：任何一个规则，它总是尽可能多地向后匹配，因此，\d+总是会把后面的0包含进来。
     * 要让\d+尽量少匹配，让0*尽量多匹配，我们就必须让\d+使用非贪婪匹配。在规则\d+后面加个?即可表示非贪婪匹配。
     */
    @Test
    public void Test4() {
//        Pattern pattern = Pattern.compile("(\\d+)(0*)");
        Pattern pattern = Pattern.compile("(\\d+?)(0*)");
        Matcher matcher = pattern.matcher("1230000");
        if (matcher.matches()) {
            System.out.println(matcher.group(0));
            System.out.println("group1=" + matcher.group(1));
            System.out.println("group2=" + matcher.group(2));
        }

    }


    @Test
    public void Test5() {
        // 分割字符串
        System.out.println(Arrays.toString("a b c".split("\\s")));
        System.out.println(Arrays.toString("a b  c".split("\\s")));

        // 搜索字符串 搜索的规则是3个字符 中间必须是o，前后两个必须是字符[A-Za-z0-9_]。
        String s = "the quick brown fox jumps over the lazy dog.";
        Pattern pattern = Pattern.compile("\\wo\\w");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            String sub = s.substring(matcher.start(), matcher.end());
            System.out.println(sub);
        }

        // 替换字符串 使用正则表达式替换字符串可以直接调用String.replaceAll()，它的第一个参数是正则表达式，第二个参数是待替换的字符串。
        s = "The     quick\t\t brown   fox  jumps   over the  lazy dog.";
        String r = s.replaceAll("\\s+", " ");
        System.out.println(r);


        // 把搜索到的指定字符串按规则替换，比如前后各加一个<b>xxxx</b>，这个时候，
        // 使用replaceAll()的时候，我们传入的第二个参数可以使用$1、$2来反向引用匹配到的子串。
         s = "the quick brown fox jumps over the lazy dog.";
         r = s.replaceAll("\\s([a-z]{4})\\s", " <b>$1</b> ");
        System.out.println(r);

    }

    /**
    * 模板引擎是指，定义一个字符串作为模板
    */
    @Test
    public void Test6() {
        String s = "Hello, ${name}! You are learning ${lang}!";
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "Bob");
        map.put("lang", "Java");

        Pattern pattern = Pattern.compile("\\$\\{(\\w+)\\}");
        Matcher matcher = pattern.matcher(s);
/*        while (matcher.find()) {
            String name = matcher.group(1);
            System.out.println(name);
            String value = map.get(name);
            s = s.replaceAll("\\$\\{" + name + "}", value);
        }*/

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            //从头开始将 s 的字符复制到sb上直到有字符被替换。第一次复制完成，sb里面存的是“Hello, Bob”
            matcher.appendReplacement(sb,map.get(matcher.group(1)).toString());
        }//循环结束时sb里面存的是“Hello, Bob! You are learning java”
        matcher.appendTail(sb);//将最后一次替换后剩下的字符复制到sb上
        System.out.println(sb);

    }

    boolean isValidMobileNumber(String s) {
        return s.matches("1\\d{10}");
    }



    @Test
    public void Test7() {
        String url = "https://hotels.ctrip.com/hotels/detail/?hotelId=1211513515&jajdij=kdowk";
        Pattern pattern = Pattern.compile("hotelId=(\\d+)");
        Matcher matcher = pattern.matcher(url);
        if (matcher.matches()) {
            System.out.println(matcher.group(0));
            System.out.println("group1=" + matcher.group(1));
            System.out.println("group2=" + matcher.group(2));

        }

        if (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
//            System.out.println(matcher.group(2));
        }

//        List<T> list = new ArrayList<T>();
    }

    @Test
    public void Test8() {
        Date startDate = DateUtil.date();
        Date endDate = DateUtil.date();
        List<Date> list = new ArrayList<>();
        for (Date date = startDate; date.getTime() <= endDate.getTime(); date = DateUtil.offsetDay(date, 1)) {
            list.add(date);
        }
        System.out.println(list);
    }



}
