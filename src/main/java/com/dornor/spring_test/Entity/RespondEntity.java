package com.dornor.spring_test.Entity;

public class RespondEntity {
    private String status;
    private String message;
    private ResponseDataEntity data;

    public RespondEntity(String status, String message, ResponseDataEntity data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseDataEntity getData() {
        return data;
    }

    public void setData(ResponseDataEntity data) {
        this.data = data;
    }
}
