package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FailActions")
public class FailActions {

    @Id
    @Column(name = "NAME")
    private String name;

    @Column(name = "ENDPOINT")
    private String endpoint;

    @Column(name = "ERROR_DESCRIPTION")
    private String errorDescription;

    @Column(name = "RESPONSE_TIME")
    private int responseTime;

    @Column(name = "RESPONSE_CODE")
    private int responseCode;

    public FailActions(String name, String endpoint, String errorDescription, int responseTime, int responseCode) {
        this.name = name;
        this.endpoint = endpoint;
        this.errorDescription = errorDescription;
        this.responseTime = responseTime;
        this.responseCode = responseCode;
    }

    public FailActions() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

}
