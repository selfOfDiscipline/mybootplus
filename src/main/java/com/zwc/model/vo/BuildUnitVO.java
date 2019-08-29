package com.zwc.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-28 13:53
 * @Description: //TODO 楼栋单元VO
 **/
@Data
public class BuildUnitVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /* 楼栋名称*/
    private String bldName;

    /* 该楼栋下的单元集合*/
    private List<String> unitList;
}
