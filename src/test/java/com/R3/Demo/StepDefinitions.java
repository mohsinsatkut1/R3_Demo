package com.R3.Demo;


import com.R3.Demo.utils.APIResponseValidator;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

public class StepDefinitions {
	
	private String response;

    @Given("User makes a GET request to the API")
    public void iMakeAGETRequestToTheAPI() throws IOException {
    	APITest.testAPICallSuccess();
    }

    @Then("the API call is successful")
    public void theAPICallIsSuccessful() throws IOException {
    	APITest.testAPIStatusCodeAndStatus();
        
    }

    @Then("the response contains valid price")
    public void theResponseContainsValidPrice() {
      
    	APITest.testValidPrice();
    }

    @Then("the status code is 200 and \"([^\"]*)\"$")
    public void theStatusCodeIs(String statusCode) throws IOException {
    	
    	APITest.testAPIStatusCode(statusCode); 
    	
    	}



    @Then("the USD price against AED is in range 3.6 - 3.7")
    public void theUSDPriceAgainstAEDIsInRange() throws IOException {
    	
    	APITest.testUSDPriceAgainstAEDInRange();


    }

    @Then("the API response time is not less than 3 seconds from current time")
    public void theAPIResponseTimeIsNotLessThan3SecondsFromCurrentTime() throws IOException {
    	
    	APITest.testAPIResponseTime();

    }

    @Then("162 currency pairs are returned by the API")
    public void currencyPairsAreReturnedByTheAPI() throws IOException {
       
    	APITest.testCurrencyPairsCount();
    }

    @Then("the API response matches the JSON schema")
    public void theAPIResponseMatchesTheJSONSchema() throws IOException {
    	
    	APITest.testAPIResponseMatchesJsonSchema();
       
    }
}


