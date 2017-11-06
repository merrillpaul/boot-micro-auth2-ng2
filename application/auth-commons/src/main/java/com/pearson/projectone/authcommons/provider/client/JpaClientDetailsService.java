package com.pearson.projectone.authcommons.provider.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pearson.projectone.core.utils.CheckedException;
import com.pearson.projectone.data.dao.security.oauth2.client.Oauth2ClientDao;
import com.pearson.projectone.data.entity.security.oauth2.client.OauthClientDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A Jpa version for managing and providing oauth2 clients inspired from
 *
 * @see org.springframework.security.oauth2.provider.client.JdbcClientDetailsService
 */
public class JpaClientDetailsService implements ClientDetailsService, ClientRegistrationService {

	@Autowired
	Oauth2ClientDao clientDao;

	@Autowired
	PasswordEncoder passwordEncoder;

	private static final Log logger = LogFactory.getLog(JpaClientDetailsService.class);

	@Override
	public ClientDetails loadClientByClientId(String clientId) {
		OauthClientDetails daoClient = clientDao.findByClientId(clientId);
		if (ObjectUtils.isEmpty(daoClient)) {
			throw new NoSuchClientException("No client with requested id: " + clientId);
		}

		return map(daoClient);
	}

	@Override
	public void addClientDetails(ClientDetails clientDetails) {
		OauthClientDetails daoClientDetails = new OauthClientDetails();
		OauthClientDetails daoClient = clientDao.findByClientId(clientDetails.getClientId());
		if (daoClient != null) {
			throw new ClientAlreadyExistsException("Client already exists: " + clientDetails.getClientId());
		}

		daoClientDetails = map(clientDetails, daoClientDetails);
		clientDao.save(daoClientDetails);
	}

	@Override
	public void updateClientDetails(ClientDetails clientDetails) {
		OauthClientDetails daoClient = clientDao.findByClientId(clientDetails.getClientId());
		if (ObjectUtils.isEmpty(daoClient)) {
			throw new NoSuchClientException("No client found with id = " + clientDetails.getClientId());
		}

		daoClient = map(clientDetails, daoClient);
		clientDao.save(daoClient);
	}


	@Override
	public void updateClientSecret(String clientId, String secret) {
		OauthClientDetails daoClient = clientDao.findByClientId(clientId);
		if (ObjectUtils.isEmpty(daoClient)) {
			throw new NoSuchClientException("No client found with id = " + clientId);
		}
		daoClient.setClientSecret(passwordEncoder.encode(secret));
		clientDao.save(daoClient);
	}

	@Override
	public void removeClientDetails(String clientId) {
		OauthClientDetails daoClient = clientDao.findByClientId(clientId);
		if (ObjectUtils.isEmpty(daoClient)) {
			throw new NoSuchClientException("No client found with id = " + clientId);
		}
		clientDao.delete(daoClient);
	}

	@Override
	public List<ClientDetails> listClientDetails() {
		List<ClientDetails> list = new ArrayList<>(0);
		clientDao.findAll().forEach(clientDetails -> {
			list.add(map(clientDetails));
		});
		return list;
	}

	public static ClientDetails map(OauthClientDetails daoClient) {

		BaseClientDetails clientDetails = new BaseClientDetails(daoClient.getClientId(),
				daoClient.getResourceIds(), daoClient.getScope(), daoClient.getAuthorizedGrantTypes(), daoClient.getAuthorities(),
				daoClient.getWebServerRedirectUri());
		clientDetails.setClientSecret(daoClient.getClientSecret());
		if (daoClient.getAccessTokenValidity() != null) {
			clientDetails.setAccessTokenValiditySeconds(daoClient.getAccessTokenValidity());
		}
		if (daoClient.getRefreshTokenValidity() != null) {
			clientDetails.setRefreshTokenValiditySeconds(daoClient.getRefreshTokenValidity());
		}
		String additionalInformationJson = daoClient.getAdditionalInformation();
		if (additionalInformationJson != null) {
			try {
				JsonMapper mapper = createJsonMapper();
				@SuppressWarnings("unchecked")
				Map<String, Object> additionalInformation = mapper.read(additionalInformationJson, Map.class);
				clientDetails.setAdditionalInformation(additionalInformation);
			} catch (CheckedException e) {
				logger.warn("Could not decode JSON for additional information: " + clientDetails, e);
			}
		}
		String scopes = daoClient.getAutoApprove();
		if (scopes != null) {
			clientDetails.setAutoApproveScopes(StringUtils.commaDelimitedListToSet(scopes));
		}

		return clientDetails;
	}

