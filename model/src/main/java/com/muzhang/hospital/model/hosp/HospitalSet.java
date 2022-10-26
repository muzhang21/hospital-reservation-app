package com.muzhang.hospital.model.hosp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.muzhang.hospital.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
@TableName("hospital_set")
public class HospitalSet extends BaseEntity {
        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "hospital_name")
        @TableField("hosname")
        private String hosname;

        @ApiModelProperty(value="hospital_code")
        @TableField("hoscode")
        private String hoscode;

        @ApiModelProperty(value="api_base_path")
        @TableField("api_url")
        private String apiUrl;

        @ApiModelProperty(value = "签名秘钥")
        @TableField("sign_key")
        private String signKey;

        @ApiModelProperty(value = "联系人姓名")
        @TableField("contacts_name")
        private String contactsName;

        @ApiModelProperty(value = "联系人手机")
        @TableField("contacts_phone")
        private String contactsPhone;

        @ApiModelProperty(value = "状态")
        @TableField("status")
        private Integer status;


}
