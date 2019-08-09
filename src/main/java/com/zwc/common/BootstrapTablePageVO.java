package com.zwc.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: BootstrapTablePageVO
 * @ProjectName: housemanage
 * @Author: 郑稳超 zwc_503@163.com
 * @Date： 2019/7/17 11:52
 * @Description: //TODO
 * @Version: 1.0
 */
@Data
public class BootstrapTablePageVO<T> implements Serializable {

    private static final long serialVersionUID = 5272841146582345667L;

    /* 总条数*/
    private Long total;

    /* 总页数*/
    private Long pages;

    /* 数据集合*/
    private List<T> dataList;
}
