package com.R3.Demo;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/feature_files/APITest.feature"},
		glue= {"com.R3.Demo"},
	    stepNotifications = true,
		plugin = {"pretty", "html:target/cucumber-reports", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
		dryRun = false,
		monochrome = true
		//tags = ("@test")
		)

public class RunCucumberTest {

}
