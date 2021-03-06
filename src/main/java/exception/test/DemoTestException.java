package exception.test;

import exception.TestException;

public class DemoTestException {

    public static void main(String[] args) throws InterruptedException {
        throw new TestException("测试异常");
    }
}
