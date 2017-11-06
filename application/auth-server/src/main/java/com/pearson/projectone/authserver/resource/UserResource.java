package com.pearson.projectone.authserver.resource;


import com.pearson.projectone.authserver.dto.user.ResetPasswordCommandDTO;
import com.pearson.projectone.authserver.dto.user.UserCreateCommandDTO;
import com.pearson.projectone.authserver.dto.user.UserDTO;
import com.pearson.projectone.authserver.dto.user.UserUpdateCommandDTO;
import com.pearson.projectone.authserver.service.UserService;
import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.core.support.rest.RestApi;
import com.pearson.projectone.data.entity.security.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@RestApi
@PreAuthorize("hasAuthority('ROLE_SYSTEM_ADMIN')")
public class UserResource {

	@Autowired
	private UserService userService;

	@PutMapping
	public ResponseEntity<BaseDTO> create(@RequestBody UserCreateCommandDTO createCommand) {
		AppUser createdUser = userService.createUser(createCommand);
		return new ResponseEntity<BaseDTO>(new BaseDTO(createdUser.getId(), createdUser.getVersion()),
				HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<BaseDTO> update(@RequestBody UserUpdateCommandDTO updateCommand) {
		AppUser updatedUser = userService.updateUser(updateCommand);
		return new ResponseEntity<BaseDTO>(new BaseDTO(updatedUser.getId(), updatedUser.getVersion()),
				HttpStatus.OK);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void delete(@RequestBody BaseDTO deleteCommand) {
		userService.deleteUser(deleteCommand.getId());
	}

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserDTO details(@PathVariable String id) {
		return userService.getUser(id);
	}

	@PostMapping("/resetPassword/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void resetPassword(@RequestBody ResetPasswordCommandDTO resetPasswordCommand, @PathVariable String id) {
		// TODO
	}

	/**
	 * {
	 *	  "criteria": [
	 *	    {
	 *	      "key": "firstName",
	 *	      "operation": "=",
	 *	      "value": "Merrill"
	 *	    }
	 *	  ],
	 *	  "pageRequest": {
	 *	    "page": 0,
	 *	    "size": 10,
	 *	    "orders": [
	 *	        {
	 *	          "property": "firstName",
	 *	          "direction": "DESC"
	 *	        }
	 *	     ]
	 *	  }
	 * }
	 *
	 * @param searchRequest
	 * @return
	 */
	@PostMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	public Page<UserDTO> search(@RequestBody PageableSearchRequestDTO searchRequest) {
		return userService.list(searchRequest);
	}

}
