package com.zwc.controller.bootcontroller;

import com.zwc.service.bootservice.BootHouseResourceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-28 10:06
 * @Description: //TODO 房源配置模块
 **/
@Api(tags = "房源配置模块")
@RestController
@RequestMapping(value = "/boot/houseResource")
public class BootHouseResourceController {

    @Autowired
    private BootHouseResourceService bootHouseResourceService;

}
