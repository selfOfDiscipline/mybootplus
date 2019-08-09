package com.zwc.model.basemodel;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zwc
 * @since 2019-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BaseUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 登录账号
     */
    private String loginAccount;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 年龄，默认为0
     */
    private Integer age;
    /**
     * 性别：0代表男，1代表女，默认为0
     */
    private Integer sex;
    /**
     * 用户盐
     */
    private String userSalt;
    /**
     * 用户手机号
     */
    private String telephone;
    /**
     * 用户备注
     */
    private String remark;
    /**
     * 删除标识：0代表未删除，1代表删除，默认为0
     */
    private Integer deleteFlag;
    /**
     * 创建人账号
     */
    private String createId;
    /**
     * 创建人名称
     */
    private String createName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人账号
     */
    private String editId;
    /**
     * 修改人名称
     */
    private String editName;
    /**
     * 修改时间
     */
    private Date editTime;


}
