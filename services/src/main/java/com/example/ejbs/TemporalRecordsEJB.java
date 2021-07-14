package com.example.ejbs;

import com.example.entities.TemporalRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Stateless
public class TemporalRecordsEJB {

    @PersistenceContext(unitName = "persist")
    private EntityManager em;

    private static final Logger TemporalRecordLOGGER = LoggerFactory.getLogger(TemporalRecordsEJB.class);

    public List<TemporalRecords> getResponseTime(String serviceName, String startDate, String endDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date upToDate = new Date();
        Date sinceDate = new Date();
        List<TemporalRecords> result;
        try {
            if (endDate != null) {
                upToDate = formatter.parse(endDate);
            }
            if (startDate != null) {
                sinceDate = formatter.parse(startDate);
            } else {
                //2021-06-24 00:00:00:00
                sinceDate.setTime(1624482000000L);
                System.out.println("Default Since is :" + sinceDate.toString());
            }
            Timestamp timestampUpTo = new java.sql.Timestamp((upToDate.getTime()));
            Timestamp timestampSince = new java.sql.Timestamp((sinceDate.getTime()));
            TypedQuery<TemporalRecords> q;
            if (serviceName == null) {
                q = em.createNamedQuery(TemporalRecords.RESPONSE_TIME_WITH_OUT_NAME, TemporalRecords.class);
                q.setParameter("since", timestampSince);
                q.setParameter("upTo", timestampUpTo);
            } else {
                q = em.createNamedQuery(TemporalRecords.RESPONSE_TIME_WITH_NAME, TemporalRecords.class);
                q.setParameter("since", timestampSince);
                q.setParameter("upTo", timestampUpTo);
                q.setParameter("requestedName", serviceName);
            }
            result = q.getResultList();
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    public void addTemporalRecord(TemporalRecords record) {
        em.merge(record);
    }

    public void delete(String serviceName) {
        List<TemporalRecords> services = em.createNamedQuery(TemporalRecords.GET_SERVICES_BY_NAME, TemporalRecords.class)
                .setParameter("requestedName", serviceName).getResultList();
        try {
            for (int i = 0; i < services.size(); i++) {
                int currentId = services.get(i).getId();
                TemporalRecords currentRecord = em.find(TemporalRecords.class, currentId);
                em.remove(currentRecord);
            }
        } catch (Exception e) {
            TemporalRecordLOGGER.error("Failed to remove");
        }
    }
}

