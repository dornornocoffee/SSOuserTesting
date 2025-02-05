package com.dornor.spring_test.Controller;

import com.dornor.spring_test.Model.Respond;
import com.dornor.spring_test.Model.ResponseData;
import com.dornor.spring_test.Model.SSO_user;
import com.dornor.spring_test.Repository.SSORepository;
import com.dornor.spring_test.Repository.SSOjdbcRepository;
import com.dornor.spring_test.Service.SSOservice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/")
@CrossOrigin()
@Tag(name = "SSO User Controller", description = "APIS for testing create SSO user")
public class SSOController {

    private final SSOservice ssoservice;

    public SSOController(SSOservice ssoservice) {
        this.ssoservice = ssoservice;
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get user all", description = "Returns all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved users"),
            @ApiResponse(responseCode = "404", description = "Users not found"),
            @ApiResponse(responseCode = "500", description = "Internal sever error")
    })
    public SSOservice getSSOservice() {
        return ssoservice;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/gentoken")
    @Operation(summary = "Create the user", description = "Returns the respond token and userId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created user"),
            @ApiResponse(responseCode = "404", description = "Information not found"),
            @ApiResponse(responseCode = "500", description = "Database connection failed")
    })
    public Respond insertSso(@RequestBody SSO_user ssoUser) {
       return ssoservice.createSSO(ssoUser);
    }
}
