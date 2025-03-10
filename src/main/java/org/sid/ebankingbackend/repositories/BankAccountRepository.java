package org.sid.ebankingbackend.repositories;

import java.util.List;

import org.sid.ebankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
	List<BankAccount> findByCustomerId(Long customerId);	
}
		