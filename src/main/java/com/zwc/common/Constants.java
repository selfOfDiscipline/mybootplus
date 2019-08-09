package com.zwc.common;

import java.io.Serializable;

/**
 * @ClassName: Constants
 * @ProjectName: housemanage
 * @Author: 郑稳超 zwc_503@163.com
 * @Date： 2019/7/16 15:40
 * @Description: //TODO 请求常量类
 * @Version: 1.0
 */
public class Constants implements Serializable {

    public static final long serialVersionUID = 1L;

    /* 请求成功Code*/
    public static final int SUCCESS = 20000;

    /* 请求失败Code*/
    public static final int ERROR = 10000;

    /* 请求成功信息*/
    public static final String SUCCESS_MSG = "请求成功！";

    /* 请求失败信息*/
    public static final String ERROR_MSG = "请求错误！";

    /* user_session键*/
    public static final String USER_SESSION = "userSession";
}
