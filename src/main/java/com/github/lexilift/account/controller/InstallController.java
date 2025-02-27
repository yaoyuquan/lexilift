package com.github.lexilift.account.controller;

import com.github.lexilift.account.entity.InstallRequest;
import com.github.lexilift.account.service.AccountService;
import com.github.lexilift.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/install")
public class InstallController {

    @Autowired
    private AccountService accountService;


    @GetMapping
    public Response install(InstallRequest request) {

        accountService.install(request);

        return Response.ok();
    }

    @PostMapping
    public Response installPost(@RequestBody InstallRequest request) {

        accountService.install(request);

        return Response.ok();
    }
}
