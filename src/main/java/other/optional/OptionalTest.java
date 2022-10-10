package other.optional;

import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class OptionalTest {

    // 创建Optional容器
    @Test
    public void test(){
        User user1 = new User();
        User user2 = null;

        Optional.of(user1);
//        Optional.of(user2);  // 报错

        Optional.ofNullable(user1);
        Optional.ofNullable(user2);
    }

    // Optional容器简单的方法
    @Test
    public void test2(){
//        User user1 = new User();
        User user1 = null;

        Optional<User> optional1 = Optional.ofNullable(user1);
        System.out.println(optional1.isPresent());
        System.out.println(optional1.orElse(new User().setId(1L)));
        System.out.println(optional1.get());
    }

    // Optional容器简单的方法
    @Test
    public void test3(){
        User user = new User();
        user.setUserName("an_zzz");
        test(user);
    }

    public static void test(User user) {
        Optional<User> optional = Optional.ofNullable(user);

        // 如果存在user，则打印user的name
        optional.ifPresent((value) -> System.out.println(value.getUserName()));

        // 旧写法
        if (user != null) {
            System.out.println(user.getUserName());
        }
    }

    // orElseGet和orElseThrow方法
    @Test
    public void test4(){
        User user = new User();
        user.setUserName("an_zzz");
        user = null;
        Optional<User> optional = Optional.ofNullable(user);
        User user1 = optional.orElseGet(() -> new User());
        System.out.println(user1);
//        user1 = null;
        user1 = optional.orElseThrow(RuntimeException::new);
        System.out.println(user1);
    }

    // filter方法
    @Test
    public void test5(){
        User user = new User();
        user.setUserName("an_zzz");
        Optional<User> optional = Optional.ofNullable(user);
        Optional<User> user1 = optional.filter((val) -> "an_zzz".equals(val));
        System.out.println(user1.isPresent());
    }

    // map方法
    @Test
    public void test6(){
        User user = new User();
//        user.setUserName("an_zzz");
        Optional<User> optional = Optional.ofNullable(user);
        System.out.println(optional.map(user1 -> user.userName).orElse("Unknow"));
    }


    @Test
    public void test7(){
        User user = new User();
        user.setUserName("an_zzz");
        String username = Optional.ofNullable(user)
                .map(user1 -> user1.getUserName())
                .map(name -> name.toUpperCase())
                .orElse("UnKnow");
        System.out.println(username);
        HashMap<String, Integer> map = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            entry.
        }
    }

    @Data
    @Accessors(chain = true)
    class User{
        private Long id;
        private String userName;
        private Short age;
    }
}
