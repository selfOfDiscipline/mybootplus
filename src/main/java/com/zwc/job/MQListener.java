package com.zwc.job;

import com.zwc.config.RabbitMQConfig;
import com.zwc.config.WebSocketServer;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-09-11 17:48
 * @Description: //TODO mq监听
 **/
@Component
public class MQListener {

    @Autowired
    private WebSocketServer webSocketServer;

    @RabbitListener(queues = RabbitMQConfig.BOOTPLUS_QUEUE)
    @RabbitHandler
    public void receiveBootQueue(String message) {
        webSocketServer.sendMessageToAll(message);
    }

    //    //  AES加密解密
//    private String sKey = "785641234654";
//    private String ivParamter = "0392039203";

//    public JSONObject decrypt(String message) {
//        try {
//            byte[] raw = sKey.getBytes("ASCII");
//            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            IvParameterSpec iv = new IvParameterSpec(ivParamter.getBytes());
//            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(message);// 先用 base64 解密
//            byte[] original = cipher.doFinal(encrypted1);
//            String originalString = new String(original, "utf-8");
//            JSONObject object = JSON.parseObject(originalString);
//            JSONObject object = JSONObject.parseObject(message);
//            // websocket发送消息
//            webSocketServer.sendMessageToAll(object.toJSONString());
//            System.out.println("推送到前台成功--队列");
//            log.info("监听消息：" + object);
//            return object;
//        } catch (Exception ex) {
//            return null;
//        }
//    }
}
