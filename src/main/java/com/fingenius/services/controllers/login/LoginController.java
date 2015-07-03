package com.fingenius.services.controllers.login;

import com.fingenius.services.queries.UsersQuery;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private static final Logger logger = LogManager.getLogger(LoginController.class.getName());
    public static final String LOGIN_VIEW = "login";
    public static final String LG_HOME_VIEW = "redirect:/loginhome.html";
    public static final String HOME_VIEW = "redirect:/home.html";


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        logger.log(Level.INFO,"Login page!");
        return LOGIN_VIEW;
    }


    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public String login(HttpServletRequest request, Authentication authentication) {
        String VIEW = null;
        String admin_page = "loginhome";
        String user_page = "home";
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String role = userDetails.getAuthorities().isEmpty() ? null : userDetails.getAuthorities().toArray()[0]
                .toString();

        HttpSession session = request.getSession();
        if(session.getAttribute("page") != null)
            session.removeAttribute("page");

        if(role.equals("ROLE_USER")) {
            VIEW = HOME_VIEW;
            session.setAttribute("page",user_page);
        }else if (role.equals("ROLE_ADMIN")) {
            VIEW = LG_HOME_VIEW;
            session.setAttribute("page",admin_page);
        }
        return VIEW;
    }

}
