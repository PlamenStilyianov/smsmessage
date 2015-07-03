package com.fingenius.services.controllers;

import com.fingenius.json.Counter;
import com.fingenius.services.queries.MessageQuery;
import com.fingenius.services.queries.NumberCountQuery;
import com.fingenius.services.queries.ReceiverQuery;
import com.fingenius.services.queries.SenderQuery;
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

@Controller
@Transactional
public class MessagesController {
    private final static Logger logger = LogManager.getLogger(MessagesController.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Value("${FROM}")
    private String FROM;

    @Value("${ACCOUNT_SID}")
    private String ACCOUNT_SID;

    public static final String MESSAGE_VIEW = "messages";


    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    @Transactional
    public String findMessage(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        logger.log(Level.INFO, "Message Controller: inside findMessage");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String senderNumber = request.getParameter("number");
        String fromNumber = "+" + senderNumber.substring(1);
        String toNumber = FROM;
        model.addAttribute("sender", SenderQuery.findSenderNumbers(entityManager, fromNumber));
        model.addAttribute("messages", MessageQuery.findSenderMessages(entityManager,fromNumber));
        model.addAttribute("receiver", ReceiverQuery.findReceiverNumbers(entityManager,fromNumber));
        Counter messageNumberCounter = NumberCountQuery.countMessagesPerNumber(entityManager, fromNumber);
        model.addAttribute("messageNumberCounter", messageNumberCounter);
        model.addAttribute("messageAccountSid", ACCOUNT_SID);
        return MESSAGE_VIEW;
    }
}
