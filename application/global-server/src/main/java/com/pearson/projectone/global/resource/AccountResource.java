package com.pearson.projectone.global.resource;

import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.core.support.rest.RestApi;
import com.pearson.projectone.data.entity.global.library.account.Account;
import com.pearson.projectone.global.dto.account.AccountDTO;
import com.pearson.projectone.global.dto.account.AccountUserAssignCommandDTO;
import com.pearson.projectone.global.dto.account.CreateAccountCommandDTO;
import com.pearson.projectone.global.dto.account.UpdateAccountCommandDTO;
import com.pearson.projectone.global.service.AccountService;
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
@PreAuthorize("hasAuthority('ROLE_BU_ADMIN')")
public class AccountResource {
	@Autowired
	private AccountService accountService;

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_CLIENT')")
	public AccountDTO get(@PathVariable String id) {
		return accountService.getAccount(id);
	}

	@PostMapping("{businessUnitId}/list")
	@ResponseStatus(HttpStatus.OK)
	public Page<AccountDTO> list(@RequestBody PageableSearchRequestDTO searchRequest, @PathVariable String businessUnitId) {
		return accountService.getAccounts(businessUnitId, searchRequest);
	}

	@PutMapping("{businessUnitId}")
	public ResponseEntity<BaseDTO> create(@RequestBody CreateAccountCommandDTO createAccountCommand, @PathVariable String businessUnitId) {
		Account account = accountService.createAccount(businessUnitId, createAccountCommand);
		return new ResponseEntity<BaseDTO>(new BaseDTO(account.getId(), account.getVersion()),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BaseDTO> update(@RequestBody UpdateAccountCommandDTO updateAccountCommand) {
		Account account = accountService.updateAccount(updateAccountCommand);
		return new ResponseEntity<BaseDTO>(new BaseDTO(account.getId(), account.getVersion()),
				HttpStatus.OK);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void delete(@RequestBody BaseDTO deleteCommand) {
		accountService.deleteAccount(deleteCommand.getId());
	}

	@PostMapping("{accountId}/assignUsers")
	@ResponseStatus(HttpStatus.OK)
	public void assignUsers(@RequestBody AccountUserAssignCommandDTO assignCommand, @PathVariable String accountId) {
		accountService.assignUsers(accountId, assignCommand);
	}
}
