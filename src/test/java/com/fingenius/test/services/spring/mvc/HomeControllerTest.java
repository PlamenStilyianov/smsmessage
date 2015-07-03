package com.fingenius.test.services.spring.mvc;

import com.fingenius.services.controllers.HomeController;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:test-jsp-servlet-context.xml"})
@WebAppConfiguration
public class HomeControllerTest {
    private final static Logger logger = LogManager.getLogger(AboutControllerTest.class.getName());
    private HomeController controller;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private MockHttpServletRequest request;


    @Before
    public void setUp() {
        controller = new HomeController();
    }

    @Test
    public void home() {
        logger.log(Level.INFO, "IndexControllerTest: inside home");
        this.request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        String view = controller.home(request, response);
        assertEquals(HomeController.HOME_VIEW, view);
    }
}
