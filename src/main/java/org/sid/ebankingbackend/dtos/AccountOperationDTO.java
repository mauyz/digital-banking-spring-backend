package org.sid.ebankingbackend.dtos;

import java.util.Date;

import org.sid.ebankingbackend.enums.OperationType;

import lombok.Data;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;
}

