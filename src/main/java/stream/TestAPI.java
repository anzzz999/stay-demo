package stream;

import clazz.ClazzReflection;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Slf4j
public class TestAPI {

    private static List<String> strs = Arrays.asList("a", "a", "b", "b", "c");

    //stream的count，anyMatch，allMatch，noneMatch操作，我们先看下函数的定义
    @Test
    public void count(){
        //count方法，跟List接口的size一样，返回的都是这个集合流的元素的长度，
        // 不同的是，流是集合的一个高级工厂，中间操作是工厂里的每一道工序，我们对这个流操作完成后，可以进行元素的数量的和；
        System.out.println(strs.size());
        System.out.println(strs.stream().filter("a"::equals).count());
    }

    @Test
    public void match(){
//        anyMatch表示，判断的条件里，任意一个元素成功，返回true
//        allMatch表示，判断条件里的元素，所有的都是，返回true
//        noneMatch跟allMatch相反，判断条件里的元素，所有的都不是，返回true

        System.out.println(strs.stream().anyMatch("a"::equals));
        System.out.println(strs.stream().allMatch("a"::equals));
        System.out.println(strs.stream().noneMatch("a"::equals));
    }

    @Test
    public void test1(){
        Long temp = 1234123L;
        ArrayList<Long> list = new ArrayList<>();
        list.add(1234123L);
        list.add(7898679L);
        Long value = list.stream().filter(item -> !temp.equals(item)).findAny()
                .get();
        System.out.println(value);
    }

    @Test
    public void test11(){

        System.out.println(buildQrConnectUrl("1", "https://i.localhome.cn/v/2203101519597?uri=http://localhost:3000/weappLogin&platform=ebooking", "xxx", "yyyy"));;
    }

    private String buildQrConnectUrl(String appId, String redirectUrl, String scope, String state) {
        return StrUtil.format("https://open.weixin.qq.com/connect/qrconnect?appid={}&redirect_uri={}&response_type=code&scope={}&state={}#wechat_redirect",
                appId, URLUtil.encode(redirectUrl), scope, StringUtils.trimToEmpty(state));
    }


    @Test
    public void test12() {
        ArrayList<ClazzReflection.User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            users.add(new ClazzReflection.User().setId((long) i));
        }
        users.add(new ClazzReflection.User().setName("!"));
        Long reduce = users.stream().map(ClazzReflection.User::getId).reduce(0L, Long::sum);
        System.out.println(reduce);

    }

    @Test
    public void test13() {
        String s = JSONUtil.toJsonStr(null);
        String s1 = StrUtil.emptyToNull(null);
        log.info("测试：{}",s);
        log.info("测试：{}",s1);
    }


}
