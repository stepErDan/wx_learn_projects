package com.dingx.personal.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dingx.personal.entity.base.BaseEntity;
import com.dingx.personal.vo.wx.WxRes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("sys_user_temp")
@ApiModel(value="SysUserTemp对象", description="用户临时登陆表，存放登陆相关字段")
public class SysUserTemp implements Serializable {

    private static final long serialVersionUID = -490246246642668336L;

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("用户唯一标识")
    private String openid;

    @ApiModelProperty("会话密钥")
    private String sessionKey;

    @ApiModelProperty("用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回")
    private String unionid;

    @ApiModelProperty("token创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty("本系统token")
    private String token;

    public SysUserTemp(){
        this.createDate = LocalDateTime.now();
    }

    public SysUserTemp(WxRes res){
        this.openid = res.getOpenid();
        this.createDate = LocalDateTime.now();
        this.sessionKey = res.getSession_key();
        this.unionid = res.getUnionid();
    }
}
