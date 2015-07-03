package com.fingenius.services.controllers.login;

import com.fingenius.services.queries.UsersQuery;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DeleteUserController {
    private static final Logger logger = LogManager.getLogger(DeleteUserController.class.getName());
    public static final String USER_VIEWS = "redirect:/listusers.html";

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/deleteuser", method = { RequestMethod.GET, RequestMethod.POST })
    @Transactional
    public String deleteUser(HttpServletRequest request, HttpServletResponse response) {
        logger.log(Level.INFO,"Delete an user page !");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String userId = (String) request.getParameter("id");

        int deletedUsers = UsersQuery.deleteUser(entityManager, userId);

        return USER_VIEWS;
    }
}

