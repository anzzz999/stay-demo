package work.uitls;

import cn.hutool.core.date.StopWatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


/**
 * @author zhanmingwei
 */
@Slf4j
public class StopWatchTest {

    @Test
    public void test1() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());
        log.info("time:{}", stopWatch.getLastTaskTimeMillis());
    }

    @Test
    public void tes2() throws InterruptedException {
        StopWatch sw = new StopWatch();
        sw.start("A");
        Thread.sleep(500);
        sw.stop();
        sw.start("B");
        Thread.sleep(300);
        sw.stop();
        sw.start("C");
        Thread.sleep(200);
        sw.stop();
//        System.out.println(sw.prettyPrint());
        log.info("prettyPrint:{}", sw.prettyPrint());
        log.info("shortSummary:{}", sw.shortSummary());
        log.info("getTaskCount:{}", sw.getTaskCount());
        log.info("lastTaskName:{}", sw.getLastTaskInfo().getTaskName());
        log.info("time:{}", sw.getLastTaskTimeMillis());
    }
}
