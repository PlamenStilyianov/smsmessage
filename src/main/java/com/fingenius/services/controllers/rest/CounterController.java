package com.fingenius.services.controllers.rest;

import com.fingenius.json.Counter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value="/counter")
public class CounterController {
    private final static Logger logger = LogManager.getLogger(CounterController.class.getName());


    @RequestMapping(value="/number", method=RequestMethod.GET)
    public Counter getCounter(HttpServletRequest request){
        logger.log(Level.INFO, "Rest Counter Controller: inside getCounter()");
        Counter counter = (Counter) request.getSession().getServletContext().getAttribute("smsNumberCounter");

        return counter;
    }

}
