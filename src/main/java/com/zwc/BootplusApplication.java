package com.zwc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//todo springboot项目启动注解
@SpringBootApplication
//todo exclude = DataSourceAutoConfiguration.class这句话加了之后会报无法创建实体bean
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//todo 加上之后，统一扫描mapper包下的mapper文件，不加则需要在每一个想要扫描到的mapper文件上单独加@Mapper注解
@MapperScan(basePackages = "com.zwc.mapper")
public class BootplusApplication {

    public static void main(String[] args) {
        //System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(BootplusApplication.class, args);
    }

}
