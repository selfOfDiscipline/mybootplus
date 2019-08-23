package com.zwc.tests;

import com.zwc.utils.HttpClientUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-12 16:33
 * @Description: //TODO 接口限流方法的测试类
 **/
public class AccessLimitClient {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static String sendGet(URL url) {
        String result = "";
        BufferedReader in = null;
        try {
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty("accept", "*/*");
            urlConnection.setRequestProperty("connection", "Keep-Alive");
            urlConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            urlConnection.connect();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送请求异常 = [" + e.getMessage() + "]");
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("关闭连接异常 = [" + e.getMessage() + "]");
            }
        }
        return result;
    }

    public void access() throws InterruptedException, MalformedURLException {
        String url = "http://192.168.1.97:8082/zwc/base/user/selectUserList";
//        final URL url = new URL("http://192.168.1.97:8080/wechat/purchase/robPurchase");

        for (int i = 0; i < 500; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程号：" + HttpClientUtil.HttpGet(url));
//                    System.out.println("线程号：" + sendGet(url));
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        AccessLimitClient accessLimitClient = new AccessLimitClient();
        try {
            accessLimitClient.access();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("main方法调用异常 = [" + e.getMessage() + "]");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("main方法调用异常 = [" + e.getMessage() + "]");
        }
    }
}
