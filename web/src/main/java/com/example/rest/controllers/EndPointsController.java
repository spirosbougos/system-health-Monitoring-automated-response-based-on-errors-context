package com.example.rest.controllers;

import com.example.ejbs.EndPointsEJB;
import com.example.entities.EndPoints;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/endpoints")
public class EndPointsController {

    @EJB
    EndPointsEJB endpoint;

    @DELETE
    @Path("/deleteEndpoint/{name}")
    public void delete(@PathParam("name") String serviceName) {
        endpoint.delete(serviceName);
    }

    @POST
    @Path("/addEndpoint")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EndPoints add(EndPoints newEndpoint) {
        endpoint.addEndpoint(newEndpoint);
        return newEndpoint;
    }
}
