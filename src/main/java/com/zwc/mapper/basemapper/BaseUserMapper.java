package com.zwc.mapper.basemapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zwc.model.basemodel.BaseUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-08 09:48
 * @Description: //TODO 用户模块mapper接口
 **/
public interface BaseUserMapper extends BaseMapper<BaseUser> {

    List<BaseUser> selectUserList();
}