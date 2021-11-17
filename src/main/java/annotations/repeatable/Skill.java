package annotations.repeatable;

import java.lang.annotation.*;

/**
* @Author: an_zzz
* @Description: @Repeatable注解： JDK1.8出现的，作用是解决一个类上不能标注重复的注解；
* @Date: 2021/9/26 16:51
*/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(Skills.class)
public @interface Skill {
    String value() default "";
}
