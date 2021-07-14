package com.example.rest.controllers;

import com.example.ejbs.ServicesLastRecordEJB;
import com.example.entities.ServicesLastRecord;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/service")
public class ServiceLastRecordController {

    @EJB
    private ServicesLastRecordEJB servicesLastRecord;

    @GET
    @Path("/getLastServices")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ServicesLastRecord> getAll() {
        return servicesLastRecord.getAll();
    }

    @DELETE
    @Path("/deleteService/{name}")
    public void delete(@PathParam("name") String serviceName) {
        servicesLastRecord.delete(serviceName);
    }

}
