package org.sid.ebankingbackend.security;

import java.util.List;
import java.util.stream.Collectors;

import org.sid.ebankingbackend.security.entities.AppUser;
import org.sid.ebankingbackend.security.service.AppUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private AppUserService appUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AppUser appUser = appUserService.loadUserByUsername(username);

		if (appUser == null) {
			throw new UsernameNotFoundException(String.format("User %s not found", username));
		}

		List<GrantedAuthority> roles = appUser.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());

		return User.builder().username(appUser.getUsername()).password(appUser.getPassword()).authorities(roles)
				.build();
	}

}
