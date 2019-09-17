package com.zwc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-09-11 17:51
 * @Description: //TODO websocket配置类
 **/
@Configuration
public class WebSocketConfig {

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 17:51 2019/9/11
     * @Param []
     * @return org.springframework.web.socket.server.standard.ServerEndpointExporter
     * @Version 1.0
     * @Description //TODO 注入ServerEndpointExporter，
     *                 这个bean会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint
     **/
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
