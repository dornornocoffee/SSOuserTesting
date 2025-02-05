package com.dornor.spring_test.Service;

import com.dornor.spring_test.Model.Respond;
import com.dornor.spring_test.Model.ResponseData;
import com.dornor.spring_test.Model.SSO_user;
import com.dornor.spring_test.Repository.SSOjdbcRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SSOservice {
    private SSOjdbcRepository repository;

    public SSOservice(SSOjdbcRepository repository) {
        this.repository = repository;
    }

    public Respond createSSO(SSO_user sso_user) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        try {
            if (sso_user == null || sso_user.userId() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ไม่พบข้อมูล");
            }
            repository.createSSO(
                    now,
                    sso_user.ssoType(),
                    sso_user.systemId(),
                    sso_user.systemName(),
                    sso_user.systemTransactions(),
                    sso_user.systemPrivileges(),
                    sso_user.systemUserGroup(),
                    sso_user.systemLocationGroup(),
                    sso_user.userId(),
                    sso_user.userFullName(),
                    sso_user.userRdOfficeCode(),
                    sso_user.userOfficeCode(),
                    sso_user.clientLocation(),
                    sso_user.locationMachineNumber(),
                    sso_user.tokenId()
            );
            return setResponse(sso_user);


        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ไม่พบฐานข้อมูลทำรายการ");
        }

    }

    public Respond setResponse(SSO_user sso_user) {
        Respond res = new Respond(String.valueOf(HttpStatus.OK.value()),"ทำรายการเรียบร้อย",new ResponseData(sso_user.userId(),sso_user.tokenId()));
        return res;
    }

    public List<SSO_user> getAllSSO() {
        return repository.findAll();
    }



}
