package com.zwc.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;


/**
 * @ClassName: HttpClientUtil
 * @ProjectName: myboot2
 * @Author: 郑稳超 zwc_503@163.com
 * @Date： 2019/4/4 12:01
 * @Description:
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /*
     * @Author: 郑稳超 zwc_503@163.com
     * @Date: 2019/5/20 9:31
     * @Param:
     * @return:
     * @Version:
     * @Description: //TODO HttpGet方式
     **/
    public static String HttpGet(String url) {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet(url);
            logger.debug("executing request " + httpget.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                logger.debug("--------------------------------------");
                // 打印响应状态
                logger.debug(response.getStatusLine().toString());
                if (entity != null) {
                    // 打印响应内容长度
                    logger.debug("Response content length: " + entity.getContentLength());
                    // 打印响应内容
                    result = EntityUtils.toString(entity);
                    logger.debug("Response content: " + result);
                }
                logger.debug("------------------------------------");
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /*
     * @Author: 郑稳超 zwc_503@163.com
     * @Date: 2019/5/20 9:19
     * @Param:
     * @return:
     * @Version:
     * @Description: //TODO HttpPost请求，参数map
     **/
    public static String HttpPost(String url, Map< String, String> params) {
        String result = null;
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列
        List<BasicNameValuePair> formparams = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            logger.debug("key= " + entry.getKey() + " and value= " + entry.getValue());
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            logger.debug("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, "UTF-8");
                    logger.debug("--------------------------------------");
                    logger.debug("Response content: " + result);
                    logger.debug("--------------------------------------");
                    return result;
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /*
     * @Author: 郑稳超 zwc_503@163.com
     * @Date: 2019/5/20 9:20
     * @Param:
     * @return:
     * @Version:
     * @Description: //TODO  HttpPost请求，参数JSONObject
     **/
    public static String HttpPostJson(String url, JSONObject json){
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(20000).setConnectionRequestTimeout(20000)
                .setSocketTimeout(20000).build();
        post.setConfig(requestConfig);
        // 构造消息头
        post.setHeader("Content-type", "application/json; charset=utf-8");
        post.setHeader("Connection", "Close");
        // 构建消息实体
        StringEntity entity = new StringEntity(json.toString(), Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");
        // 发送Json格式的数据请求
        entity.setContentType("application/json");
        post.setEntity(entity);
        try{
            CloseableHttpResponse response = httpclient.execute(post);
            HttpEntity httpEntity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(httpEntity, "UTF-8");
            }
            response.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}