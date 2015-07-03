package com.fingenius.services.controllers.login;

import com.fingenius.services.queries.UsersQuery;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class ListUsersController {
    private static final Logger logger = LogManager.getLogger(ListUsersController.class.getName());
    public static final String LIST_USERS_VIEW = "listusers";

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * List users
     */
    @RequestMapping(value = "/listusers", method = RequestMethod.GET)
    @Transactional
    public String listUsers(Model model, HttpServletResponse response) throws IOException {
        logger.log(Level.INFO,"List users !");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        model.addAttribute("users", UsersQuery.listUsers(entityManager));
        return LIST_USERS_VIEW;
    }
}
