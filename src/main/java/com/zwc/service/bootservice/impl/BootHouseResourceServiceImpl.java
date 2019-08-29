package com.zwc.service.bootservice.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.google.common.collect.Lists;
import com.zwc.mapper.bootmapper.BootHouseResourceMapper;
import com.zwc.model.bootmodel.BootHouseResource;
import com.zwc.model.vo.BuildUnitVO;
import com.zwc.service.bootservice.BootHouseResourceService;
import com.zwc.utils.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-28 10:01
 * @Description: //TODO 房源接口实现类
 **/
@Service
public class BootHouseResourceServiceImpl implements BootHouseResourceService {

    @Autowired
    private BootHouseResourceMapper bootHouseResourceMapper;

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 10:21 2019/8/28
     * @Param 
     * @return 
     * @Version 1.0
     * @Description //TODO 查询
     **/
    @Override
    public void selectHouseResourceList() {
        List<BuildUnitVO> buildList = Lists.newArrayList();
        List<BootHouseResource> houseResourceList = bootHouseResourceMapper.selectList(Condition.create().eq("delete_flag", 0));
        Map<String, List<BootHouseResource>> bldNameMap = houseResourceList.stream().collect(Collectors.groupingBy(BootHouseResource::getBldName));
        for (String bldName:
             bldNameMap.keySet()) {
            BuildUnitVO buildUnitVO = new BuildUnitVO();
            buildUnitVO.setBldName(bldName);
            List<BootHouseResource> unitHouseResourceList = bldNameMap.get(bldName);
            //单元排序
            List<String> unitList = unitHouseResourceList.stream().map(BootHouseResource::getUnit).sorted(Comparator.comparing(a-> NumberUtil.chineseNumber2Int(a.substring(0,a.length()-2)))).distinct().collect(Collectors.toList());
            buildUnitVO.setUnitList(unitList);
            buildList.add(buildUnitVO);
        }
        //楼栋排序
        buildList = buildList.stream().sorted(Comparator.comparing(a -> NumberUtil.chineseNumber2Int(a.getBldName().substring(0, a.getBldName().length() - 2)))).collect(Collectors.toList());
        System.out.println("buildList = " + buildList);
        List<BootHouseResource> collect = houseResourceList.stream().sorted(Comparator.comparing((BootHouseResource a) -> Integer.valueOf(a.getFloor().substring(0, a.getFloor().length() - 1))).reversed()).collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }
}
