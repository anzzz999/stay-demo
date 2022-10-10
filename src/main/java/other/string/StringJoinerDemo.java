package other.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.StringJoiner;

/**
 * @Author: zhanmingwei
 * <p>
 * 基于StringBuilder实现，add时就把prefix和分隔符给加上了，suffix永远都不加，知道toString和length调用时才加入计算。
 * toString 把suffix加上后，会再把suffix去掉
 * length = (value != null ? value.length() + suffix.length() : emptyValue.length())
 *
 */
@Slf4j
public class StringJoinerDemo {

    @Test
    public void test() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        joiner.add("a");
        // [a]
        log.info(joiner.toString());
        joiner.add("b");
        // [a,b]
        log.info(joiner.toString());
        StringJoiner newJoiner = new StringJoiner("-", "\"", "\"");
        newJoiner.add("1");
        newJoiner.add("2");
        log.info(newJoiner.toString());
        // "1-2"
        joiner.merge(newJoiner);
        // [a,b,1-2]
        log.info(joiner.toString());
        log.info(joiner.toString());
    }


}
