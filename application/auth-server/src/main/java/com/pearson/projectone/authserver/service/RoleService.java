package com.pearson.projectone.authserver.service;

import com.pearson.projectone.authserver.dto.role.AppRoleDTO;
import com.pearson.projectone.core.support.data.NameCommandDTO;
import com.pearson.projectone.core.support.modelmapper.ModelMapperService;
import com.pearson.projectone.data.dao.security.user.AppRoleDao;
import com.pearson.projectone.data.entity.security.user.AppRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

	@Autowired
	private AppRoleDao roleDao;

	@Autowired
	private ModelMapperService modelMapperService;

	public AppRole createRole(NameCommandDTO createCommand) {
		AppRole newRole = new AppRole();
		newRole.setAuthority(createCommand.getName());
		return roleDao.save(newRole);
	}

	public AppRoleDTO getRole(String authority) {
		return modelMapperService.convertToDTO(roleDao.findByAuthority(authority), AppRoleDTO.class);
	}

	public AppRoleDTO getRoleById(String roleId) {
		return modelMapperService.convertToDTO(roleDao.findOne(roleId), AppRoleDTO.class);
	}

	public List<AppRoleDTO> listAll() {
		List<AppRoleDTO> list = new ArrayList<>(0);
		roleDao.findAll().forEach(appRole -> {
			list.add(modelMapperService.convertToDTO(appRole, AppRoleDTO.class));
		});
		return list;
	}
}
