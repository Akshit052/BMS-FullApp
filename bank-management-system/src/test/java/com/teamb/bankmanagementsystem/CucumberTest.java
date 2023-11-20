package com.teamb.bankmanagementsystem;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@SpringBootTest
@CucumberOptions(
        features = "src/test/resources/com.teamb.bankmanagementsystem.features",
        glue = "com.teamb.bankmanagementsystem.stepdefinitions"
)
public class CucumberTest {
}
