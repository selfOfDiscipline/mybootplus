package com.zwc.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 房源表
 * </p>
 *
 * @author zwc
 * @since 2019-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BootHouseResource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 房源同步guid
     */
    private String roomGuid;
    /**
     * 房源名称
     */
    private String roomCode;
    /**
     * 项目ID
     */
    private Long projGuid;
    /**
     * 分期ID
     */
    private Long projChildGuid;
    /**
     * 分期名称
     */
    private String projChildName;
    /**
     * 楼陈名称
     */
    private String bldName;
    /**
     * 单元名称
     */
    private String unit;
    /**
     * 当前层数
     */
    private String floor;
    /**
     * 房间类型
     */
    private String bProductTypeName;
    /**
     * 房间号
     */
    private String no;
    /**
     * 总价
     */
    private BigDecimal total;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 建筑面积
     */
    private String bldArea;
    /**
     * 户型
     */
    private String huXing;
    /**
     * 户型结构
     */
    private String roomStru;
    /**
     * 分组表id
     */
    private Long groupId;
    /**
     * 活动表ID
     */
    private Long activityId;
    /**
     * 更新时间
     */
    private Date gxTime;
    /**
     * 删除标志：0代表未删除，1代表已删除
     */
    private Integer deleteFlag;
    /**
     * 创建人id
     */
    private String createId;
    /**
     * 创建人名称
     */
    private String createName;
    /**
     * 修改人id
     */
    private String editId;
    /**
     * 修改人名称
     */
    private String editName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date editTime;
    /**
     * 出售标记 0:未售 1:已售
     */
    private Integer status;


}
