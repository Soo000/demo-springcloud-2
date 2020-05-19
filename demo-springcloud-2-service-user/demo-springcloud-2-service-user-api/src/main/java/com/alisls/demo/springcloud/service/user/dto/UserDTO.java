package com.alisls.demo.springcloud.service.user.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * 用户DTO
 *
 * @author Ke Wang
 */
@ApiModel(description = "用户DTO")
@Data
@ToString
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1171647143049064655L;

    @ApiModelProperty(value = "用户标识", example = "1234567890123456789")
	private Long id;

	@ApiModelProperty(value = "用户名", required = true, example = "wangke")
	@NotBlank(message = "用户名不能为空！")
	private String username;

    @ApiModelProperty(value = "用户昵称", required = true, example = "小可")
    @NotBlank(message = "用户昵称不能为空！")
	private String nickname;

    @ApiModelProperty(value = "手机号码", example = "15091545831")
	private String mobile;

    @ApiModelProperty(value = "电子邮件", example = "xxx@163.com")
	private String email;

    @ApiModelProperty(value = "年龄", allowableValues = "@code range[1, 150]")
	private Integer age;
	
}
