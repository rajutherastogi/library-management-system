package com.raju.libraryManagementSystem.entity;

import com.raju.libraryManagementSystem.enums.AccountStatus;
import com.raju.libraryManagementSystem.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String phone;
    @Valid
    private Address address;
    @NotNull
    private AccountStatus status;
    @NotNull
    private Date membershipDate;
    @NotNull
    private AccountType accountType;
}
