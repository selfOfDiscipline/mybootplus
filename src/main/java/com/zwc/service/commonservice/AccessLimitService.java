package com.zwc.service.commonservice;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-12 16:27
 * @Description: //TODO API接口限流方法   注入法
 **/
@Service
public class AccessLimitService {
    
    /* 每秒释放5个令牌，实际会+1*/
    RateLimiter rateLimiter = RateLimiter.create(5);

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 16:30 2019/8/12
     * @Param 
     * @return 
     * @Version 1.0
     * @Description //TODO 获取令牌方法
     **/
    public boolean tryAcquire() {
        return rateLimiter.tryAcquire();
    }
}