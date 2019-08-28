package com.zwc.service.baseservice.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.zwc.common.BootstrapTablePageVO;
import com.zwc.mapper.basemapper.BaseUserMapper;
import com.zwc.model.basemodel.BaseUser;
import com.zwc.service.baseservice.BaseUserService;
import com.zwc.utils.CommonUtil;
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

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 17:38 2019/8/22
     * @Param 
     * @return 
     * @Version 1.0
     * @Description //TODO  新增用户的实现类
     **/
    @Override
    public void insertOrUpdateUser(BaseUser baseUser) {
        // 判断对象ID不为空则为修改，为空则为新增
        if (CommonUtil.nonNull(baseUser.getId())) {
            baseUser.setEditTime(new Date());
            baseUserMapper.updateById(baseUser);
        } else {
            baseUser.setDeleteFlag(0);
            baseUser.setCreateTime(new Date());
            baseUserMapper.insert(baseUser);
        }
    }
}
