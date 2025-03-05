package org.sid.ebankingbackend.security.service;

import org.sid.ebankingbackend.security.entities.AppRole;
import org.sid.ebankingbackend.security.entities.AppUser;

public interface AppUserService {

	AppUser addNewUser(String username, String password, String email, String confirmPassword);

	AppRole addNewRole(String role);

	void addRoleToUser(String username, String role);

	void removeFromRoleToUser(String username, String role);

	AppUser loadUserByUsername(String username);
}
