package com.zwc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-09 13:19
 * @Description: //TODO 配置文件获取类
 **/
public class ConfigPropertyUtil {

    private static final String CONFIG_PATH= "/config.properties";

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 13:24 2019/8/9
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 根据配置文件中的key，获取相对应的value
     **/
    public static String getValueByKey(String key){
        Properties prop =  new  Properties();
        InputStream in = ConfigPropertyUtil.class.getResourceAsStream(CONFIG_PATH);
        String value = "";
        try{
            prop.load(in);
            value = prop.getProperty(key).trim();
        }catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 13:24 2019/8/9
     * @Param 
     * @return 
     * @Version 1.0
     * @Description //TODO 根据所传配置文件路径 && key，获取value
     **/
    public static String getValueByKey(String configPath,String key){
        Properties prop =  new  Properties();
        InputStream in = ConfigPropertyUtil.class.getResourceAsStream(configPath);
        String value = "";
        try{
            prop.load(in);
            value = prop.getProperty(key).trim();
        }catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }
}
