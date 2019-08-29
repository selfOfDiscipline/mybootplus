package com.zwc.controller.bootcontroller;

import com.zwc.common.Constants;
import com.zwc.common.JsonResponse;
import com.zwc.service.bootservice.BootHouseResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 10:18 2019/8/28
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 房源查询
     **/
    @ApiOperation(value = "房源查询", httpMethod = "GET", notes = "详细描述")
    @ApiResponses({
            @ApiResponse(code = Constants.SUCCESS, message = Constants.SUCCESS_MSG),
            @ApiResponse(code = Constants.ERROR, message = Constants.ERROR_MSG)
    })
    @GetMapping(value = "/selectHouseResourceList")
    public JsonResponse selectHouseResourceList(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(Constants.SUCCESS);
        jsonResponse.setMessage(Constants.SUCCESS_MSG);
        try {
            bootHouseResourceService.selectHouseResourceList();
        } catch (Exception e) {
            jsonResponse.setCode(Constants.ERROR);
            jsonResponse.setMessage(Constants.ERROR_MSG);
        }
        return jsonResponse;
    }
}
