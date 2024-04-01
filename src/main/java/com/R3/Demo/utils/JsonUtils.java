package com.R3.Demo.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.jsonpath.JsonPath;

public class JsonUtils {

	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static double getDoubleFromJsonPath(String json, String jsonPath) {
        try {
            Object result = JsonPath.read(json, jsonPath);
            if (result instanceof Number) {
                return ((Number) result).doubleValue();
            } else if (result instanceof String) {
                return Double.parseDouble((String) result);
            } else {
                throw new IllegalArgumentException("Unexpected JSON path result type: " + result.getClass());
            }
        } catch (NumberFormatException e) {
            logger.error("Error parsing double value from JSON path: {}", e.getMessage(), e);
            return Double.NaN; // Return NaN if value cannot be parsed
        } catch (IllegalArgumentException | com.jayway.jsonpath.PathNotFoundException e) {
            logger.error("Error extracting value from JSON path: {}", e.getMessage(), e);
            return Double.NaN; // Return NaN if value cannot be extracted
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage(), e);
            return Double.NaN; // Return NaN for any other unexpected errors
        }
    }

	public static JSONObject getJsonObject(String jsonString) {
        
        try {
        	JSONObject jsonObject = new JSONObject(jsonString);
            return new JSONObject(jsonString);
        } catch (Exception e) {
            logger.error("Error creating JSONObject: {}", e.getMessage(), e);
            return null;
        }
    }
    
	
	public static int getCurrencyPairsCount(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject ratesObject = jsonObject.getJSONObject("rates");
            return ratesObject.length();
        } catch (Exception e) {
            logger.error("Error getting currency pairs count: {}", e.getMessage(), e);
            return -1; // Return -1 to indicate an error
        }
    }

	public static String readJsonSchemaFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            logger.error("Error reading JSON schema from file: {}", e.getMessage(), e);
            return null;
        }
    }
}
