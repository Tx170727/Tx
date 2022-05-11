package com.tx.entity.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegisterParemt {
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value="验证码")
    private String code;
}
