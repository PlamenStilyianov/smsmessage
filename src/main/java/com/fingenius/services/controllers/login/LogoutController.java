package com.fingenius.services.controllers.login;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;


@Controller
public class LogoutController {
    private static final Logger logger = LogManager.getLogger(LogoutController.class.getName());
    public static final String LOGIN_VIEW = "logout";

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        logger.log(Level.INFO,"Login page!");
        return LOGIN_VIEW;
    }

}
