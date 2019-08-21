package com.zwc.monitor;

import com.zwc.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-21 16:05
 * @Description: //TODO rabbitmq监听器
 **/
@Component
@RabbitListener(queues = RabbitMQConfig.BOOTPLUS_QUEUE)
public class RabbitMQMonitor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 16:30 2019/8/21
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 消费消息
     **/
    @RabbitHandler
    public void hanlder(String message) {
        logger.info("成功接收队列【bootplus_queue】中的消息为：" + message);
    }
}
