package com.example.ejbs;

import com.example.entities.ServicesLastRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class ServicesLastRecordEJB {


    @PersistenceContext(unitName = "persist")
    private EntityManager em;

    private static final Logger ServiceLastRecordLOGGER = LoggerFactory.getLogger(ServicesLastRecordEJB.class);

    public void updateService(ServicesLastRecord service) {
        if (em.contains(service)) {
            em.persist(service);
        } else {
            em.merge(service);
        }
    }

    public List<ServicesLastRecord> getAll() {
        TypedQuery<ServicesLastRecord> q = em.createNamedQuery(ServicesLastRecord.GET_ALL, ServicesLastRecord.class);
        return q.getResultList();
    }

    public void delete(String serviceName) {
        ServicesLastRecord servicesLastRecord = em.createNamedQuery(ServicesLastRecord.GET_BY_NAME,
                ServicesLastRecord.class).setParameter("serviceName", serviceName).getSingleResult();
        try {
            em.remove(servicesLastRecord);
        } catch (Exception e) {
            ServiceLastRecordLOGGER.error("Failed to remove.");
        }
    }
}
