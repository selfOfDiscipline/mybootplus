package com.zwc.service.baseservice;

import com.zwc.common.BootstrapTablePageVO;
import com.zwc.model.basemodel.BaseUser;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-08 09:46
 * @Description: //TODO 用户模块接口
 **/
public interface BaseUserService {

    BootstrapTablePageVO<BaseUser> selectUserList();

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 17:38 2019/8/22
     * @Param 
     * @return 
     * @Version 1.0
     * @Description //TODO  新增用户
     **/
    void insertOrUpdateUser(BaseUser baseUser);
}