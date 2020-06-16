package com.dingx.personal.vo.wx;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "微信登陆请求vo")
public class WxReq {
    @ApiModelProperty("小程序 appId")
    private String appid;

    @ApiModelProperty("小程序 appSecret")
    private String secret;

    @ApiModelProperty("登录时获取的 code")
    private String js_code;

    @ApiModelProperty("授权类型，此处只需填写 authorization_code")
    private String grant_type = "authorization_code";
}
