package com.example.bank_account_service.entities;

import com.example.bank_account_service.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    @Id
    private String id;
    private Date createdAt;
    private Double balance; //valeur par defaut est nulle
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @ManyToOne
    private Customer customer;

}
