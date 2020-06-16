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
 * 用户菜单表，存放菜单相关字段
 * </p>
 *
 * @author dingx
 * @since 2020-05-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value="SysMenu对象", description="用户菜单表，存放菜单相关字段")
public class SysMenu extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "排序")
    private Integer order;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "图标，存放前台icon图标")
    private String icon;

    @ApiModelProperty(value = "菜单路径")
    private String url;

    @ApiModelProperty(value = "类别：1、管理端；2、客户端；3、按钮")
    private Integer type;

    @ApiModelProperty(value = "父类id")
    private Long fid;

    @ApiModelProperty(value = "层级，根级为0，依次往下")
    private Integer level;
}
