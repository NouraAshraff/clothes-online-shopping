package com.ntg.clothes_online_project.user.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {
	private static final long serialVersionUID = 2570855747735180835L;
	private final String userName;

	public CustomUser(String email, String password, boolean enabled, boolean accountNonExpired,
                      boolean credentialsNonExpired, boolean accountNonLocked, Collection<GrantedAuthority> authorities,
                      String userName) {

		super(email, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

}
