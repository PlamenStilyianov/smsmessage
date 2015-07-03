package com.fingenius.services.queries;

import com.fingenius.domain.Message;
import com.fingenius.json.Counter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
public class NumberCountQuery {
    private final static Logger logger = LogManager.getLogger(NumberCountQuery.class.getName());

    @PersistenceContext
    private static EntityManager entityManager;

    public static Counter countMessagesPerNumber(EntityManager em, String fromNumber) {
        logger.log(Level.INFO, "NumberCountQuery: inside countMessagesPerNumber");
        entityManager =  em;
        Query query= entityManager.createQuery("select m from Message m where m.fromNumber = :numberFrom ")
                .setParameter("numberFrom", fromNumber);
        List<Message> messages = (List<Message>)query.getResultList();

        Counter vo = new Counter(fromNumber,messages.size());


        return vo;
    }
}


