package com.zwc.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-08 13:55
 * @Description: //TODO 全局事务配置类
 **/
@Aspect
@Configuration
public class TransactionAdviceConfig {

    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.***.service..*.*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {

        /* 定义默认事务*/
        DefaultTransactionAttribute attr_REQUIRED = new DefaultTransactionAttribute();
        attr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        /* 定义默认事务 && 开启只读*/
        DefaultTransactionAttribute attr_REQUIRED_READONLY = new DefaultTransactionAttribute();
        attr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        attr_REQUIRED_READONLY.setReadOnly(true);

        /* 定义事务匹配规则*/
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("save*", attr_REQUIRED);
        source.addTransactionalMethod("delete*", attr_REQUIRED);
        source.addTransactionalMethod("update*", attr_REQUIRED);
        source.addTransactionalMethod("insert*", attr_REQUIRED);
        source.addTransactionalMethod("exec*", attr_REQUIRED);
        source.addTransactionalMethod("set*", attr_REQUIRED);
        source.addTransactionalMethod("get*", attr_REQUIRED_READONLY);
        source.addTransactionalMethod("query*", attr_REQUIRED_READONLY);
        source.addTransactionalMethod("find*", attr_REQUIRED_READONLY);
        source.addTransactionalMethod("list*", attr_REQUIRED_READONLY);
        source.addTransactionalMethod("count*", attr_REQUIRED_READONLY);
        source.addTransactionalMethod("select*", attr_REQUIRED_READONLY);

        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
