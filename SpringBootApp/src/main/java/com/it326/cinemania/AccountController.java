package com.it326.cinemania;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class AccountController {
    
    private final AccountRepository repository;

    AccountController(AccountRepository repository){
        this.repository = repository;
    }
    
    // @GetMapping("/accounts")
    // List<Account> listAllAccounts(){
    //     return repository.findAll();
    // }
    
    // The method below returns a collection of Account resources. They are a little more than just Accounts, because they also contain links to where they and
    // other resources can be found.
    // It is also ran when the user goes to (websiteUrl)/accounts with a GET request.
    @GetMapping("/accounts")
    CollectionModel<EntityModel<Account>> listAllAccounts() {
        List<EntityModel<Account>> accounts = repository.findAll().stream()
            .map(account -> EntityModel.of(account,
                linkTo(methodOn(AccountController.class).displayAccountByID(account.getAccountID())).withSelfRel(),
                linkTo(methodOn(AccountController.class).listAllAccounts()).withRel("accounts")))
            .collect(Collectors.toList());

        return CollectionModel.of(accounts, linkTo(methodOn(AccountController.class).listAllAccounts()).withSelfRel());
    }

    @PostMapping("/accounts")
    Account storeAccount(@RequestBody Account newAccount){
        return repository.save(newAccount);
    }

    // @GetMapping("/accounts/{id}")
    // Account displayAccountByID(@PathVariable long id){        
    //     return repository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    // }

    // The method below returns an individual Account resource.
    @GetMapping("/accounts/{id}")
    EntityModel<Account> displayAccountByID(@PathVariable long id){        
        
        Account account = repository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));

        return EntityModel.of(account,
            linkTo(methodOn(AccountController.class).displayAccountByID(id)).withSelfRel(),
            linkTo(methodOn(AccountController.class).listAllAccounts()).withRel("accounts"));
    }

    @PutMapping("/employees/{id}")
    Account replaceAccount(@RequestBody Account newAccount, @PathVariable Long id) {
    return repository.findById(id)
        .map(account -> {
        account.setFName(newAccount.getFName()); 
        account.setLName(newAccount.getLName());
        account.setEmail(newAccount.getEmail());
        return repository.save(account);
        })
        .orElseThrow(() -> new AccountNotFoundException(id));
    }

    @DeleteMapping("/accounts/{id}")
    void deleteAccount(@PathVariable long id){
        repository.deleteById(id);
    }
}
