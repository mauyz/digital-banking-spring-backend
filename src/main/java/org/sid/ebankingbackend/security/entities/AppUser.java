package org.sid.ebankingbackend.security.entities;

	
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;
import org.sid.ebankingbackend.entities.Customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
	@Builder.Default
	private List<AppRole> roles = new ArrayList<AppRole>();	
	@OneToOne(mappedBy = "user")
    private Customer customer;
}
