package com.alisls.demo.springcloud.service.user.web;

import com.springcloud.common.model.dto.DataResult;
import com.springcloud.common.model.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alisls.demo.springcloud.service.user.dto.RoleDTO;
import com.alisls.demo.springcloud.service.user.service.RoleService;

import lombok.AllArgsConstructor;

/**
 * 用户服务
 *
 * @author Ke Wang
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {

	private final RoleService roleService;

	@ApiOperation(value = "角色查询", notes = "根据角色标识查询角色")
    @ApiImplicitParam(
            name = "id",
            required = true,
            paramType = "path",
            dataType = "Long",
            example = "1234567890123456789"
    )
	@GetMapping("/getRoleById/{id}")
	public Result getRoleById(@PathVariable Long id) {
		RoleDTO roleDTO = roleService.getRoleById(id);
		return DataResult.ofSuccess(roleDTO);
	}
	
}
