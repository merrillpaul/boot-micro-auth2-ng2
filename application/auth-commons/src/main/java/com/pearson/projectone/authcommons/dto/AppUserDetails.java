package com.pearson.projectone.authcommons.dto;


import com.pearson.projectone.data.entity.security.user.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * DTO which is used in spring authentication's user detail service and
 * shows up as the principal within authenticated requests
 */
public class AppUserDetails extends User {

	private String firstName;

	private String lastName;

	private String honorific;

	private String timezoneId;

	private String id;

	private Boolean passwordExpired;

	private Map<String, Serializable> extraData = new HashMap<>(0);

	private List<String> roles;

	public AppUserDetails(String id, String username, String password, String firstName, String lastName, String honorific,
	                      String timezoneId, Map<String, Serializable> extraData, Collection<? extends GrantedAuthority> authorities,
	                      boolean enabled, boolean accountExpired, boolean accountLocked) {
		super(username, password, enabled, !accountExpired, true, !accountLocked, authorities == null ? new ArrayList<GrantedAuthority>() : authorities);
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.honorific = honorific;
		this.timezoneId = timezoneId;
		this.extraData = extraData == null ? new HashMap<>(0) : extraData;
	}

	public AppUserDetails(String id, String username, String firstName, String lastName, String honorific,
	                      String timezoneId, Map<String, Serializable> extraData,
	                      Collection<? extends GrantedAuthority> authorities) {
		this(id, username, "N/A", firstName, lastName, honorific, timezoneId, extraData, authorities, true, false, false);
	}

	public AppUserDetails(AppUser user, Collection<? extends GrantedAuthority> authorities, boolean passWordExpired) {
		this(
				user.getId(),
				user.getUsername(),
				user.getPassword(),
				user.getFirstName(),
				user.getLastName(),
				user.getHonorific(),
				user.getTimezoneId(),
				null, // TODO add extra information from user account
				authorities,
				user.isEnabled(),
				user.isAccountExpired(),
				user.isAccountLocked()
		);
		this.passwordExpired = passWordExpired;
	}

	private AppUserDetails(AppUserDetails user) {
		this(
				user.getId(),
				user.getUsername(),
				"", //clearing passwords
				user.getFirstName(),
				user.getLastName(),
				user.getHonorific(),
				user.getTimezoneId(),
				user.extraData,
				null, // clearing extra information
				user.isEnabled(),
				user.isAccountNonExpired(),
				user.isAccountNonLocked()
		);

		this.roles = new ArrayList<>(0);
		if (user.getAuthorities() != null) {
			this.roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
		}

	}

	@Override
	public String toString() {
		return "AppUserDetails{" +
				" super = {" +
				super.toString() + "}" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", honorific='" + honorific + '\'' +
				", timezoneId='" + timezoneId + '\'' +
				", id='" + id + '\'' +
				", passwordExpired=" + passwordExpired +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}

		AppUserDetails that = (AppUserDetails) o;

		return id != null ? id.equals(that.id) : that.id == null;

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (id != null ? id.hashCode() : 0);
		return result;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHonorific() {
		return honorific;
	}

	public void setHonorific(String honorific) {
		this.honorific = honorific;
	}

	public String getTimezoneId() {
		return timezoneId;
	}

	public void setTimezoneId(String timezoneId) {
		this.timezoneId = timezoneId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getPasswordExpired() {
		return passwordExpired;
	}

	public void setPasswordExpired(Boolean passwordExpired) {
		this.passwordExpired = passwordExpired;
	}

	public Serializable getExtraData(String key) {
		return this.extraData.get(key);
	}

	public void setExtraData(String key, Serializable value) {
		this.extraData.put(key, value);
	}

	public List<String> getRoles() {
		return roles;
	}

	public static final AppUserDetails clone(AppUserDetails original) {
		return new AppUserDetails(original);
	}
}
