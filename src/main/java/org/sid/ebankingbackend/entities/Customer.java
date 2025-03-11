package org.sid.ebankingbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import org.sid.ebankingbackend.security.entities.AppUser;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "customer")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<BankAccount> bankAccounts;
    @OneToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private AppUser user;
}
