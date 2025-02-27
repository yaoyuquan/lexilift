package com.github.lexilift.account.entity;

import lombok.Data;

@Data
public class Account {

    private String accountId;

    private String accountName;

    private String email;

    private String password;

    private String passwordSalt;

    private String roleId;

    private String status;
}
