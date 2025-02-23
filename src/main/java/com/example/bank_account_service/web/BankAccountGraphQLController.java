package com.example.bank_account_service.web;

import com.example.bank_account_service.dto.BankAccountRequestDTO;
import com.example.bank_account_service.dto.BankAccountResponseDTO;
import com.example.bank_account_service.entities.BankAccount;
import com.example.bank_account_service.entities.Customer;
import com.example.bank_account_service.repositories.BankAccountRepository;
import com.example.bank_account_service.repositories.CustomerRepository;
import com.example.bank_account_service.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

// GRAPHQL
@Controller
public class BankAccountGraphQLController {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;

    //Liste des comptes
    @QueryMapping
    public List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();
    }

    // Afficher un compte par Id
    @QueryMapping
    public BankAccount accountById(@Argument String id){
        return  bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found", id)));
    }

    //Ajouter un compte
    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount){
        return accountService.addAccount(bankAccount);
    }

    //Modifier un compte
    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id,@Argument BankAccountRequestDTO bankAccount){
        return accountService.updateAccount(id, bankAccount);
    }

    //Supprimer un compte
    @MutationMapping
    public Boolean deleteAccount(@Argument String id){
        bankAccountRepository.deleteById(id);
        return true;
    }

    //Liste des clients(table customer)
    @QueryMapping
    public List<Customer> customers(){
        return customerRepository.findAll();
    }
}


//record BankAccountDTO(Double balance, String type, String currency){}