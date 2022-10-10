package clazz;

import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;

/**
 * 反射的三种方式
 */
public class ClazzReflection {


    @Data
    @Accessors(chain = true)
    public static class User{
        private Long id;
        private String name;
        private Integer age;
    }

    @Test
    public void Test1(){

        try {

            // 第一、通过Class.forName方式
            Class user1 = Class.forName("clazz.ClazzReflection$User");

            // 第二、通过对象实例方法获取对象
            Class user2 = User.class;

            // 第三、通过Object类的getClass方法
            User user = new User();
            Class user3 = user.getClass();

            System.out.println(user1);
            System.out.println(user2);
            System.out.println(user3);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }

}
