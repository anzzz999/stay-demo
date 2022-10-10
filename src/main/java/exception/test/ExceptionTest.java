package exception.test;

import exception.TestException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class ExceptionTest {

    // 主线程不会停止
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
//            while (true) {
            while (!Thread.interrupted()) {
                System.out.println("hello");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // 关键代码，通过调用interrupt()中断方法并不能直接终止另一个线程，而是需要被中断的线程自己处理中断请求。
                    // 在catch代码块中调用Thread.currentThread().interrupt()方法将线程的中断状态设置为true,以便其他操作可以感知到此线程被中断了
                    // 将中断状态置位true
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Thread.sleep(888);
        thread.interrupt();
    }

    @Test
    @SneakyThrows
    public void test1() {

        throw new TestException("测试异常");

    }

}
