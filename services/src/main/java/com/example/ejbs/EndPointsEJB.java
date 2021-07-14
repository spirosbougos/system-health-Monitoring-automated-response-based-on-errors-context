package com.example.ejbs;

import com.example.entities.EndPoints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class EndPointsEJB {
    @PersistenceContext(unitName = "persist")
    private EntityManager em;

    private static final Logger EndPoints_LOGGER = LoggerFactory.getLogger(EndPointsEJB.class);

    public List<EndPoints> getEndpoints() {
        TypedQuery<EndPoints> q = em.createNamedQuery(EndPoints.GET_ENDPOINTS, EndPoints.class);
        return q.getResultList();
    }

    public boolean delete(String endpointName) {
        EndPoints endpoint = em.createNamedQuery(EndPoints.FIND_BY_NAME, EndPoints.class)
                .setParameter("endpointName", endpointName).getSingleResult();
        try {
            em.remove(endpoint);
            return true;
        } catch (Exception e) {
            EndPoints_LOGGER.error("Failed to remove.");
            return false;
        }
    }

    public void addEndpoint(EndPoints endpoint) {
        if (em.contains(endpoint)) {
            em.merge(endpoint);
        } else {
            em.persist(endpoint);
        }
    }

}
