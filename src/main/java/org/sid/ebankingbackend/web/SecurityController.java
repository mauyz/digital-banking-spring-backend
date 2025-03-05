package org.sid.ebankingbackend.web;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class SecurityController {

	@GetMapping("/profile")
	public Authentication getProfile(Authentication authentication) {
		return authentication;
	}

}
