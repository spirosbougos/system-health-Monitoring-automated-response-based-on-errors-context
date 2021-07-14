package com.example.rest.controllers;

import com.example.ejbs.Service;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/commonService")
public class ServiceController {

    @EJB
    private Service service;

    @DELETE
    @Path("/delete/{name}")
    public void delete(@PathParam("name") String serviceName) {
        service.delete(serviceName);
    }
}
