package com.dingx.personal.vo.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回类，统一返回格式
 */
@Data
public class CommonStr {
    @ApiModelProperty("接收字符串")
    private String str;
}
