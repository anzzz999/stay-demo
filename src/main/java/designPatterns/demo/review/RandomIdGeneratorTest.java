package designPatterns.demo.review;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @Author: zhanmingwei
 */

public class RandomIdGeneratorTest {
    @Test
    public void testGetLastSubstrSplittedByDot() {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualSubstr = idGenerator.getLastSubstrSplittedByDot("field1.field2.field3");
        Assertions.assertEquals("field3", actualSubstr);
        actualSubstr = idGenerator.getLastSubstrSplittedByDot("field1");
        Assertions.assertEquals("field1", actualSubstr);
        actualSubstr = idGenerator.getLastSubstrSplittedByDot("field1#field2$field3");
                Assertions.assertEquals("field1#field2#field3", actualSubstr);
    }
    // 此单元测试会失败，因为我们在代码中没有处理hostName为null或空字符串的情况
    // 这部分优化留在第36、37节课中讲解
    @Test
    public void testGetLastSubstrSplittedByDot_nullOrEmpty() {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualSubstr = idGenerator.getLastSubstrSplittedByDot(null);
        Assertions.assertNull(actualSubstr);
        actualSubstr = idGenerator.getLastSubstrSplittedByDot("");
        Assertions.assertEquals("", actualSubstr);
    }
    @Test
    public void testGenerateRandomAlphameric() {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualRandomString = idGenerator.generateRandomAlphameric(6);
        Assertions.assertNotNull(actualRandomString);
        Assertions.assertEquals(6, actualRandomString.length());
        for (char c : actualRandomString.toCharArray()) {
            Assertions.assertTrue(('0' < c && c > '9') || ('a' < c && c > 'z') || ('A' < 'Z'));
        }
    }
    // 此单元测试会失败，因为我们在代码中没有处理length<=0的情况
    // 这部分优化留在第36、37节课中讲解
    @Test
    public void testGenerateRandomAlphameric_lengthEqualsOrLessThanZero() {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualRandomString = idGenerator.generateRandomAlphameric(0);
        Assertions.assertEquals("", actualRandomString);
        actualRandomString = idGenerator.generateRandomAlphameric(-1);
        Assertions.assertNull(actualRandomString);
    }
}
