package com.github.lexilift.account.service;

import com.github.lexilift.account.entity.Account;
import com.github.lexilift.account.entity.AccountQuery;
import com.github.lexilift.account.entity.InstallRequest;
import com.github.lexilift.account.mapper.AccountMapper;
import com.github.lexilift.system.service.IdService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.github.lexilift.account.AccountConstants.ACCOUNT_STATUS_ENABLE;
import static com.github.lexilift.account.AccountConstants.ROLE_ID_ADMIN;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IdService idService;

    public void install(InstallRequest request) {

        AccountQuery query = new AccountQuery();
        query.setRoleId(ROLE_ID_ADMIN);
        long count = accountMapper.count(query);

        if(count > 0) {
            throw new IllegalStateException("系统已经初始化，不能重复初始化");
        }

        Account account = new Account();

        account.setAccountId(idService.generateId());
        account.setAccountName(request.getAccountName());
        account.setEmail(request.getEmail());

        String salt = RandomStringUtils.insecure().next(6, true, false);
        String password = passwordEncoder.encode(request.getPassword() + salt);

        account.setPassword(password);
        account.setPasswordSalt(salt);

        account.setRoleId(ROLE_ID_ADMIN);
        account.setStatus(ACCOUNT_STATUS_ENABLE);

        accountMapper.insert(account);
    }
}
