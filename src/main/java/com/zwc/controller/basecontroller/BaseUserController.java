package com.zwc.controller.basecontroller;

import com.zwc.annotation.RateLimit;
import com.zwc.common.BootstrapTablePageVO;
import com.zwc.common.Constants;
import com.zwc.common.JsonResponse;
import com.zwc.model.basemodel.BaseUser;
import com.zwc.service.baseservice.BaseUserService;
import com.zwc.utils.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
            @ApiResponse(code = Constants.ERROR, message = Constants.ERROR_MSG),
            @ApiResponse(code = 999, message = "系统繁忙！")
    })
    @RateLimit(permitsPerSecond = 200)
    @GetMapping("/selectUserList")
    public JsonResponse selectUserList() {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(Constants.SUCCESS);
        jsonResponse.setMessage(Constants.SUCCESS_MSG);
        try {
            BootstrapTablePageVO<BaseUser> tablePageVO = baseUserService.selectUserList();
            String a = "abc";

            String b = "abc";

            if (a == b )
                System.out.println("a==b");
            else
                System.out.println("a!=b");
            jsonResponse.setData(tablePageVO);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setCode(Constants.ERROR);
            jsonResponse.setMessage(Constants.ERROR_MSG);
        }
        return jsonResponse;
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 17:40 2019/8/22
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO  新增或修改用户，有ID则是修改，反之为新增
     **/
    @ApiOperation(value = "新增或修改用户", httpMethod = "POST", notes = "有ID是修改，反之为新增")
    @ApiResponses({
            @ApiResponse(code = Constants.SUCCESS, message = Constants.SUCCESS_MSG),
            @ApiResponse(code = Constants.ERROR, message = Constants.ERROR_MSG),
    })
    @PostMapping("/insertOrUpdateUser")
    public JsonResponse insertOrUpdateUser(@RequestBody BaseUser baseUser, HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(Constants.SUCCESS);
        jsonResponse.setMessage(Constants.SUCCESS_MSG);
        // 判断参数不为空
        if (CommonUtil.nonNull(baseUser)) {
            try {
                baseUserService.insertOrUpdateUser(baseUser);
            } catch (Exception e) {
                e.printStackTrace();
                jsonResponse.setCode(Constants.ERROR);
                jsonResponse.setMessage(Constants.ERROR_MSG);
            }
        } else {
            jsonResponse.setCode(Constants.ERROR);
            jsonResponse.setMessage(Constants.ERROR_MSG);
        }
        return jsonResponse;
    }
}
