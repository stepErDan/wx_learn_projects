package com.dingx.personal.vo.sys;

import com.dingx.personal.entity.sys.SysMenu;
import com.dingx.personal.entity.sys.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户角色扩展对象
 * </p>
 *
 * @author dingx
 * @since 2020-05-14
 */
@Data
@ApiModel(value="SysRoleExtend对象", description="用户扩展对象")
public class SysRoleExtend extends SysRole implements Serializable {

    private static final long serialVersionUID = 7889012952777886565L;

    @ApiModelProperty("权限关联菜单")
    private List<SysMenu> menuList;
}
