package com.mycompany.mavenproject1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckCode {
      
    public String ReturnOutput(String usercode ) {
        // Replace with your JDoodle API details
        String clientId = "84dd61d0a68c60c421c0b7d74fa1c26";
        String clientSecret = "ef5b4b72e2bfd3e05e8bdf1faecba317162b9362ec218c0af5ec567604041be6";
        
        try {
            // API endpoint URL
            URL url = new URL("https://api.jdoodle.com/v1/execute");
            
            // Create an HTTP connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            
            String javaCode = addEscapedQuotes(usercode);
            // Create JSON input data for the API request
            String inputData = "{"
                    + "\"clientId\": \"" + clientId + "\","
                    + "\"clientSecret\": \"" + clientSecret + "\","
                    + "\"script\":\"" + javaCode + "\"," 
                    + "\"language\": \"" + "java" + "\","
                    + "\"versionIndex\": \"3\""
                    + "}";

            
            // Send the request
            try (OutputStream os = conn.getOutputStream()) {
                byte[] inputBytes = inputData.getBytes("utf-8");
                os.write(inputBytes, 0, inputBytes.length);
            }
            
            // Get the response
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }


                String inputString =  response.toString();
                
                System.out.println(inputString);
                int indexOfOutput = inputString.indexOf(":\"");
                  int indexOfBackslash = inputString.indexOf("\\n", indexOfOutput);
                System.out.println(indexOfOutput);

        System.out.println(indexOfBackslash);

        // Find the index of "\" after "\", starting from the index of "\"
        // int indexOfQuoteAfterBackslash = inputString.indexOf("\"", indexOfBackslash);

        // Extract the substring between "\", and "\""
        String extractedValue = inputString.substring(indexOfOutput + 2, indexOfBackslash);

        // Remove the newline character ("\n") from the extracted value
        extractedValue = extractedValue.replace("\\n", "");

        // Print the extracted value
        System.out.println("Extracted value: " + extractedValue);

                // System.out.println("Extracted Word: " + extractedWord);
                return extractedValue ;
            } finally {
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        
        }
         
        
    }
    public static String addEscapedQuotes(String input) {
        String result = "";

        for (char c : input.toCharArray()) {
            if (c == '\"') 
            {   
                result += "\\\"";
            } else 
            {
                result += c;
            }
        }

        return result;

    }


    
}
