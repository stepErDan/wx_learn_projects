package com.dingx.personal.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;

import com.dingx.personal.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户业务表，存放业务相关字段
 * </p>
 *
 * @author dingx
 * @since 2020-05-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value="SysUser对象", description="用户业务表，存放业务相关字段")
public class SysUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关联sys_user_base.id")
    private Long uid;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("头像")
    private String avatarUrl;

    @ApiModelProperty("性别（？）：1、男，2、女")
    private Integer gender;

    @ApiModelProperty("国家")
    private String country;

    @ApiModelProperty("省份")
    private String province;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("用户唯一标识")
    private String openid;
}
