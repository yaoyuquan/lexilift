package com.github.lexilift.account.mapper;

import com.github.lexilift.account.entity.Account;
import com.github.lexilift.account.entity.AccountQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    void insert(Account account);

    long count(AccountQuery query);
}
