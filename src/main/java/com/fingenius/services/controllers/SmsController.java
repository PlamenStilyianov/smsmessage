package com.fingenius.services.controllers;

import com.fingenius.json.Counter;
import com.fingenius.services.data.RetrieveDataService;
import com.fingenius.services.data.StoreDataService;
import com.fingenius.services.queries.NumberCountQuery;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;


@Controller
@Repository
@javax.transaction.Transactional
@SessionAttributes(types = Counter.class)
public class SmsController {
    private final static Logger logger = LogManager.getLogger(SmsController.class.getName());
    private StoreDataService data;
    protected static final String SMS_VIEW = "sms";

    @PersistenceContext
    private EntityManager entityManager;


    @RequestMapping(value = "/sms", method = { RequestMethod.GET, RequestMethod.POST })
    @Transactional
    public String service(HttpServletRequest request, Model model) throws IOException {
        logger.log(Level.INFO, "SMS Controller: inside service()");


        //Sender
        String fromNumber = request.getParameter("From");
        String fromCountry = request.getParameter("FromCountry");
        String accountSid = request.getParameter("AccountSid");

        //Message
        String messageSid = request.getParameter("MessageSid");
        String smsMessageSid = request.getParameter("SmsMessageSid");
        String smsStatus = request.getParameter("SmsStatus");
        String smsSid = request.getParameter("SmsSid");
        Date dateSend = Calendar.getInstance().getTime();
        String body = request.getParameter("Body");

        //Receiver
        String toNumber = request.getParameter("To");
        String toState = request.getParameter("ToState");
        String toCountry = request.getParameter("ToCountry");

        data = new StoreDataService(entityManager);
        data.addSender(accountSid, fromNumber, fromCountry);
        data.addMessage(messageSid, smsMessageSid, smsStatus, smsSid, body, dateSend, fromNumber, toNumber);
        data.addReceiver(messageSid, accountSid, toNumber, toState, toCountry);

        model.addAttribute("message", RetrieveDataService.findMessage(entityManager));
        model.addAttribute("sender", RetrieveDataService.findSender(entityManager));
        model.addAttribute("receiver", RetrieveDataService.findReceiver(entityManager));

        Counter smsNumberCounter = NumberCountQuery.countMessagesPerNumber(entityManager, fromNumber);

        ServletContext application = request.getSession().getServletContext();
        if (application.getAttribute("smsNumberCounter") != null){
            application.removeAttribute("smsNumberCounter");
        }
        application.setAttribute("smsNumberCounter", smsNumberCounter);

        return SMS_VIEW;

    }

    public void forTestOnly(String fromNumber, String fromCountry, Date dateSend, String accountSid, String messageSid, String smsMessageSid, String smsStatus, String smsSid, String body, String toNumber, String toState, String toCountry) {
        logger.log(Level.INFO,"SMS Controller: inside forTestOnly()");
        data = new StoreDataService(entityManager);
        data.addSender(accountSid, fromNumber, fromCountry);
        data.addMessage(messageSid, smsMessageSid, smsStatus, smsSid, body, dateSend, fromNumber, fromNumber);
        data.addReceiver(messageSid, accountSid, toNumber, toState, toCountry);
    }
}
