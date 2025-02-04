package com.dornor.spring_test.Model;

public record Respond(
    String responseCode,
    String responseMessage,
    ResponseData responseData

) {
}

