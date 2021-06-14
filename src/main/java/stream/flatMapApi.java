package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 如果使用的是map方法，返回的是[ ...['y', 'o', 'u', 'r'], ['n', 'a', 'm', 'e']]
 *
 * 如果使用的是flatMap方法，返回的是['y', 'o', 'u', 'r', 'n', 'a', 'm', 'e']
 *
 * 这是map和flatMap的区别
 */
public class flatMapApi {
    public static void main(String[] args) {
        String[]  strings= {"123", "456", "789"};
        List<Stream<String>> list = Arrays.stream(strings).map(flatMapApi::letters).collect(Collectors.toList());
        List<String> collect = Arrays.stream(strings).flatMap(flatMapApi::letters).collect(Collectors.toList());
    }

    public static Stream<String> letters(String s){
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            result.add(s.substring(i, i + 1));
        }
        return result.stream();
    }
}
