package com.dornor.spring_test.Controller;

import com.dornor.spring_test.Model.SSO_user;
import com.dornor.spring_test.Repository.SSORepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apitest")
public class SSOController {

    private final SSORepository ssoRepository;

    public SSOController(SSORepository ssoRepository) {
        this.ssoRepository = ssoRepository;
    }

    @GetMapping("")
    public SSORepository getSsoRepository() {
        return ssoRepository;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/apitest/gentoken")
    public List<SSO_user> insertSso(@RequestBody SSO_user ssoUser) {
        ssoRepository.save(ssoUser);
        return null;
    }
}
