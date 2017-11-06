package com.pearson.projectone.authserver.resource;

import com.pearson.projectone.authcommons.provider.client.JpaClientDetailsService;
import com.pearson.projectone.authserver.dto.client.OauthClientCreateUpdateCommandDTO;
import com.pearson.projectone.authserver.dto.client.OauthClientDeleteCommandDTO;
import com.pearson.projectone.core.support.modelmapper.ModelMapperService;
import com.pearson.projectone.core.support.rest.RestApi;
import com.pearson.projectone.data.entity.security.oauth2.client.OauthClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RestApi
@Component
@PreAuthorize("hasAuthority('ROLE_SYSTEM_ADMIN')")
public class ClientResource {

	@Autowired
	private ClientRegistrationService clientRegistrationService;

	@Autowired
	private ClientDetailsService clientDetailsService;

	@Autowired
	private ModelMapperService modelMapperService;

	@GetMapping("{clientId}")
	@ResponseBody
	public ClientDetails details(@PathVariable("clientId") String clientId) {
		ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
		return clientDetails;
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestBody OauthClientCreateUpdateCommandDTO createCommand) {
		OauthClientDetails oauthClientDetails = modelMapperService.convertToEntity(createCommand, OauthClientDetails.class);
		clientRegistrationService.addClientDetails(JpaClientDetailsService.map(oauthClientDetails));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody OauthClientCreateUpdateCommandDTO updateCommand) {
		OauthClientDetails oauthClientDetails = modelMapperService.convertToEntity(updateCommand, OauthClientDetails.class);
		clientRegistrationService.updateClientDetails(JpaClientDetailsService.map(oauthClientDetails));
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody OauthClientDeleteCommandDTO deleteCommand) {
		clientRegistrationService.removeClientDetails(deleteCommand.getClientId());
	}

	@GetMapping("/list")
	@ResponseBody
	public List<ClientDetails> list() {
		return clientRegistrationService.listClientDetails();
	}
}
