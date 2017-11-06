package com.pearson.projectone.global.service;

import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.data.dao.global.library.account.AccountDao;
import com.pearson.projectone.data.entity.global.library.account.Account;
import com.pearson.projectone.global.dto.account.AccountDTO;
import com.pearson.projectone.global.dto.account.AccountUserAssignCommandDTO;
import com.pearson.projectone.global.dto.account.CreateAccountCommandDTO;
import com.pearson.projectone.global.dto.account.UpdateAccountCommandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	@Autowired
	private AccountDao accountDao;

	public AccountDTO getAccount(String id) {
		// TODO impl
		return null;
	}

	public Page<AccountDTO> getAccounts(String businessUnitId, PageableSearchRequestDTO searchRequest) {
		// TODO impl
		return null;
	}

	public Account createAccount(String businessUnitId, CreateAccountCommandDTO createAccountCommand) {
		// TODO impl
		return null;
	}

	public Account updateAccount(UpdateAccountCommandDTO updateAccountCommand) {
		// TODO impl
		return null;
	}

	public void deleteAccount(String accountId) {
		// TODO impl
	}

	public void assignUsers(String accountId, AccountUserAssignCommandDTO assignCommand) {
		// TODO impl
	}

}
