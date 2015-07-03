package com.fingenius.services.controllers.login;

import com.fingenius.services.data.StoreDataService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

@Controller
public class CreateUserController {
    private static final Logger logger = LogManager.getLogger(CreateUserController.class.getName());
    public static final String NEWUSER_VIEW = "createuser";
    public static final String USERS_VIEW = "redirect:/listusers.html";

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/createuser", method = RequestMethod.GET)
    public String createUser() {
        logger.log(Level.INFO,"User page !");

        return NEWUSER_VIEW;
    }

    /**
     * Create an user
     */
    @RequestMapping(value = "/createuser", method = RequestMethod.POST)
    @Transactional
    public String createUser(HttpServletRequest request, HttpServletResponse response, Model model) {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        logger.log(Level.INFO,"Create an user page !");
        String userName = (String) request.getParameter("userName");
        String userPassword = (String) request.getParameter("userPassword");
        StoreDataService service = new StoreDataService(entityManager);
        service.addUser(userName,userPassword);

        return USERS_VIEW;
    }
}
