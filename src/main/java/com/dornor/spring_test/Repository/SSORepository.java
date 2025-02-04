package com.dornor.spring_test.Repository;

import com.dornor.spring_test.Model.SSO_user;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SSORepository {
    private final List<SSO_user> ssoUsers = new ArrayList<SSO_user>();

    public SSORepository() {}

    public List<SSO_user> getSSOUsers() {
        return ssoUsers;
    }

    public void save(SSO_user ssoUser) {
        ssoUsers.add(ssoUser);
    }
}
