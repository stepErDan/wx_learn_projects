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
 * 单位基础表，存放单位基础信息，即：适用于所有项目的单位表（不适用本项目）
 * </p>
 *
 * @author dingx
 * @since 2020-05-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_org_base")
@ApiModel(value="SysOrgBase对象", description="单位基础表，存放单位基础信息，即：适用于所有项目的单位表（不适用本项目）")
public class SysOrgBase extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位编码")
    private String code;

}
