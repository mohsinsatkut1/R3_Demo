package com.R3.Demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class APIClient {

	private static final Logger logger = Logger.getLogger(APIClient.class.getName());

    public static String get(String apiUrl) {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            } else {
                logger.log(Level.WARNING, "Failed to fetch data from API. Response code: {0}", responseCode);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Exception occurred while fetching data from API", e);
        }

        return response.toString();
    }
}
