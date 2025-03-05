package org.sid.ebankingbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.formLogin(customizer -> customizer.defaultSuccessUrl("/profile").permitAll());
		httpSecurity.authorizeHttpRequests(customizer -> customizer.anyRequest().authenticated());
		httpSecurity.userDetailsService(userDetailsServiceImpl);

		return httpSecurity.build();
	}

}
