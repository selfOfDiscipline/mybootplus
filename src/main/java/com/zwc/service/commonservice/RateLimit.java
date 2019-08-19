package com.zwc.service.commonservice;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-12 17:18
 * @Description: //TODO rateLimit接口限流自定义注解
 *              @Inherited 注解和普通类的区别是如果一个子类想获取到父类上的注解信息，
 *              那么必须在父类上使用的注解上面 加上@Inherit关键字
 **/
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {

    /* 每秒发放令牌书，默认为10个令牌，实际是10 + 1 = 11个*/
    double permitsPerSecond() default 10;

    /* 超时时间，即能否在指定时间内得到令牌，没得到则立即返回，不进入目标类或目标方法，默认为0，即不等待，获取不到立即返回*/
    long timeOut() default 0;

    /* 超时时间单位，默认为毫秒*/
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

    // 无法获取令牌返回提示信息 默认值可以自行修改
    String msg() default "系统繁忙，请稍后再试！";
}