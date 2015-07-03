package com.fingenius.test.services.spring.orm;

import com.fingenius.services.controllers.SmsController;
import com.fingenius.services.data.RetrieveDataService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@Transactional
@TransactionConfiguration(defaultRollback=false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-application-context.xml"})
public class PersistenceServiceTest {
    private final static Logger logger = LogManager.getLogger(PersistenceServiceTest.class.getName());

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    SmsController service;

    @PersistenceContext
    EntityManager entityManager;

    private String fromNumber;
    private String fromCountry;
    private Date dateSend;
    private String accountSid;
    //Message
    private String messageSid;
    private String smsMessageSid;
    private String smsStatus;
    private String smsSid;
    private String body;

    //Receiver
    private String toNumber;
    private String toState;
    private String toCountry;

    @Before
    public void setUp(){
        this.fromNumber = "From";
        this.fromCountry = "FromCountry";
        this.dateSend = Calendar.getInstance().getTime();
        this.accountSid = "AccountSid";

        //Message
        this.messageSid = "MessageSid";
        this.smsMessageSid = "SmsMessageSid";
        this.smsStatus = "SmsStatus";
        this.smsSid = "SmsSid";
        this.body = "Body";

        //Receiver
        this.toNumber = "To";
        this.toState = "ToState";
        this.toCountry = "ToCountry";
    }

    @Test
    public void message(){
        logger.log(Level.INFO, "PersistenceServiceTest: inside message");
        service.forTestOnly(
                fromNumber,
                fromCountry,
                dateSend,
                accountSid,
                messageSid,
                smsMessageSid,
                smsStatus,
                smsSid,
                body,
                toNumber,
                toState,
                toCountry
        );
        assertEquals("Body",RetrieveDataService.findMessage(entityManager).getBody());
    }

    @Test
    public void createMessage(){
        logger.log(Level.INFO, "PersistenceServiceTest: inside createMessage");
        service.forTestOnly(
                fromNumber,
                fromCountry,
                dateSend,
                accountSid,
                messageSid,
                smsMessageSid,
                smsStatus,
                smsSid,
                body,
                toNumber,
                toState,
                toCountry
        );

        assertNotNull(RetrieveDataService.findSender(entityManager));
        assertNotNull(RetrieveDataService.findMessage(entityManager));
        assertNotNull(RetrieveDataService.findReceiver(entityManager));

    }

}
