package org.sid.ebankingbackend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue("SA")
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SavingAccount extends BankAccount {
    private double interestRate;
}
