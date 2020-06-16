package com.dingx.personal.vo.wx;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "微信验证返回vo")
public class WxToken {
    @ApiModelProperty("昵称")
    private String openid;

    @ApiModelProperty("头像")
    private String session_key;

    @ApiModelProperty("性别（？）：1、男，2、女")
    private Integer unionid;

    @ApiModelProperty("国家")
    private String country;

    @ApiModelProperty("省份")
    private String province;

    @ApiModelProperty("城市")
    private String city;
}
