package com.fingenius.services.controllers.login;

import com.fingenius.services.queries.UsersQuery;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PostAuthorize;
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
public class EditUserController {
    private static final Logger logger = LogManager.getLogger(EditUserController.class.getName());
    private static final String EDIT_USER_VIEW = "edituser";
    private static final String USERS_VIEW = "redirect:/listusers.html";

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Edit existing user.
     */
    @RequestMapping(value = "/edituser", method = RequestMethod.GET)
    @Transactional
    public String editUser(HttpServletRequest request, Model model) {
        logger.log(Level.INFO,"Edit existing user.");
        String userId = (String) request.getParameter("id");

        model.addAttribute("user", UsersQuery.findUserById(entityManager, userId));

        return EDIT_USER_VIEW;
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    @Transactional
    public String updateUser(HttpServletRequest request, HttpServletResponse response) {
        logger.log(Level.INFO,"Edit existing user.");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String userId = (String) request.getParameter("id");
        String userPassword = (String) request.getParameter("userpassword");

        int updatedUsers = UsersQuery.editUser(entityManager, userId, userPassword);

        return USERS_VIEW;
    }
}
