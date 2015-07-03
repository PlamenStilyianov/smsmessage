package com.fingenius.services.queries;

import com.fingenius.domain.Message;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
public class MessageQuery implements SmsQuery {
    private final static Logger logger = LogManager.getLogger(MessageQuery.class.getName());
    @PersistenceContext
    private static EntityManager entityManager;

    public static List<Message> findSenderMessages(EntityManager em, String fromNumber) {
        logger.log(Level.INFO, "MessageQuery: inside findSenderMessages");
        entityManager =  em;
        Query query= entityManager.createQuery("select m from Message m where m.fromNumber = :numberFrom or m.toNumber = :numberTo order by m.dateSend")
                .setParameter("numberFrom", fromNumber)
                .setParameter("numberTo", fromNumber);
        List<Message> messages = (List<Message>)query.getResultList();

        return messages;
    }
}
