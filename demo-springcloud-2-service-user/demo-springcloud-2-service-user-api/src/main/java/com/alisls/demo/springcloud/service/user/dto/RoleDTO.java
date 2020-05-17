package com.alisls.demo.springcloud.service.user.dto;

import java.io.Serializable;

import com.springcloud.common.model.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色DTO
 *
 * @author Ke Wang
 */
@ApiModel(description = "角色DTO")
@Getter
@Setter
@ToString
public class RoleDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -1541136050994621833L;

    @ApiModelProperty(value = "角色标识", example = "1234567890123456789")
	private Long id;

    @ApiModelProperty(value = "角色编码", required = true, example = "role_admin")
    private String roleCode;

    @ApiModelProperty(value = "角色名称", required = true, example = "管理员")
	private String roleName;

}
