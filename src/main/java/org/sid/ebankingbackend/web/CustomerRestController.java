package org.sid.ebankingbackend.web;

import lombok.AllArgsConstructor;
import org.sid.ebankingbackend.dtos.CustomerDTO;
import org.sid.ebankingbackend.dtos.CustomerWithUserDto;
import org.sid.ebankingbackend.exceptions.CustomerNotFoundException;
import org.sid.ebankingbackend.services.BankAccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CustomerRestController {
	private BankAccountService bankAccountService;

	@GetMapping("/customers")
	public List<CustomerDTO> customers() {
		return bankAccountService.listCustomers();
	}

	@GetMapping("/customers/search")
	public List<CustomerDTO> searchCustomers(@RequestParam(defaultValue = "") String keyword) {
		return bankAccountService.searchCustomers("%" + keyword + "%");
	}

	@GetMapping("/customers/{id}")
	public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
		return bankAccountService.getCustomer(customerId);
	}
	
	@GetMapping("/customers/user/{username}")	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")		
	public CustomerDTO getCustomer(@PathVariable String username){
		return bankAccountService.getCustomerByUsername(username);
	}

	@PostMapping("/customers")
	public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
		return bankAccountService.saveCustomer(customerDTO);
	}
	
	@PostMapping("/customers/user")
	public CustomerDTO saveCustomerWith(@RequestBody CustomerWithUserDto customerDTO) {
		return bankAccountService.saveCustomerWithUser	(customerDTO);
	}

	@PutMapping("/customers/{customerId}")
	public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
		customerDTO.setId(customerId);
		return bankAccountService.updateCustomer(customerDTO);
	}

	@DeleteMapping("/customers/{id}")
	public void deleteCustomer(@PathVariable Long id) throws CustomerNotFoundException {
		bankAccountService.deleteCustomer(id);
	}
}
