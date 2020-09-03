package com.im.chat.mq;

import com.alibaba.fastjson.JSON;
import com.im.chat.service.SessionViewRedundantUpdation;
import com.mr.response.error.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Slf4j
public class MqConsumer {

    private DefaultMQPushConsumer consumer;
    @Value("${mq.nameserver.addr}")
    private String nameAddr;

    @Value("${mq.topicname}")
    private String topicName;

    @Autowired
    private SessionViewRedundantUpdation sessionViewRedundantUpdation;

    @PostConstruct
    public void init() throws MQClientException {
        consumer = new DefaultMQPushConsumer("stock_consumer_group");
        consumer.setNamesrvAddr(nameAddr);
        consumer.subscribe(topicName,"*");
        log.info("rocketmq开始监听");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                log.info("接受消息m");

                try
                {
                    Message msg = msgs.get(0);
                    log.info(String.valueOf(msgs.size()));
                    String jsonString  = new String(msg.getBody());
                    log.info(jsonString);
                    Map<String,String> map = JSON.parseObject(jsonString, Map.class);
                    Long userId = Long.parseLong(map.get("userId"));
                    String userName = (String) map.get("userName");
                    String avatarUrl = (String) map.get("avatarUrl");

                    log.info("userId: {}  userName {}", userId, userName);

                    try {
                        log.info("开始更新");
                        sessionViewRedundantUpdation.sessionViewUserRedundantUpdatate(userId,userName,avatarUrl);
                    } catch (BusinessException e) {
                        e.printStackTrace();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

    }
}