package com.it326.cinemania;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Account {
    private @Id @GeneratedValue long accountID;
    private String fName;
    private String lName;
    private String email;

    Account(){}

    Account(String email){
        this.email = email;
    }

    Account(String fName, String lName, String email){
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Account))
            return false;
        Account account = (Account) o;
        return Objects.equals(this.accountID, account.accountID) && Objects.equals(this.fName, account.fName) 
        && Objects.equals(this.lName, account.lName) && Objects.equals(this.email, account.email);
  }

    @Override
    public String toString(){
        return "Account{" + "accountID=" + this.accountID + ", fName='" + this.fName + "', lName ='" + this.lName + "', email ='" + this.email + "'}";
    }

    public Long getAccountID(){
        return accountID;
    }

    public String getFName(){
        return fName;
    }
    
    public String getLName(){
        return lName;
    }
    
    public String getEmail(){
        return email;
    }

    public void setFName(String fName){
        this.fName = fName;
    }

    public void setLName(String lName){
        this.lName = lName;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
