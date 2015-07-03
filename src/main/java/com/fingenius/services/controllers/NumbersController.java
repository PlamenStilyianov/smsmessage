package com.fingenius.services.controllers;

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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Transactional
public class NumbersController {
    private final static Logger logger = LogManager.getLogger(NumbersController.class.getName());
    @PersistenceContext
    private EntityManager entityManager;

    @Value("${FROM}")
    private String FROM;

    protected static final String NUMBER_VIEW = "numbers";


    @RequestMapping(value = "/numbers", method = RequestMethod.GET)
    @Transactional
    public String findAllNumbers(Model model, HttpServletResponse response) throws IOException {
        logger.log(Level.INFO, "Number Controller: inside findAllNumbers");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String fromNumber = FROM;
        model.addAttribute("senders", SenderQuery.findSenderNumbersWithoutFrom(entityManager, fromNumber));

        return NUMBER_VIEW;
    }
}
