package org.sid.ebankingbackend.security.entities;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class AppUser {
	@Id
	private String userId;	
	@Column(unique = true)
	private String username;
	@Column(unique = true)
	private String email;
	private String password;
	@ManyToAny(fetch = FetchType.LAZY)
	private List<AppRole> roles;
}
