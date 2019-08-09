package com.zwc.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-08 09:55
 * @Description: //TODO 公共结果类
 **/
@Data
public class JsonResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /* 返回值：默认20000代表请求成功，10000代表请求失败。*/
    private int code;

    /* 返回警示信息*/
    private String message;

    /* 返回数据信息*/
    private Object data;
}
