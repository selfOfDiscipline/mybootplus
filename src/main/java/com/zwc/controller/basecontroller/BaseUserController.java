package com.zwc.controller.basecontroller;

import com.zwc.common.BootstrapTablePageVO;
import com.zwc.common.Constants;
import com.zwc.common.JsonResponse;
import com.zwc.model.basemodel.BaseUser;
import com.zwc.service.baseservice.BaseUserService;
import com.zwc.utils.JedisUtil;
import com.zwc.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-08 09:42
 * @Description: //TODO 用户模块
 **/
@Api(tags = "用户模块")
@RestController
@RequestMapping(value = "/base/user")
public class BaseUserController {

    @Autowired
    private BaseUserService baseUserService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /*
     * @Author zwc   zwc_503@163.com
     * @Date 10:24 2019/8/8
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO
     **/
    @ApiOperation(value = "查询用户", httpMethod = "GET", notes = "该方法的描述内容")
    @ApiResponses({
            @ApiResponse(code = Constants.SUCCESS, message = Constants.SUCCESS_MSG),
            @ApiResponse(code = Constants.ERROR, message = Constants.ERROR_MSG)
    })
    @GetMapping("/selectUserList")
    public JsonResponse selectUserList() {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(Constants.SUCCESS);
        jsonResponse.setMessage(Constants.SUCCESS_MSG);
        try {
            BootstrapTablePageVO<BaseUser> tablePageVO = baseUserService.selectUserList();
            jsonResponse.setData(tablePageVO);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setCode(Constants.ERROR);
            jsonResponse.setMessage(Constants.ERROR_MSG);
        }
        return jsonResponse;
    }

}
