package org.sid.ebankingbackend.web;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@AllArgsConstructor
public class SecurityController {

	private AuthenticationManager authenticationManager;

	private JwtEncoder jwtEncoder;

	@GetMapping("/auth/profile")
	public Authentication getProfile() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@PostMapping("/auth/login")
	public Map<String, String> login(String username, String password) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		Instant instant = Instant.now();

		String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

		JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
				.issuedAt(instant)
				.expiresAt(instant.plus(1, ChronoUnit.HOURS))
				.subject(username)
				.claim("authorities", scope)
				.build();

		JwtEncoderParameters parameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(),
				jwtClaimsSet);

		String jwt = jwtEncoder.encode(parameters).getTokenValue();

		return Map.of("accessToken", jwt);

	}

}
