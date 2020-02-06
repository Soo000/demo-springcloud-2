package com.alisls.demo.springcloud.service.user.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alisls.demo.springcloud.service.user.dto.RoleDTO;
import com.alisls.demo.springcloud.service.user.service.RoleService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {

	private final RoleService roleService;
	
	@GetMapping("/getRoleById/{id}")
	public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) {
		RoleDTO roleDTO = roleService.getRoleById(id);
		return ResponseEntity.ok(roleDTO);
	}
	
}
