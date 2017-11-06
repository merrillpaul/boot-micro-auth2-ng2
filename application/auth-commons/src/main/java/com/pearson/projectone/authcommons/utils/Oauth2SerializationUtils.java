package com.pearson.projectone.authcommons.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.util.ObjectUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Utils to serialize and base64 enc-dec of OAuth2
 * access and refresh tokens. This is used internally in the token store to store the instances as
 * base64 strings in whichever database we use, either RDBMS or NoSQL
 */
public abstract class Oauth2SerializationUtils {

	private static final Log logger = LogFactory.getLog(Oauth2SerializationUtils.class);

	private Oauth2SerializationUtils() {

	}

	public static String extractTokenKey(String value) {
		if (ObjectUtils.isEmpty(value)) {
			return null;
		}
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).", e);
		}

		try {
			byte[] bytes = digest.digest(value.getBytes("UTF-8"));
			return String.format("%032x", new BigInteger(1, bytes));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).", e);
		}
	}

	public static String serializeAccessToken(OAuth2AccessToken token) {
		return Base64.getEncoder().encodeToString(SerializationUtils.serialize(token));
	}

	public static String serializeRefreshToken(OAuth2RefreshToken token) {
		return Base64.getEncoder().encodeToString(SerializationUtils.serialize(token));
	}

	public static String serializeAuthentication(OAuth2Authentication authentication) {
		return Base64.getEncoder().encodeToString(SerializationUtils.serialize(authentication));
	}

	public static OAuth2AccessToken deserializeAccessToken(String tokenObj) {
		byte[] tokenBytes = Base64.getDecoder().decode(tokenObj);
		return SerializationUtils.deserialize(tokenBytes);
	}

	public static OAuth2RefreshToken deserializeRefreshToken(String tokenObj) {
		byte[] tokenBytes = Base64.getDecoder().decode(tokenObj);
		return SerializationUtils.deserialize(tokenBytes);
	}

	public static OAuth2Authentication deserializeAuthentication(String authentication) {
		byte[] authenticationBytes = Base64.getDecoder().decode(authentication);
		return SerializationUtils.deserialize(authenticationBytes);
	}
}
