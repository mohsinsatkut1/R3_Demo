package com.R3.Demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.time.Instant;

import org.json.JSONObject;

import com.R3.Demo.utils.*;


public class APITest {

	
	private static final String API_URL = "https://open.er-api.com/v6/latest/USD";
    private static final String SCHEMA_FILE_PATH = "src/test/resources/schema/APISchema.json";
    private static String response;

    public static void testAPICallSuccess() throws IOException {
        response = APIClient.get(API_URL);
        assertTrue("API call failed or returned invalid response", response != null && !response.isEmpty());
    }

    public static void testAPIStatusCodeAndStatus() throws IOException {
        
        assertEquals("API status code is not 200", "SUCCESS", APIResponseValidator.getStatusCode(response).toString());
        //assertTrue("API status is not SUCCESS", APIResponseValidator.getStatus(response).equalsIgnoreCase("SUCCESS"));
        
        assertTrue("API call failed or returned invalid response", response != null && !response.isEmpty());
    }
    
    public static void testAPIStatusCode(String statusCode) {
    	
    	 assertEquals("API status code is not as expected", statusCode.toString(), APIResponseValidator.getStatusCode(response).toString());
    }
    
    

    public static void testUSDPriceAgainstAEDInRange() throws IOException {
    	
        double usdToAED = JsonUtils.getDoubleFromJsonPath(response, "$.rates.AED");
        assertTrue("USD to AED price is not in range 3.6 - 3.7", usdToAED >= 3.6 && usdToAED <= 3.7);
    }

    public static void testAPIResponseTime() throws IOException {
        
    	long responseTimeInSeconds = APIResponseValidator.getResponseTimeInSeconds(API_URL);
    	long currentTimeInSeconds = APIResponseValidator.getCurrentTimeInSeconds();
      
        long differenceInSeconds = currentTimeInSeconds - responseTimeInSeconds;

        assertTrue("API response time is less than 3 seconds from current time", differenceInSeconds <= 3);
    }
    
    public static void testValidPrice() {
    	  
   	 assertTrue(APIResponseValidator.isValidPrice(response));
   }
    
    public static void testCurrencyPairsCount() throws IOException {
        response = APIClient.get(API_URL);
        int currencyPairsCount = JsonUtils.getCurrencyPairsCount(response);
        assertEquals("Number of currency pairs returned is not 162", 162, currencyPairsCount);
    }

    public static void testAPIResponseMatchesJsonSchema() throws IOException {
        try {
            String jsonSchema = JsonUtils.readJsonSchemaFromFile(SCHEMA_FILE_PATH);
            assertTrue("API response does not match JSON schema", APIResponseValidator.isResponseValidAgainstSchema(response, jsonSchema));
        } catch (IOException e) {
            fail("Error reading JSON schema file: " + e.getMessage());
        }
        
        
    }
    
   
}
