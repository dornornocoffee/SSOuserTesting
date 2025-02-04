package com.dornor.spring_test.Repository;

import com.dornor.spring_test.Model.SSO_user;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SSOjdbcRepository {
    private JdbcTemplate jdbcTemplate;

    public SSOjdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static SSO_user mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SSO_user(rs.getString("request_date"),
                rs.getString("ssotype"),
                rs.getString("systemid"),
                rs.getString("systemname"),
                rs.getString("systemtransactions"),
                rs.getString("systemprivileges"),
                rs.getString("systemusergroup"),
                rs.getString("systemlocationgroup"),
                rs.getString("userid"),
                rs.getString("userfullname"),
                rs.getString("userrdofficecode"),
                rs.getString("userofficecode"),
                rs.getString("clientlocation"),
                rs.getString("locationmachinenumber"),
                rs.getString("tokenid"));

    }

    public List<SSO_user> findAll() {
        String sql = "select * from SSO_USER_TEST";
        List<SSO_user> users = jdbcTemplate.query(sql, SSOjdbcRepository::mapRow);
        return users;
    }

    public void createSSO(
            String requestDate,
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
        String sql = "INSERT INTO SSO_USER_TEST " +
                "(request_date, ssoType, systemId, systemName, systemTransactions, " +
                "systemPrivileges, systemUserGroup, systemLocationGroup, userId, " +
                "userFullName, userRdOfficeCode, userOfficeCode, clientLocation, " +
                "locationMachineNumber, tokenId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, requestDate, ssoType, systemId, systemName,
                systemTransactions, systemPrivileges, systemUserGroup, systemLocationGroup,
                userId, userFullName, userRdOfficeCode, userOfficeCode, clientLocation,
                locationMachineNumber, tokenId);
    }
}
