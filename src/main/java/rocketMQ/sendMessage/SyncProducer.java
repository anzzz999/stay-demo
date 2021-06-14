package rocketMQ.sendMessage;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import rocketMQ.Constant;


/**
 * 1.2 消息发送
 * 1、Producer端发送同步消息
 * 这种可靠性同步地发送方式使用的比较广泛，比如：重要的消息通知，短信通知。
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("yueanju");
        producer.setNamesrvAddr(Constant.NAMESRV_ADDR);
        producer.start();
        for (int i = 0; i < 100; i++) {
            Message msg = new Message("yueanju_topic","Tag"+ i%5,("Hello RocketMQ "+ i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);

            System.out.printf("%s%n", sendResult);
        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }
}
