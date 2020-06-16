package com.dingx.personal.vo.common;

import com.dingx.personal.common.constant.GeneralAttributes;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import static com.dingx.personal.common.constant.GeneralAttributes.RESULT_SUCCESS;

/**
 * 返回类，统一返回格式
 */
@Data
public class Result<T> {
    @ApiModelProperty("状态代码，例；200")
    private Integer code;

    @ApiModelProperty("返回信息，例：'成功'")
    private String msg;

    @ApiModelProperty("返回数据，Json格式")
    private T data;

    public Result(){
        this.code = RESULT_SUCCESS;
        this.msg = "成功";
    }

    public Result(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Result(String msg,T data){
        this.code = RESULT_SUCCESS;
        this.msg = msg;
        this.data = data;
    }

    public Result(T data){
        this.code = RESULT_SUCCESS;
        this.msg = "成功";
        this.data = data;
    }

    public static Result success(String msg){
        return new Result(RESULT_SUCCESS,msg);
    }

    public static Result error(String msg){
        return new Result(GeneralAttributes.RESULT_ERROR,msg);
    }
}
