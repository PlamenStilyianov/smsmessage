package com.fingenius.services.data;

import com.fingenius.domain.Message;
import com.fingenius.domain.Receiver;
import com.fingenius.domain.Sender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;


@Transactional
public class RetrieveDataService {
    private final static Logger logger = LogManager.getLogger(RetrieveDataService.class.getName());
    @PersistenceContext
    private static EntityManager entityManager;

    public static Message findMessage(EntityManager em) {
        logger.log(Level.INFO, "RetrieveDataService: inside findMessage");
        entityManager =  em;
        Query query= entityManager.createQuery("select m from Message m order by m.id desc");
        int last = query.getResultList().size() - 1;
        return (Message) query.getResultList().get(0);
    }

    public static Sender findSender(EntityManager em) {
        logger.log(Level.INFO, "RetrieveDataService: inside findSender");
        entityManager =  em;
        Query query= entityManager.createQuery("select s from Sender s order by s.id desc");
        int last = query.getResultList().size() - 1;
        return (Sender) query.getResultList().get(0);
    }

    public static Receiver findReceiver(EntityManager em) {
        logger.log(Level.INFO, "RetrieveDataService: inside findReceiver");
        entityManager =  em;
        Query query= entityManager.createQuery("select r from Receiver r order by r.id desc");
        int last = query.getResultList().size() - 1;
        return (Receiver) query.getResultList().get(0);
    }

}
