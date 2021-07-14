package com.example.rest.controllers;

import com.example.ejbs.EndPointsEJB;
import com.example.ejbs.TemporalRecordsEJB;
import com.example.entities.TemporalRecords;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

@Path("/temporalRecords")
public class TemporalRecordController {

    @EJB
    private TemporalRecordsEJB temporalRecords;

    @EJB
    private EndPointsEJB endpointsEJB;


    private final Logger log = Logger.getLogger(getClass().getName());

    @GET
    @Path("/getResponseTime")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TemporalRecords> getResponseTime(
            @QueryParam("serviceName") String serviceName,
            @QueryParam("sinceDate") String sinceDate,
            @QueryParam("upToDate") String upToDate) {
        return temporalRecords.getResponseTime(serviceName, sinceDate, upToDate);

    }

    @DELETE
    @Path("/deleteTemporal/{name}")
    public void delete(@PathParam("name") String serviceName) {
        temporalRecords.delete(serviceName);
    }
}




