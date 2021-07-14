package com.example.entities;

import javax.persistence.*;

@Entity
@Table(name = "ENDPOINTS")
@NamedQueries({
        @NamedQuery(
                name = "GET.ALL",
                query = "Select e from EndPoints e"
        ),
        @NamedQuery(
                name = "FIND.BY.NAME",
                query = "Select e from EndPoints e where e.name = :endpointName"
        )
})
public class EndPoints {

    public static final String GET_ENDPOINTS = "GET.ALL";
    public static final String FIND_BY_NAME = "FIND.BY.NAME";
    @Id
    @Column(name = "ID ")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ENDPOINT")
    private String endpoint;

    @Column(name = "FREQUENCY")
    private int frequency;

    @Column(name = "METHOD_TYPE")
    private String methodType;

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
