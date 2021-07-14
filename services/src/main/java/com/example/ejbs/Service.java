package com.example.ejbs;

import com.example.entities.ServicesLastRecord;
import com.example.entities.TemporalRecords;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.Timestamp;

@Stateless
public class Service {

    @EJB
    EndPointsEJB endpoints;
    @EJB
    TemporalRecordsEJB temporalRecords;
    @EJB
    ServicesLastRecordEJB serviceLastRecord;

    public void update(String name, String endpoint, String methodType, int responseStatus,
                       Timestamp date, int responseTime) {
        ServicesLastRecord lastService = new ServicesLastRecord(name, endpoint, methodType, responseStatus, date,
                responseTime);
        TemporalRecords temporalService = new TemporalRecords(name, endpoint, methodType, responseStatus, date,
                responseTime);
        serviceLastRecord.updateService(lastService);
        temporalRecords.addTemporalRecord(temporalService);
    }

    public void delete(String serviceName) {
        endpoints.delete(serviceName);
        serviceLastRecord.delete(serviceName);
        temporalRecords.delete(serviceName);
    }
}
