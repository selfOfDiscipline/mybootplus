package com.zwc.config;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.zwc.common.JsonResponse;
import com.zwc.service.commonservice.RateLimit;
import org.apache.ibatis.annotations.Results;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-12 17:42
 * @Description: //TODO rateLimit自定义注解的切面类
 *
 *  使用Guava的RateLimiter实现限流，每秒最大10个请求，仅适用于单体
 *  不保证公平访问
 *  允许先消费，后付款，就是它可以来一个请求的时候一次性取走几个或者是剩下所有的令牌甚至多取
 *  但是后面的请求就得为上一次请求买单，它需要等待桶中的令牌补齐之后才能继续获取令牌
 *  所以实际上每秒能够通过的数量会比设置的permitsPerSecond大
 *  在设置permitsPerSecond的时候应比实际估计的流量要小
 *  限制能卖的总包子
 *
 **/
@Scope // 该注解默认为单例模式
@Aspect
@Component
public class RateLimitAspect {

    /* 存放RateLimiter,一个url对应一个令牌桶*/
    private Map<String, RateLimiter> limiterMap = Maps.newConcurrentMap();

    @Pointcut("@annotation(com.zwc.service.commonservice.RateLimit)")
    private void pointCut() {}

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    Object obj = null;
        // 获取注解
        RateLimit rateLimit = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(RateLimit.class);
        // 获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取url
        String url = request.getRequestURI();
        // 判断注解非空
        if (rateLimit != null) {
            // 获取permitsPerSecond & timeOut & timeUnit
            double permitsPerSecond = rateLimit.permitsPerSecond();
            long timeOut = rateLimit.timeOut();
            TimeUnit timeUnit = rateLimit.timeUnit();
            String msg = rateLimit.msg();
            // 定义令牌桶
            RateLimiter rateLimiter = null;
            // 判断map中是否有已经创建好的令牌桶，若是第一次请求该url，则创建新的令牌桶
            if (!limiterMap.containsKey(url)) {
                // 没有，故创建新的令牌桶
                rateLimiter = RateLimiter.create(permitsPerSecond);
                limiterMap.put(url, rateLimiter);
                System.out.println("请求======>" + url + "创建令牌桶，容量为：" + permitsPerSecond);
            }
            // 从保存的map中取到令牌桶
            rateLimiter = limiterMap.get(url);
            // 若得到令牌
            if (rateLimiter.tryAcquire(timeOut, timeUnit)) {
                // 开始执行目标方法
                obj = joinPoint.proceed();
            } else {
                // 否则直接返回错误信息
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setCode(999);
                jsonResponse.setMessage(msg);
                obj = jsonResponse;
            }
        }
        return obj;
    }
}