	private OauthClientDetails map(ClientDetails clientDetails, OauthClientDetails daoClient) {

		daoClient.setClientId(clientDetails.getClientId());
		if (clientDetails.getClientSecret() != null) {
			daoClient.setClientSecret(passwordEncoder.encode(clientDetails.getClientSecret()));
		}
		daoClient.setAccessTokenValidity(clientDetails.getAccessTokenValiditySeconds());
		daoClient.setRefreshTokenValidity(clientDetails.getRefreshTokenValiditySeconds());

		String resourceIds = clientDetails.getResourceIds() != null ?
				StringUtils.collectionToCommaDelimitedString(clientDetails.getResourceIds()) : null;

		String scope = clientDetails.getScope() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails
				.getScope()) : null;

		String grantTypes = clientDetails.getAuthorizedGrantTypes() != null ? StringUtils
				.collectionToCommaDelimitedString(clientDetails.getAuthorizedGrantTypes()) : null;

		String redirectUri = clientDetails.getRegisteredRedirectUri() != null ? StringUtils
				.collectionToCommaDelimitedString(clientDetails.getRegisteredRedirectUri()) : null;

		String authorities = clientDetails.getAuthorities() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails
				.getAuthorities()) : null;

		daoClient.setResourceIds(resourceIds);
		daoClient.setScope(scope);
		daoClient.setAuthorizedGrantTypes(grantTypes);
		daoClient.setWebServerRedirectUri(redirectUri);
		daoClient.setAuthorities(authorities);
		daoClient.setAutoApprove(getAutoApproveScopes(clientDetails));
		String json = null;
		try {
			json = createJsonMapper().write(clientDetails.getAdditionalInformation());
			daoClient.setAdditionalInformation(json);
		} catch (CheckedException e) {
			logger.warn("Could not serialize additional information: " + clientDetails, e);
		}

		return daoClient;
	}

	private static String getAutoApproveScopes(ClientDetails clientDetails) {
		if (clientDetails.isAutoApprove("true")) {
			return "true"; // all scopes autoapproved
		}
		Set<String> scopes = new HashSet<String>();
		for (String scope : clientDetails.getScope()) {
			if (clientDetails.isAutoApprove(scope)) {
				scopes.add(scope);
			}
		}
		return StringUtils.collectionToCommaDelimitedString(scopes);
	}


	private static JsonMapper createJsonMapper() {
		if (ClassUtils.isPresent("org.codehaus.jackson.map.ObjectMapper", null)) {
			return new JacksonMapper();
		} else if (ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", null)) {
			return new Jackson2Mapper();
		}
		return new NotSupportedJsonMapper();
	}

	interface JsonMapper {
		String write(Object input) throws CheckedException;

		<T> T read(String input, Class<T> type) throws CheckedException;
	}

	private static class JacksonMapper implements JsonMapper {
		private org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();

		@Override
		public String write(Object input) throws CheckedException {
			try {
				return mapper.writeValueAsString(input);
			} catch (IOException e) {
				throw new CheckedException(e.getMessage(), e);
			}
		}

		@Override
		public <T> T read(String input, Class<T> type) throws CheckedException {
			try {
				return mapper.readValue(input, type);
			} catch (IOException e) {
				throw new CheckedException(e.getMessage(), e);
			}
		}
	}

	private static class Jackson2Mapper implements JsonMapper {
		private com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();

		@Override
		public String write(Object input) throws CheckedException {
			try {
				return mapper.writeValueAsString(input);
			} catch (JsonProcessingException e) {
				throw new CheckedException(e.getMessage(), e);
			}
		}

		@Override
		public <T> T read(String input, Class<T> type) throws CheckedException {
			try {
				return mapper.readValue(input, type);
			} catch (IOException e) {
				throw new CheckedException(e.getMessage(), e);
			}
		}
	}

	private static class NotSupportedJsonMapper implements JsonMapper {
		@Override
		public String write(Object input) throws CheckedException {
			throw new UnsupportedOperationException(
					"Neither Jackson 1 nor 2 is available so JSON conversion cannot be done");
		}

		@Override
		public <T> T read(String input, Class<T> type) throws CheckedException {
			throw new UnsupportedOperationException(
					"Neither Jackson 1 nor 2 is available so JSON conversion cannot be done");
		}
	}


}
