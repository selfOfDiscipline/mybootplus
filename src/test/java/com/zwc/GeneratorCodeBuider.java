package com.zwc;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-07 17:25
 * @Description: //TODO 数据库映射工具类
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneratorCodeBuider {

    /* 数据库路径*/
    @Value("${spring.datasource.url}")
    private String url;

    /* 账号*/
    @Value("${spring.datasource.username}")
    private String username;

    /* 密码*/
    @Value("${spring.datasource.password}")
    private String password;

    /* 数据库驱动*/
    @Value("${spring.datasource.driver-class-name}")
    private String driverName;

    /* 作者*/
    private String author = "zwc";

    /* 输出路径*/
    private String outPutDir = "E:/outPutDir/";

    /* 输出包名称*/
    private String packageName = "com.zwc";

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 17:46 2019/8/7
     * @Param 
     * @return 
     * @Version 1.0
     * @Description //TODO  输入数据库表名即可，多个表名用英文逗号隔开
     **/
    @Test
    public void generatorCodeBuilder() {
        boolean serviceNameStart = true;
        generatorByTables(serviceNameStart, packageName, "boot_house_resource");
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 17:41 2019/8/7
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 根据表名自动生成model && mapper && controlelr
     **/
    private void generatorByTables(boolean serviceNameStart, String packageName, String... tables) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(this.url)
                .setUsername(this.username)
                .setPassword(this.password)
                .setDriverName(this.driverName);

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)
                .setEntityLombokModel(true)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tables);

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setActiveRecord(false)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setEnableCache(false)
                .setAuthor(author)
                .setOutputDir(outPutDir)
                .setFileOverride(true);

        if (!serviceNameStart) globalConfig.setServiceName("%sService");

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(packageName)
                .setController("controller")
                .setEntity("model");

        new AutoGenerator().setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .execute();
    }
}
