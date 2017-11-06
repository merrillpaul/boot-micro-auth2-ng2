package com.pearson.projectone.authserver.service;

import com.pearson.projectone.authserver.dto.user.UserCreateCommandDTO;
import com.pearson.projectone.authserver.dto.user.UserDTO;
import com.pearson.projectone.authserver.dto.user.UserUpdateCommandDTO;
import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.core.support.data.search.jpa.JpaSearchSpecificationBuilder;
import com.pearson.projectone.core.support.modelmapper.ModelMapperService;
import com.pearson.projectone.data.dao.security.user.AppRoleDao;
import com.pearson.projectone.data.dao.security.user.AppUserDao;
import com.pearson.projectone.data.entity.security.user.AppRole;
import com.pearson.projectone.data.entity.security.user.AppUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

	@Autowired
	private AppUserDao userDao;

	@Autowired
	private AppRoleDao roleDao;

	@Autowired
	private ModelMapperService modelMapperService;

	public AppUser createUser(UserCreateCommandDTO createCommand) {
		AppUser appUser = modelMapperService.convertToEntity(createCommand, AppUser.class);
		List<AppRole> roles = new ArrayList<>(0);
		createCommand.getRoles().forEach(role -> {
			roles.add(roleDao.findByAuthority(role));
		});
		appUser.setRoles(roles);
		return userDao.save(appUser);
	}

	public AppUser updateUser(UserUpdateCommandDTO updateCommand) {
		AppUser appUser = modelMapperService.convertToEntity(updateCommand, AppUser.class);
		AppUser retrievedUser = userDao.findOne(updateCommand.getId());
		List<AppRole> roles = new ArrayList<>(0);
		updateCommand.getRoles().forEach(role -> {
			roles.add(roleDao.findByAuthority(role));
		});
		BeanUtils.copyProperties(appUser, retrievedUser);
		retrievedUser.setRoles(roles);
		return userDao.save(retrievedUser);
	}

	public void deleteUser(String userId) {
		// TODO
	}

	public UserDTO getUser(String userId) {
		AppUser user = userDao.findOne(userId);
		UserDTO result = modelMapperService.convertToDTO(user, UserDTO.class);
		return result;
	}

	/**
	 * Returns a page with search criteria
	 *
	 * @param searchRequest
	 * @return
	 */
	public Page<UserDTO> list(PageableSearchRequestDTO searchRequest) {
		Specification<AppUser> specification = new JpaSearchSpecificationBuilder<AppUser>()
				.with(searchRequest.getCriteria()).build();
		List<UserDTO> list = new ArrayList<>(0);
		Converter<AppUser, UserDTO> converter = new Converter<AppUser, UserDTO>() {
			@Override
			public UserDTO convert(AppUser source) {
				return modelMapperService.convertToDTO(source, UserDTO.class);
			}
		};
		Page<UserDTO> page = (ObjectUtils.isEmpty(specification) ?
				userDao.findAll(searchRequest.toPageable()) :
				userDao.findAll(specification, searchRequest.toPageable())).map(converter);
		return page;
	}
}
