package collection;

import java.util.HashSet;

public class ToStringTest {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        System.out.println(set);
//        Arrays.toString(set.toArray());
        System.out.println(set);

        set.stream().forEach(
                item ->{
                    if ("1".equals(item)) {
                        return;
                    }
                    System.out.println(item);
                }
        );
    }
}
