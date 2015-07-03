package com.fingenius.services.controllers.login;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginHomeController {
    private static final Logger logger = LogManager.getLogger(LoginHomeController.class.getName());
    public static final String HOME_VIEW = "loginhome";

    @RequestMapping(value = "/loginhome", method = RequestMethod.GET)
    public String loginhome() {
        logger.log(Level.INFO, "Login Home page !");

        return HOME_VIEW;
    }
}