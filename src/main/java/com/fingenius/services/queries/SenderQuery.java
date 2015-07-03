package com.fingenius.services.queries;

import com.fingenius.domain.Sender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Transactional
public class SenderQuery implements SmsQuery {
    private final static Logger logger = LogManager.getLogger(SenderQuery.class.getName());

    @PersistenceContext
    private static EntityManager entityManager;

    public static List<Sender> findSenderNumbers(EntityManager em) {
        logger.log(Level.INFO, "SenderQuery: inside findSenderNumbers");
        entityManager =  em;
        Query query= entityManager.createQuery("select s from Sender s  order by s.fromNumber");
        List<Sender> senders = (List<Sender>) query.getResultList();
        SortedMap<String, Sender> map = new TreeMap<>();
        for(Sender s : senders){
            map.put(s.getFromNumber(), s);
        }
        senders.clear();
        for(Sender s : map.values()) {
            senders.add(s);
        }
        return senders;
    }

    public static List<Sender> findSenderNumbersWithoutFrom(EntityManager em, String from) {
        logger.log(Level.INFO, "SenderQuery: inside findSenderNumbersWithoutFrom");
        entityManager =  em;
        Query query= entityManager.createQuery("select s from Sender s where s.fromNumber != :fromNumber   order by s.fromNumber")
                                  .setParameter("fromNumber", from);
        List<Sender> senders = (List<Sender>) query.getResultList();
        SortedMap<String, Sender> map = new TreeMap<>();
        for(Sender s : senders){
            map.put(s.getFromNumber(), s);
        }
        senders.clear();
        for(Sender s : map.values()) {
            senders.add(s);
        }
        return senders;
    }

    public static Sender findSenderNumbers(EntityManager em, String numberSender) {
        logger.log(Level.INFO, "SenderQuery: inside findSenderNumbers");
        entityManager =  em;
        Query query= entityManager.createQuery("select s from Sender s where s.fromNumber = :fromNumbrer").setParameter("fromNumbrer", numberSender);
        List<Sender> sender = (List<Sender>) query.getResultList();

        return sender.get(0);
    }
}
