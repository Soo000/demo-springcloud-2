package com.alisls.demo.springcloud.service.user.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alisls.demo.springcloud.service.user.dao.RoleDAO;
import com.alisls.demo.springcloud.service.user.dto.RoleDTO;
import com.alisls.demo.springcloud.service.user.entity.RoleDO;
import com.alisls.demo.springcloud.service.user.service.RoleService;
import com.demo.springcloud.common.core.exception.BizException;

import lombok.AllArgsConstructor;

/**
 * 角色Service实现
 *
 * @author Ke Wang
 */
@Service("roleService")
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RoleDAO roleDAO;
	
	@Override
	public RoleDTO getRoleById(Long id) {
		Optional<RoleDO> roleOptional = roleDAO.findById(id);
		RoleDO roleDO = roleOptional.orElseThrow(() -> new BizException("没有查询到角色"));
		
		RoleDTO roleDTO = new RoleDTO();
		if (roleDO != null) {
			roleDTO = new RoleDTO();
			BeanUtils.copyProperties(roleDO, roleDTO);
		}
		return roleDTO;
	}

}
