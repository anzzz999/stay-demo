package test;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.K;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author zhanmingwei
 */
@Slf4j
public class MapDemo {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        // 对key进行操作
        map.compute("a", (k, v) -> v != null && v < 10 ? Integer.valueOf(v + 10) : v);
        log.info("{}", map);

        // 如果不存在则添加
        map.putIfAbsent("a", 1);
        map.putIfAbsent("d", 4);
        log.info("{}", map);

        // 是否包含value
        boolean b = map.containsValue(4);
        log.info("map containsValue: {}", b);

        // 如果缺席则进行操作
        map.computeIfAbsent("a", getFunction());
        map.computeIfAbsent("f", getFunction());
        log.info("{}", map);

        // 如果存在则进行操作
        map.computeIfPresent("a", getBiFunction());
        log.info("{}", map);

        // 对所有的key操作
        map.replaceAll((k, v) -> v * 2);
        log.info("{}", map);
    }

    private static Function<String, Integer> getFunction() {
        return v -> 100;
    }

    private static BiFunction<String, Integer, Integer> getBiFunction() {
        return (k, v) -> Objects.equals(k, "a") ? v + 100 : v;
    }


    /**
     * 模拟map的函数方法
     */
    @Test
    public void test() {
        Entry<String, Integer> entry = new Entry<>();
        Integer mwVal = entry.computeIfAbsent("mw", v -> 100);
        log.info("entry:{}, mwVal:{}", entry, mwVal);
        entry.compute((k, v) -> k != null && v != null ? Integer.valueOf(v + 100) : v);
        log.info("{}", entry);
    }

    @Data
    public static class Entry<K, V> {
        private K key;
        private V value;

        public V compute(BiFunction<? super K, ? super V, ? extends V> function) {
            value = function.apply(key, value);
            return value;
        }

        public V computeIfAbsent(K key, Function<? super K, ? extends V> function) {
            if (this.key == null && value == null) {
                this.key = key;
                value = function.apply(key);
            }
            return value;
        }
    }
}
