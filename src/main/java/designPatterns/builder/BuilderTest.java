package designPatterns.builder;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @Author: zhanmingwei
 */
@Slf4j
public class BuilderTest {

    @Test
    public void Test1(){

        ConstructorArg constructorArg0 = new ConstructorArg.Builder().setIsRef(true).build();
        log.info("constructorArg:{}", constructorArg0);

        // type为 String.class
        ConstructorArg constructorArg1 = new ConstructorArg.Builder().setIsRef(true).setType(Long.class).build();
        log.info("constructorArg:{}", constructorArg1);


        ConstructorArg constructorArg2 = new ConstructorArg.Builder().setIsRef(false).setType(Long.class).setArg(1000L).build();
        log.info("constructorArg:{}", constructorArg2);

        // false 缺少参数
        ConstructorArg constructorArg3 = new ConstructorArg.Builder().setIsRef(false).setType(Long.class).build();
        log.info("constructorArg:{}", constructorArg3);
    }
}
