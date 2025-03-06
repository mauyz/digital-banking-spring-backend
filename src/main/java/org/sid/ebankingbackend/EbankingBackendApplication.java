package org.sid.ebankingbackend;

import java.util.List;
import java.util.stream.Stream;

import org.sid.ebankingbackend.dtos.BankAccountDTO;
import org.sid.ebankingbackend.dtos.CurrentBankAccountDTO;
import org.sid.ebankingbackend.dtos.CustomerDTO;
import org.sid.ebankingbackend.dtos.SavingBankAccountDTO;
import org.sid.ebankingbackend.exceptions.CustomerNotFoundException;
import org.sid.ebankingbackend.security.service.AppUserService;
import org.sid.ebankingbackend.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EbankingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbankingBackendApplication.class, args);
	}

	//@Bean
	CommandLineRunner generateCustomers(BankAccountService bankAccountService) {
		return args -> {
			Stream.of("Customer1", "Customer2", "Customer3").forEach(name -> {
				CustomerDTO customer = new CustomerDTO();
				customer.setName(name);
				customer.setEmail(name + "@gmail.com");
				bankAccountService.saveCustomer(customer);
			});
			bankAccountService.listCustomers().forEach(customer -> {
				try {
					bankAccountService.saveCurrentBankAccount(Math.random() * 90000, 9000, customer.getId());
					bankAccountService.saveSavingBankAccount(Math.random() * 120000, 5.5, customer.getId());

				} catch (CustomerNotFoundException e) {
					e.printStackTrace();
				}
			});
			List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
			for (BankAccountDTO bankAccount : bankAccounts) {
				for (int i = 0; i < 10; i++) {
					String accountId;
					if (bankAccount instanceof SavingBankAccountDTO) {
						accountId = ((SavingBankAccountDTO) bankAccount).getId();
					} else {
						accountId = ((CurrentBankAccountDTO) bankAccount).getId();
					}
					bankAccountService.credit(accountId, 10000 + Math.random() * 120000, "Credit");
					bankAccountService.debit(accountId, 1000 + Math.random() * 9000, "Debit");
				}
			}
		};
	}

	//@Bean
	CommandLineRunner generateAppUsers(AppUserService appUserService) {
		return args -> {
			appUserService.addNewRole("USER");
			appUserService.addNewRole("ADMIN");
			appUserService.addNewUser("user1", "1234", "user1@gmail.com", "1234");
			appUserService.addNewUser("user2", "1234", "user2@gmail.com", "1234");

			appUserService.addNewUser("admin", "admin", "admin@gmail.com", "admin");

			appUserService.addRoleToUser("user1", "USER");
			appUserService.addRoleToUser("user2", "USER");

			appUserService.addRoleToUser("admin", "USER");
			appUserService.addRoleToUser("admin", "ADMIN");

		};
	}

}
