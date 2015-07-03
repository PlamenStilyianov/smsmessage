package com.fingenius.services.controllers;

import com.fingenius.json.Counter;
import com.fingenius.services.data.StoreDataService;
import com.fingenius.services.queries.MessageReplyQuery;
import com.fingenius.services.queries.NumberCountQuery;
import com.fingenius.services.queries.ReceiverQuery;
import com.fingenius.services.queries.SenderQuery;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@Transactional
public class MessagesReplyController {
    private final static Logger logger = LogManager.getLogger(MessagesReplyController.class.getName());

    @Value("${ACCOUNT_SID}")
    private String ACCOUNT_SID;

    @Value("${AUTH_TOKEN}")
    private String AUTH_TOKEN;

    @Value("${FROM}")
    private String FROM;

    @PersistenceContext
    private EntityManager entityManager;

    protected static final String MESSAGE_REPLY_VIEW = "messagesreply";


    @RequestMapping(value = "/messagesreply", method = RequestMethod.GET)
    @Transactional
    public String findMessage(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, TwilioRestException {
        logger.log(Level.INFO, "Messages Reply Controller: inside findMessage");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String  body = request.getParameter("Body");
        String  fromNumber = request.getParameter("From");
        String  toNumber = request.getParameter("To");

        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("Body", body));
        params.add(new BasicNameValuePair("To", toNumber));
        params.add(new BasicNameValuePair("From", fromNumber));

        MessageFactory messageFactory = client.getAccount().getMessageFactory();
        Message message = messageFactory.create(params);
        logger.log(Level.INFO,"Message created: " + message);

        //Sender

        String  fromCountry = request.getParameter("FromCountry");
        String  accountSid = message.getAccountSid();

        //Message
        String  messageSid = message.getSid();
        String  smsMessageSid = message.getSid();
        String  smsStatus = message.getStatus();
        String  smsSid = message.getSid();
        Date dateSend = message.getDateCreated();

        //Receiver

        String  toState = request.getParameter("ToState");
        String  toCountry = request.getParameter("ToCountry");

        StoreDataService data = new StoreDataService(entityManager);
        data.addSender(accountSid, fromNumber, fromCountry);
        data.addMessage(messageSid, smsMessageSid, smsStatus, smsSid, body, dateSend, fromNumber, toNumber);
        data.addReceiver(messageSid, accountSid, toNumber, toState, toCountry);

        Counter messageNumberCounter = NumberCountQuery.countMessagesPerNumber(entityManager, toNumber);

        model.addAttribute("sender", SenderQuery.findSenderNumbers(entityManager, toNumber));
        model.addAttribute("messages", MessageReplyQuery.findSenderMessages(entityManager,toNumber));
        model.addAttribute("receiver", ReceiverQuery.findReceiverNumbers(entityManager, toNumber));
        model.addAttribute("messageNumberCounter", messageNumberCounter);
        model.addAttribute("messageAccountSid", ACCOUNT_SID);

        return MESSAGE_REPLY_VIEW;
    }
}