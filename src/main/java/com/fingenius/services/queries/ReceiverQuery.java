package com.fingenius.services.queries;

import com.fingenius.domain.Receiver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ReceiverQuery implements SmsQuery {
    private final static Logger logger = LogManager.getLogger(ReceiverQuery.class.getName());

    @PersistenceContext
    private static EntityManager entityManager;

    public static Receiver findReceiverNumbers(EntityManager em, String numberSender) {
        logger.log(Level.INFO, "ReceiverQuery: inside findReceiverNumbers");
        entityManager =  em;
        Query query= entityManager.createNativeQuery("select * from Receiver r where (r.MESSAGESID, r.ACCOUNTSID ) in \n" +
                "(select m.MESSAGESID, s.ACCOUNTSID from Sender s, Message m where s.FROMNUMBER = m.FROMNUMBER and m.FROMNUMBER = :fromNumber)", com.fingenius.domain.Receiver.class).setParameter("fromNumber", numberSender);
        List<Receiver> receiver = (List<Receiver>) query.getResultList();

        return receiver.get(0);
    }

}
