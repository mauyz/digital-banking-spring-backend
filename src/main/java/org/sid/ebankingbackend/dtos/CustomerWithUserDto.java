package org.sid.ebankingbackend.dtos;


import lombok.Data;

@Data	
public class CustomerWithUserDto {
    private String name;
    private String email;	
    private String username;
    private String password	;
}
