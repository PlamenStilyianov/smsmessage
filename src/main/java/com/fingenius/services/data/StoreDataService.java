package com.fingenius.services.data;

import com.fingenius.domain.Message;
import com.fingenius.domain.Receiver;
import com.fingenius.domain.Sender;
import com.fingenius.domain.SmsEntity;
import com.fingenius.domain.login.Authorities;
import com.fingenius.domain.login.Users;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@Transactional
public class StoreDataService {
    private final static Logger logger = LogManager.getLogger(StoreDataService.class.getName());
    @PersistenceContext
    private EntityManager entityManager;

    public StoreDataService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addSender(String accountSid, String fromNumber, String fromCountry){
        logger.log(Level.INFO, "StoreDataService: inside addSender");
        Sender sender = new Sender();
        sender.setAccountSid(accountSid);
        sender.setFromNumber(fromNumber);
        sender.setFromCountry(fromCountry);

        persist(sender);

    }

    public void addMessage(String messageSid, String smsMessageSid, String smsStatus, String smsSid, String body, Date dateSend, String fromNumber, String toNumber){
        logger.log(Level.INFO, "StoreDataService: inside addMessage");
        Message message = new Message();
        message.setMessageSid(messageSid);
        message.setSmsMessageSid(smsMessageSid);
        message.setSmsStatus(smsStatus);
        message.setSmsSid(smsSid);
        message.setBody(body);
        message.setDateSend(dateSend);
        message.setFromNumber(fromNumber);
        message.setToNumber(toNumber);
        persist(message);
    }

    public void addReceiver(String messageSid, String accountSid, String toNumber, String toState, String toCountry){
        logger.log(Level.INFO, "StoreDataService: inside addReceiver");
        Receiver receiver = new Receiver();
        receiver.setMessageSid(messageSid);
        receiver.setAccountSid(accountSid);
        receiver.setToNumber(toNumber);
        receiver.setToState(toState);
        receiver.setToCountry(toCountry);

        persist(receiver);
    }

    public void addUser(String userName, String password){
        logger.log(Level.INFO, "StoreDataService: inside addUser");
        Users user = new Users();
        user.setUsername(userName);
        user.setUserpassword(password);
        user.setEnabled(true);
        Authorities authorities = new Authorities();
        authorities.setUsername(userName);
        authorities.setAuthority("ROLE_USER");
        persist(user);
        persist(authorities);
    }

    private void persist(SmsEntity entity) {
        if(entity.getId() == null){
            entityManager.persist(entity);

        } else {
            entityManager.merge(entity);

        }
        entityManager.flush();

    }
}
