package com.example.ejbs;

import com.example.entities.FailActions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FailActionsEJB {

    @PersistenceContext(unitName = "persist")
    private EntityManager em;

    private static final Logger FailActions_LOGGER = LoggerFactory.getLogger(FailActionsEJB.class);

    public void addFailAction(String name, String endpoint, String description, int responseTime, int responseCode) {
        FailActions fail = new FailActions(name, endpoint, description, responseTime, responseCode);
        if (em.contains(fail)) {
            em.merge(fail);
        } else {
            em.persist(fail);
        }
    }

}
