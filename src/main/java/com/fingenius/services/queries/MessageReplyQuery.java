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
public class MessageReplyQuery implements SmsQuery {
    private final static Logger logger = LogManager.getLogger(MessageReplyQuery.class.getName());
    @PersistenceContext
    private static EntityManager entityManager;

    public static List<Message> findSenderMessages(EntityManager em, String toNumber) {
        logger.log(Level.INFO, "MessageReplyQuery: inside findSenderMessages");
        entityManager =  em;
        Query query= entityManager.createQuery("select m from Message m where m.fromNumber = :numberFrom or m.toNumber = :numberTo order by m.dateSend")
                .setParameter("numberFrom", toNumber)
                .setParameter("numberTo", toNumber);

        List<Message> messages = (List<Message>)query.getResultList();

        return messages;
    }
}