package io.changsoft.sis.cucumber;

import io.changsoft.sis.ChangsoftSisApp;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = ChangsoftSisApp.class)
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
