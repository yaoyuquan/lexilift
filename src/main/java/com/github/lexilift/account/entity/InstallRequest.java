package com.github.lexilift.account.entity;

import lombok.Data;

@Data
public class InstallRequest {

    private String accountName;

    private String email;

    private String password;
}
