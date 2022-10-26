package com.muzhang.hospital.model.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class BaseEntity implements Serializable {
    @ApiModelProperty(value="id")
    @TableId(type = IdType.AUTO)
    private long id;

    @ApiModelProperty(value = "created_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value="updated_time")
    @TableField("update_time")
    private Date updatetime;

    @ApiModelProperty(value = "logical_deletion(1: deleted, 0: notDeleted)")
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "other_reference")
    @TableField(exist = false)
    private Map<String,Object> param = new HashMap<>();


}
