package com.pearson.projectone.data.entity.security.user;


import com.pearson.projectone.core.support.data.RelationalEntity;
import com.pearson.projectone.core.support.data.interceptor.trackchanges.TrackChanges;
import com.pearson.projectone.core.utils.AutoWireHelper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Resolves to app_user table
 */
@Entity
@TrackChanges
public class AppUser extends RelationalEntity {
	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	@TrackChanges
	private String password;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String honorific;

	private String timezoneId;

	private boolean enabled;

	private boolean accountExpired;

	private boolean accountLocked;

	private Timestamp lastUnlocked;

	private Integer failedLogins;

	private boolean confirmed;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable
			(
					name = "appUserRoles",
					joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"),
					inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id")
			)
	private List<AppRole> roles;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<PasswordHistory> passwordHistories;

	@Transient
	public boolean shouldPreventLogin() {
		return accountExpired || accountLocked || !enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public void setTimezoneId(String timeZoneId) {
		this.timezoneId = timeZoneId;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public Timestamp getLastUnlocked() {
		return lastUnlocked;
	}

	public void setLastUnlocked(Timestamp lastUnlocked) {
		this.lastUnlocked = lastUnlocked;
	}

	public Integer getFailedLogins() {
		return failedLogins;
	}

	public void setFailedLogins(Integer failedLogins) {
		this.failedLogins = failedLogins;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public List<AppRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AppRole> roles) {
		this.roles = roles;
	}

	public Set<PasswordHistory> getPasswordHistories() {
		return passwordHistories;
	}

	public void setPasswordHistories(Set<PasswordHistory> passwordHistories) {
		this.passwordHistories = passwordHistories;
	}

	@Transient
	public Set<PasswordHistory> addPasswordHistory(String password) {
		this.passwordHistories = ObjectUtils.defaultIfNull(this.passwordHistories, new HashSet<PasswordHistory>(1));
		PasswordHistory history = new PasswordHistory();
		history.setPassword(password);
		history.setUser(this);
		this.passwordHistories.add(history);
		return this.passwordHistories;
	}

	@Override
	public void onBeforeInsert() {
		addPasswordHistory();
	}

	@Override
	public void onBeforeFlushForUpdate() {
		if (isDirty("password")) {
			addPasswordHistory();
		}
	}


	private void addPasswordHistory() {
		PasswordEncoder passwordEncoder = AutoWireHelper.getBean(PasswordEncoder.class);
		if (this.getPassword() != null) {
			String encodedPassword = passwordEncoder.encode(this.getPassword());
			this.setPassword(encodedPassword);
			this.addPasswordHistory(encodedPassword);
		}
	}
}
