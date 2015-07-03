package com.fingenius.test.services.spring.rest;

import com.fingenius.json.Counter;
import com.fingenius.services.controllers.rest.CounterController;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:test-rest-application-context.xml"})
@WebAppConfiguration
public class RestServiceTest {
    private final static Logger logger = LogManager.getLogger(RestServiceTest.class.getName());
    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private MockHttpSession session;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.session = new MockHttpSession();
        Counter counter = new Counter("07931703933",1);
        session.getServletContext().setAttribute("smsNumberCounter",counter);

        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();

    }

    @Test
    public void getCounter() throws Exception {
        logger.log(Level.INFO, "RestServiceTest: inside getCounter");
        int expectCount = 1;

        mockMvc.perform(get("/counter/number").session(session)
                .accept(MediaType.parseMediaType("application/json; charset=UTF-8 ")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.counter.count").value(expectCount));
    }

    @Configuration
    @EnableWebMvc
    public static class TestConfiguration {
        @Bean
        public CounterController contactController() {
            return new CounterController();
        }
    }

    @After
    public void tearDown(){
        if(session != null){
            session.getServletContext().removeAttribute("smsNumberCounter");
        }
    }

}
