package com.example.schedulers;


import com.example.ejbs.*;
import com.example.entities.EndPoints;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Logger;


@Startup
@Singleton
public class ServiceScheduler {

    @EJB
    private EndPointsEJB endpointsEJB;

    @EJB
    ServicesLastRecordEJB servicesLastRecordEJB;

    @EJB
    TemporalRecordsEJB temporalRecordsEJB;

    @EJB
    Service service;

    @EJB
    FailActionsEJB failAction;

    private final Logger log = Logger.getLogger(getClass().getName());

    int iterationCount = 0;

    @Schedule(
            second = "*/5",
            minute = "*",
            hour = "*",
            persistent = false)
    public void atSchedule() {
        iterationCount++;
        if (iterationCount > 10000000) {
            iterationCount = 0;
        }

        List<EndPoints> endpoints = endpointsEJB.getEndpoints();

        for (EndPoints endpoint : endpoints) {
            int mf = endpoint.getFrequency();
            int iterations = mf / 5;
            if (iterationCount % iterations == 0) {
                Client client = ClientBuilder.newClient();
                WebTarget target = client.target(endpoint.getEndpoint());
                EndPoints endPPP = new EndPoints();
                Response response;
                int responseTime;
                String methodType = endpoint.getMethodType().toUpperCase();
                if (methodType.equals("GET")) {
                    long start = System.currentTimeMillis();
                    response = target.request(MediaType.APPLICATION_JSON).get();
                    responseTime = (int) (System.currentTimeMillis() - start);
                    service.update(endpoint.getName(), endpoint.getEndpoint(), endpoint.getMethodType(),
                            response.getStatus(), new Timestamp(start), responseTime);
                } else if (methodType.equals("POST")) {
                    long start = System.currentTimeMillis();
                    response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(endPPP));
                    responseTime = (int) (System.currentTimeMillis() - start);
                    service.update(endpoint.getName(), endpoint.getEndpoint(), endpoint.getMethodType(),
                            response.getStatus(), new Timestamp(start), responseTime);
                } else /*if (methodType.equals("DELETE"))*/ {
                    long start = System.currentTimeMillis();
                    response = target.request().delete();
                    responseTime = (int) (System.currentTimeMillis() - start);
                    service.update(endpoint.getName(), endpoint.getEndpoint(), endpoint.getMethodType(),
                            response.getStatus(), new Timestamp(start), responseTime);
                }

                if (response.getStatus() < 200 || response.getStatus() >= 400) {
                    failAction.addFailAction(endpoint.getName(), endpoint.getEndpoint(),
                            response.getStatusInfo().toString(), responseTime, response.getStatus());
                }
            }
        }
    }
}
