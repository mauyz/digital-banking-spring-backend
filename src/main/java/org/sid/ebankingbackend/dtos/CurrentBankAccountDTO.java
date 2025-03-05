package org.sid.ebankingbackend.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.sid.ebankingbackend.enums.AccountStatus;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
public class CurrentBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double overDraft;
}
