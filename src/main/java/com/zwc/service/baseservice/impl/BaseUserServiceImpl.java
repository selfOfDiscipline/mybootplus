package com.zwc.service.baseservice.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.zwc.common.BootstrapTablePageVO;
import com.zwc.mapper.basemapper.BaseUserMapper;
import com.zwc.model.basemodel.BaseUser;
import com.zwc.service.baseservice.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-08 09:46
 * @Description: //TODO 用户模块实现类
 **/
@Service
public class BaseUserServiceImpl implements BaseUserService {

    @Autowired
    private BaseUserMapper baseUserMapper;

    @Override
    public BootstrapTablePageVO<BaseUser> selectUserList() {
        // 第一遍查询
        List<BaseUser> dataList = baseUserMapper.selectList(Condition.create().eq("delete_flag", 0));
        // 第一遍查询
        List<BaseUser> dataList3 = baseUserMapper.selectUserList();
        BootstrapTablePageVO<BaseUser> tablePageVO = new BootstrapTablePageVO<>();
        tablePageVO.setTotal(Long.valueOf(dataList.size()));
        tablePageVO.setDataList(dataList);
        return tablePageVO;
    }
}
