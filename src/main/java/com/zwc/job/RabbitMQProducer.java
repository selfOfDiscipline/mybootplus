package com.zwc.job;

import com.zwc.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-21 16:06
 * @Description: //TODO rabbitMq生产者
 **/
@Component
public class RabbitMQProducer implements RabbitTemplate.ConfirmCallback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE原型，所以不能自动注入
    private RabbitTemplate rabbitTemplate;

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 16:11 2019/8/21
     * @Param 
     * @return 
     * @Version 1.0
     * @Description //TODO  构造方法注入rabbitTemplate
     **/
    @Autowired
    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        // 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置
        rabbitTemplate.setConfirmCallback(this);
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 16:15 2019/8/21
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO  把消息推送至该路由键绑定的队列
     **/
    public void sendMsg(String message) {
        CorrelationData correlationID = new CorrelationData(UUID.randomUUID().toString());
        // 把消息推送至mq
        rabbitTemplate.convertAndSend(RabbitMQConfig.EX_BOOTPLUS_QUEUE, RabbitMQConfig.BOOTPLUS_KEY, message, correlationID);
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 16:16 2019/8/21
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 重写回调方法
     **/
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("回调ID：" + correlationData);
        if (ack) {
            logger.info("消息消费成功！");
        } else {
            logger.info("消息消费失败！原因为：" + cause);
        }
    }
}
