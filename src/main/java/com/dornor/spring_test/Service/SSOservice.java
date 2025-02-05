package com.dornor.spring_test.Service;

import com.dornor.spring_test.Entity.RespondEntity;
import com.dornor.spring_test.Entity.ResponseDataEntity;
import com.dornor.spring_test.Entity.SSOUser;
import com.dornor.spring_test.Repository.SSORepository;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SSOservice {
    private SSORepository ssoRepository;

    public SSOservice(SSORepository ssoRepository) {
        this.ssoRepository = ssoRepository;
    }

    public RespondEntity createSSO(SSOUser ssoUser) {

        if (ssoUser == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ไม่พบข้อมูล");
        }
        if (ssoUser.getRequestDate() == null) {
            String now = LocalDateTime.now().toString();
            ssoUser.setRequestDate(now);
        }

        try {
            ssoRepository.save(ssoUser);
            return setResponse(ssoUser);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ไม่พบฐานข้อมูลทำรายการ", e);
        }
    }

    public RespondEntity setResponse(SSOUser sso_user) {
        RespondEntity res = new RespondEntity(String.valueOf(HttpStatus.OK.value()),"ทำรายการเรียบร้อย",new ResponseDataEntity(sso_user.getUserId(),sso_user.getTokenId()));
        return res;
    }

    public List<SSOUser> getAllSSO() {
        return ssoRepository.findAll();
    }



}
