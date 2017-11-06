package com.pearson.projectone.authserver.resource;

import com.pearson.projectone.authserver.dto.role.AppRoleDTO;
import com.pearson.projectone.authserver.service.RoleService;
import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.core.support.data.NameCommandDTO;
import com.pearson.projectone.core.support.rest.RestApi;
import com.pearson.projectone.data.entity.security.user.AppRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Component
@RestApi
@PreAuthorize("hasAuthority('ROLE_SYSTEM_ADMIN')")
public class RoleResource {

	@Autowired
	private RoleService roleService;

	@PutMapping
	public ResponseEntity<BaseDTO> create(@RequestBody NameCommandDTO createCommand) {
		AppRole appRole = roleService.createRole(createCommand);
		return new ResponseEntity<BaseDTO>(new BaseDTO(appRole.getId(), appRole.getVersion()),
				HttpStatus.OK);
	}

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public AppRoleDTO details(@PathVariable String id) {
		return roleService.getRoleById(id);
	}

	@GetMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	public List<AppRoleDTO> list() {
		return roleService.listAll();
	}

}
