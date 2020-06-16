package com.dingx.personal.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.dingx.personal.common.util.SnowflakeIdWorker;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty(value = "1、删除；0、未删除；")
    @TableLogic
    private Integer removed;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    public BaseEntity(){
        this.id = SnowflakeIdWorker.generateId();
        this.removed = RemoveFlag.NOTDEL.getCode();
        this.createDate = LocalDateTime.now();
    }

    public enum RemoveFlag{
        //未删除
        NOTDEL(0),
        //已删除
        DEL(1);

        private int code;

        RemoveFlag(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
