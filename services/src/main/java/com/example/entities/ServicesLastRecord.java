package com.example.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ServicesLastRecord")
@NamedQueries({
        @NamedQuery(
                name = "GET.ALL.RECORDS",
                query = "select s from ServicesLastRecord s"
        ),
        @NamedQuery(
                name = "GET.BY.NAME",
                query = "Select s from ServicesLastRecord s where s.name = :serviceName"
        )
})
public class ServicesLastRecord {

    public static final String GET_ALL = "GET.ALL.RECORDS";
    public static final String GET_BY_NAME = "GET.BY.NAME";

    @Id
    @Column(name = "SERVICE_NAME")
    private String name;

    @Column(name = "ENDPOINT")
    private String endpoint;

    @Column(name = "METHOD_TYPE")
    private String methodType;

    @Column(name = "HTTP_RESPONSE_CODE")
    private int http_response_code;

    @Column(name = "REQUESTED_DATE")
    private Timestamp requestedDate;

    @Column(name = "RESPONSE_TIME")
    private int responseTime;

    public ServicesLastRecord(String name, String endpoint, String methodType, int http_response_code, Timestamp requestedDate, int responseTime) {
        this.name = name;
        this.endpoint = endpoint;
        this.methodType = methodType;
        this.http_response_code = http_response_code;
        this.requestedDate = requestedDate;
        this.responseTime = responseTime;
    }

    public ServicesLastRecord() {}

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

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public int getHttp_response_code() {
        return http_response_code;
    }

    public void setHttp_response_code(int http_response_code) {
        this.http_response_code = http_response_code;
    }

    public String getRequestedDate() {
        return requestedDate.toString();
    }

    public void setRequestedDate(Timestamp requestedDate) {
        this.requestedDate = requestedDate;
    }

    public float getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }
}
