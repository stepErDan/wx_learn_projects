package com.dingx.personal.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dingx.personal.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName(value = "sys_user_base")
@ApiModel(value="SysUserBase对象", description="用户基础表，存放用户基础信息，即：适用于所有项目的用户表")
public class SysUserBase extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1165395455337870702L;

    @ApiModelProperty("密码（登录用）MD5加密")
    private String pwd;

    @ApiModelProperty("帐号（登陆用）")
    private String account;

    @ApiModelProperty("用户手机号（登录用）")
    private String phone;

    @ApiModelProperty("用户状态：1、启用；0、冻结；")
    private Integer state;

    @ApiModelProperty("是否管理员：0、否；1、是；")
    private Integer isAdmin;

    public SysUserBase(){
        this.state = 1;
        this.isAdmin = 0;
    }
}
