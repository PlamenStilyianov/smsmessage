package com.fingenius.services.controllers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;


@Controller
public class AboutController {

    private static final Logger logger = LogManager.getLogger(AboutController.class.getName());
    public static final String ABOUT_VIEW = "about";

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about( HttpServletResponse response) {
        logger.log(Level.INFO,"About page !");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        return ABOUT_VIEW;
    }

}
