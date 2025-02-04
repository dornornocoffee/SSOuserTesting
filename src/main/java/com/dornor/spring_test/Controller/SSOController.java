package com.dornor.spring_test.Controller;

import com.dornor.spring_test.Model.Respond;
import com.dornor.spring_test.Model.ResponseData;
import com.dornor.spring_test.Model.SSO_user;
import com.dornor.spring_test.Repository.SSORepository;
import com.dornor.spring_test.Repository.SSOjdbcRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apitest")
@CrossOrigin()
public class SSOController {

    private final SSOjdbcRepository ssojdbcRepository;

    public SSOController(SSORepository ssoRepository, SSOjdbcRepository ssojdbcRepository) {
        this.ssojdbcRepository = ssojdbcRepository;
    }

    @GetMapping("")
    public SSOjdbcRepository getSsoRepository() {
        return ssojdbcRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    public Respond setRespond(SSO_user ssoUser, String statusCode, String statusMessage) {

        if (ssoUser == null || ssoUser.userId() == null) {
            statusCode = String.valueOf(HttpStatus.BAD_REQUEST);
            statusMessage = "ไม่พบข้อมูลสำหรับทำรายการ";
        }

        if(statusCode == String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR)){
            ResponseData responseData = new ResponseData(ssoUser.userId(),null);
            Respond res = new Respond(
                    statusCode,
                    statusMessage,
                    responseData
            );
            return res;
        } else {
            ResponseData responseData = new ResponseData(ssoUser.userId(),ssoUser.tokenId());
            Respond res = new Respond(
                    statusCode,
                    statusMessage,
                    responseData
            );
            return res;
        }

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/gentoken")
    public Respond insertSso(@RequestBody SSO_user ssoUser) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String statusCode = "";
        String statusMessage = "";
        try {
            ssojdbcRepository.createSSO(
                    now,
                    ssoUser.ssoType(),
                    ssoUser.systemId(),
                    ssoUser.systemName(),
                    ssoUser.systemTransactions(),
                    ssoUser.systemPrivileges(),
                    ssoUser.systemUserGroup(),
                    ssoUser.systemLocationGroup(),
                    ssoUser.userId(),
                    ssoUser.userFullName(),
                    ssoUser.userRdOfficeCode(),
                    ssoUser.userOfficeCode(),
                    ssoUser.clientLocation(),
                    ssoUser.locationMachineNumber(),
                    ssoUser.tokenId()
            );
            statusCode = String.valueOf(HttpStatus.OK);
            statusMessage = "ทำรายการเรียบร้อย";
            Respond res = setRespond(ssoUser,statusCode,statusMessage);
            return res;
        } catch (DataAccessException e) {
            statusCode = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR);
            statusMessage = "ไม่พบฐานข้อมูลสำหรับทำรายการ";
            return setRespond(ssoUser,statusCode,statusMessage);
        }
    }
}
