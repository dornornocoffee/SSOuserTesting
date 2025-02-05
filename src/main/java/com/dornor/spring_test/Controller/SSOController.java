package com.dornor.spring_test.Controller;

import com.dornor.spring_test.Entity.RespondEntity;
import com.dornor.spring_test.Entity.SSOUser;
import com.dornor.spring_test.Service.SSOservice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<SSOUser> getSSOservice() {
        return ssoservice.getAllSSO();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/gentoken")
    @Operation(summary = "Create the user", description = "Returns the respond token and userId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created user"),
            @ApiResponse(responseCode = "404", description = "Information not found"),
            @ApiResponse(responseCode = "500", description = "Database connection failed")
    })
    public RespondEntity insertSso(@RequestBody SSOUser ssoUser) {
       return ssoservice.createSSO(ssoUser);
    }
}
