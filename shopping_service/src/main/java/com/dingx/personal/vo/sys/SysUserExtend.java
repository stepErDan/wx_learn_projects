package com.dingx.personal.vo.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dingx.personal.entity.base.BaseEntity;
import com.dingx.personal.entity.sys.SysRole;
import com.dingx.personal.entity.sys.SysUser;
import com.dingx.personal.entity.sys.SysUserBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value="SysUserExtend对象", description="用户扩展对象")
public class SysUserExtend extends SysUserBase implements Serializable {

    private static final long serialVersionUID = -4851475886827746980L;

    @ApiModelProperty("用户")
    private SysUser sysUser;

    @ApiModelProperty("用户权限")
    private List<SysRole> roleList;
}
