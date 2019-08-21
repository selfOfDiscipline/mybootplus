package com.zwc.config;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-21 15:41
 * @Description: //TODO rabbitmq配置类
 **/
@Configuration
public class RabbitMQConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /* 主机*/
    @Value("${spring.rabbitmq.host}")
    private String host;

    /* 端口*/
    @Value("${spring.rabbitmq.port}")
    private int port;

    /* 账号*/
    @Value("${spring.rabbitmq.username}")
    private String username;

    /* 密码*/
    @Value("${spring.rabbitmq.password}")
    private String password;

    /* 队列*/
    public static final String BOOTPLUS_QUEUE = "bootplus_queue";

    /* 死亡消息队列*/
    public static final String DEAD_BOOTPLUS_QUEUE = "dead_bootplus_queue";

    /* 交换机*/
    public static final String EX_BOOTPLUS_QUEUE = "ex_bootplus_queue";

    /* 死亡交换机*/
    public static final String EX_DEAD_BOOTPLUS_QUEUE = "ex_dead_bootplus_queue";

    /* 路由键*/
    public static final String BOOTPLUS_KEY = "bootplus_key";

    /* 死亡路由键*/
    public static final String DEAD_BOOTPLUS_KEY = "dead_bootplus_key";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory(host, port);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost("/");
        // 如果要进行消息回调，则这里必须要设置为true
        factory.setPublisherConfirms(true);
        return factory;
    }

    @Bean
    // 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
//        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 16:20 2019/8/21
     * @Param 
     * @return 
     * @Version 1.0
     * @Description //TODO
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     **/
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EX_BOOTPLUS_QUEUE);
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 16:22 2019/8/21
     * @Param 
     * @return 
     * @Version 1.0
     * @Description //TODO  获取队列
     **/
    @Bean
    public Queue queue() {
        Map<String, Object> map = Maps.newHashMap();
        // 配置该队列中的消息存活时间为10分钟
        map.put("x-message-ttl", 600000);
        // 配置该队列中过期消息的去处
        map.put("x-dead-letter-exchange", EX_DEAD_BOOTPLUS_QUEUE);
        // 队列持久化
        return new Queue(BOOTPLUS_QUEUE, true, false, false, map);
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 16:23 2019/8/21
     * @Param 
     * @return 
     * @Version 1.0
     * @Description //TODO 将队列 & 交换机 & 路由键   绑定到一起
     *                 一个交换机可以绑定多个队列
     **/
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(RabbitMQConfig.BOOTPLUS_KEY);
    }
}
