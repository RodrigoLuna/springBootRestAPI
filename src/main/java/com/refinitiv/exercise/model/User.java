package com.refinitiv.exercise.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @ApiModelProperty(notes = "User unique Id", hidden = true)
    private Long id;

    @Column(nullable = false)
    @ApiModelProperty(notes = "User's Name")
    private String name;

    @ManyToMany
    @JoinTable(
        name = "assigned_accounts",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    @ApiModelProperty(notes = "Accounts binded to the User", hidden = false)
    private List<Account> assignedAccounts = new ArrayList<>();

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return assignedAccounts;
    }

    public void addAccount(Account account) {
        this.assignedAccounts.add(account);
    }

    public void removeAccount(Account account) {
        this.assignedAccounts.remove(account);
    }

}