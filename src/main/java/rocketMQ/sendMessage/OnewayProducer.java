package rocketMQ.sendMessage;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import rocketMQ.Constant;

/**
 * 1.2 消息发送
 * 3、单向发送消息
 * 这种方式主要用在不特别关心发送结果的场景，例如日志发送。
 */
public class OnewayProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("an_zzz_firstMQ_group_name");
        producer.setNamesrvAddr(Constant.NAMESRV_ADDR);
        producer.start();
        for (int i = 0; i < 100; i++) {
            Message msg = new Message("TopicTest","TagA",("Hello RocketMQ "+ i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            //单向发送消息，没有返回值
            producer.sendOneway(msg);
        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }
}
