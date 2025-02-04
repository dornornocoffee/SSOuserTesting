package com.dornor.spring_test.Model;

public record SSO_user(
        String request_date,
        String ssoType,
        String systemId,
        String systemName,
        String systemTransactions,
        String systemPrivileges,
        String systemUserGroup,
        String systemLocationGroup,
        String userId,
        String userFullName,
        String userRdOfficeCode,
        String userOfficeCode,
        String clientLocation,
        String locationMachineNumber,
        String tokenId

) {

}
