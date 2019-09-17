package com.zwc.config;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-09-11 17:52
 * @Description: //TODO websocket服务类
 **/
@ServerEndpoint(value = "/websocket/{userId}")// websocket客户端连接地址的路径
@Component
public class WebSocketServer {

    private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    /**
     *  存活着的session集合 （使用线程安全的并发map保存）
     */
    private static Map<String, Session> liveSessions = Maps.newConcurrentMap();

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 17:57 2019/9/11
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO  建立连接时候的回调方法
     *                 session 与客户端的WebSocket连接会话
     *                 userId  用户名，WebSocket支持路径参数
     **/
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String userId) {
        liveSessions.put(session.getId(), session);
        System.out.println("进入连接的userId = " + userId);
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 18:00 2019/9/11
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 接收前台发过来的数据
     **/
    @OnMessage
    public void onMessage(Session session, @PathParam(value = "userId") String userId, String message) {
        System.out.println("userId = " + userId + "发送过来的消息为：" + message);
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 18:03 2019/9/11
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO  连接关闭时候调用的方法
     **/
    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        liveSessions.remove(session.getId());
        System.out.println("关闭连接的userId = " + userId);
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 18:05 2019/9/11
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 连接发生错误时候
     **/
    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("throwable = " + throwable.getMessage());
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 18:06 2019/9/11
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 单独发送消息
     **/
    public void sendMessage(Session session, String message) {
        try {
            // 同步发送文本格式
            session.getBasicRemote().sendText(message);
            // 异步发送文本格式
//            session.getAsyncRemote().sendText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 18:08 2019/9/11
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 消息发送给每个人
     **/
    public void sendMessageToAll(String message) {
        liveSessions.forEach((sessionId, session) -> {
            sendMessage(session, message);
        });
    }


}
