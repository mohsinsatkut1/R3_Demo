package com.R3.Demo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.logging.Level;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;



public class APIResponseValidator {

	private static final Logger logger = LoggerFactory.getLogger(APIResponseValidator.class);

    public static StatusCode getStatusCode(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            if (jsonResponse.has("result")) {
                String statusCode = jsonResponse.getString("result");
                switch (statusCode) {
                    case "success":
                        return StatusCode.SUCCESS;
                    case "failure":
                        return StatusCode.FAILURE;
                    case "pending":
                        return StatusCode.PENDING;
                    default:
                        return StatusCode.UNKNOWN;
                }
            } else {
                return StatusCode.UNKNOWN;
            }
        } catch (JSONException e) {
            logger.error("Error occurred while parsing JSON response: {}", e.getMessage());
            return StatusCode.UNKNOWN;
        }
    }

    public static String getStatus(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            if (jsonResponse.has("result")) {
                return jsonResponse.getString("result");
            } else {
                return null;
            }
        } catch (JSONException e) {
            logger.error("Error occurred while parsing JSON response: {}", e.getMessage());
            return null;
        }
    }
    
    public static boolean isValidPrice(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            JSONObject ratesObject = jsonResponse.optJSONObject("rates"); // Use optJSONObject to handle null case

            if (ratesObject != null) {
                for (String key : ratesObject.keySet()) {
                    double price = ratesObject.optDouble(key); // Use optDouble to handle NaN or non-existent keys

                    // Check if the price is NaN, infinite, or zero/negative
                    if (Double.isNaN(price) || Double.isInfinite(price) || price <= 0) {
                        return false; // Invalid price
                    }
                }
                return true; // All prices are valid
            } else {
                logger.warn("No rates object found in the response.");
            }
        } catch (JSONException e) {
            logger.error("Error occurred while parsing JSON response: {}", e.getMessage());
        }
        return false; // JSONException occurred or rates object is null
    }
	
    public static long getResponseTimeInSeconds(String response) throws IOException {
      
    
        APIClient.get(response);
       
        long endTime = Instant.now().getEpochSecond();
        
        return endTime;
    }
    
    public static long getCurrentTimeInSeconds() {
        try {
            return Instant.now().getEpochSecond();
        } catch (Exception e) {
            // Log the exception
            logger.error("Exception occurred while getting current time: {}", e.getMessage(), e);
            // Return a default value or handle the exception as required
            return -1; // Default value or handle the exception appropriately
        }
    }
    
    public static boolean isResponseValidAgainstSchema(String response, String jsonSchema) throws IOException {
        try {
            // Parse JSON schema
        	InputStream schemaInputStream = APIResponseValidator.class.getClassLoader().getResourceAsStream(jsonSchema);
            JSONObject rawSchema = new JSONObject(new JSONTokener(schemaInputStream));
            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonSchema schema = factory.getJsonSchema(JsonLoader.fromString(rawSchema.toString()));

            // Parse API response
            JSONObject jsonResponse = new JSONObject(response);

            // Validate response against schema
            ProcessingReport report = schema.validate(JsonLoader.fromString(jsonResponse.toString()));

            return report.isSuccess(); // Response is valid against schema if the report indicates success
        } catch (ProcessingException e) {
            e.printStackTrace();
            return false; // Response is not valid against schema
        }
    }
}
