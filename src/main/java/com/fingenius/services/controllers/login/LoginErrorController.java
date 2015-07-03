package com.fingenius.services.controllers.login;

import com.fingenius.services.queries.UsersQuery;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginErrorController {
    private static final Logger logger = LogManager.getLogger(LoginErrorController.class.getName());
    public static final String LOGIN_VIEW = "redirect:/login.html";



    @RequestMapping(value = "/loginerror", method = RequestMethod.GET)
    @Transactional
    public String login(HttpServletRequest request, Model model) {
        logger.log(Level.INFO, "Login post page!");
        String userName = (String) request.getParameter("username");
        String userPassword = (String) request.getParameter("password");
        String role = (String) request.getParameter("role");

        return LOGIN_VIEW;
    }

}
