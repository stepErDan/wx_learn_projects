package com.dingx.personal.vo.wx;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "微信登陆返回vo")
public class WxRes {
    @ApiModelProperty("用户唯一标识")
    private String openid;

    @ApiModelProperty("会话密钥")
    private String session_key;

    @ApiModelProperty("用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。")
    private String unionid;

    @ApiModelProperty("错误码")
    private Integer errcode;

    @ApiModelProperty("错误信息")
    private String errmsg;
}
