package annotations.repeatable;

import java.lang.annotation.*;

// 容器注解Skills
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Skills {

    Skill[] value();
}