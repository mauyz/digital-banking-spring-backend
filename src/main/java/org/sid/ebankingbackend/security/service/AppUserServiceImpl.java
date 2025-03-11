package org.sid.ebankingbackend.security.service;

import java.util.UUID;

import org.sid.ebankingbackend.security.entities.AppRole;
import org.sid.ebankingbackend.security.entities.AppUser;
import org.sid.ebankingbackend.security.repo.AppRoleRepository;
import org.sid.ebankingbackend.security.repo.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

	private AppUserRepository appUserRepository;

	private AppRoleRepository appRoleRepository;

	private PasswordEncoder passwordEncoder;

	@Override
	public AppUser addNewUser(String username, String password, String confirmPassword) {
		AppUser user = appUserRepository.findByUsername(username);
		if (user != null) {
			throw new RuntimeException("User already exists");
		}
		if (password != confirmPassword) {
			throw new RuntimeException("Password not match");
		}
		user = AppUser.builder().userId(UUID.randomUUID().toString()).username(username)
				.password(passwordEncoder.encode(password)).build();
		user = appUserRepository.save(user);
		return user;
	}

	@Override
	public AppRole addNewRole(String role) {
		AppRole appRole = appRoleRepository.findById(role).orElse(null);
		if (appRole == null) {
			appRole = AppRole.builder().role(role).build();
			appRole = appRoleRepository.save(appRole);
		}
		return appRole;
	}

	@Override
	public void addRoleToUser(String username, String role) {
		AppUser user = appUserRepository.findByUsername(username);
		if (user == null) {
			throw new RuntimeException("User does not exist");
		}
		AppRole appRole = appRoleRepository.findById(role).orElse(null);
		if (appRole == null) {
			appRole = addNewRole(role);
		}
		user.getRoles().add(appRole);

	}

	@Override
	public void removeFromRoleToUser(String username, String role) {
		AppUser user = appUserRepository.findByUsername(username);
		if (user == null) {
			throw new RuntimeException("User does not exist");
		}
		AppRole appRole = appRoleRepository.findById(role).orElse(null);

		user.getRoles().remove(appRole);
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		return appUserRepository.findByUsername(username);
	}

}
