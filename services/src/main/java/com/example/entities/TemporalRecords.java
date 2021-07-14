package com.example.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "temporalRecords")
@NamedQueries({
        @NamedQuery(
                name = "GetResponseTimeWithOutName",
                query = "Select r from TemporalRecords r where r.requestedDate >= :since and r.requestedDate <= :upTo"
        ),
        @NamedQuery(
                name = "GetResponseTimeWithName",
                query = "Select r from TemporalRecords r where r.requestedDate >= :since and r.requestedDate <= :upTo and r.serviceName =:requestedName"
        ),
        @NamedQuery(
                name = "GET.TEMPORAL.BY.NAME",
                query = "Select s from TemporalRecords s where s.serviceName =:requestedName"
        )
})
public class TemporalRecords {

    public static final String RESPONSE_TIME_WITH_NAME = "GetResponseTimeWithName";
    public static final String RESPONSE_TIME_WITH_OUT_NAME = "GetResponseTimeWithOutName";
    public static final String GET_SERVICES_BY_NAME = "GET.TEMPORAL.BY.NAME";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "SERVICE_NAME")
    private String serviceName;

    @Column(name = " ENDPOINT")
    String endpoint;

    @Column(name = "METHOD_TYPE")
    String methodType;

    @Column(name = "HTTP_RESPONSE_CODE")
    int http_response_code;

    @Column(name = "REQUESTED_DATE")
    private Timestamp requestedDate;

    @Column(name = "RESPONSE_TIME")
    private int responseTime;

    public TemporalRecords(String serviceName, String endpoint, String methodType, int http_response_code, Timestamp requestedDate, int responseTime) {
        this.serviceName = serviceName;
        this.endpoint = endpoint;
        this.methodType = methodType;
        this.http_response_code = http_response_code;
        this.requestedDate = requestedDate;
        this.responseTime = responseTime;
    }

    public TemporalRecords() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getRequestedDate() {
        return requestedDate.toString();
    }

    public void setRequestedDate(Timestamp requestedDate) {
        this.requestedDate = requestedDate;
    }

    public int getResponseTime() {
        return responseTime;
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

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }
}
