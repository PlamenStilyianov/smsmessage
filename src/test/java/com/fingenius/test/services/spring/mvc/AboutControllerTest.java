package com.fingenius.test.services.spring.mvc;

import com.fingenius.services.controllers.AboutController;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.assertEquals;

/**
 * Created by plamen on 07/04/2015.
 **/
public class AboutControllerTest {
    private final static Logger logger = LogManager.getLogger(AboutControllerTest.class.getName());
    private AboutController controller;

    @Before
    public void setUp() {
        controller = new AboutController();
    }

    @Test
    public void about() {
      //  logger.log(Level.INFO, "AboutControllerTest: inside about");
        MockHttpServletResponse response = new MockHttpServletResponse();
        String view = controller.about(response);
        assertEquals(AboutController.ABOUT_VIEW, view);
    }
}
