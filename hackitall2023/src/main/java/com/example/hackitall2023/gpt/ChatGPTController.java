package com.example.hackitall2023.gpt;

import com.example.hackitall2023.UpcomingEventsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class ChatGPTController {
    String apiKey = System.getenv("apikey");

    @PostMapping("/chatgpt")
    @ResponseBody
    public String getInformationAboutEvents(@RequestBody String input){
        String eventsDetails = UpcomingEventsService.events.stream().map(ev -> " Title is: " + ev.getTitle()
                + " Description is " + ev.getDescription()
        ).reduce("",(a,b)->a.concat(b));

        String s = chatGPT("We have the following events: " + eventsDetails + ". Based on these events details, " +
                "respond to the following question from a participant if it is related to them. Don't use any quotes in the response. " +
                "If he is asking another thing unrelated, give him a valuable response only considering the initial event details: " +
                input);
        return s;
    }

    public String chatGPT(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        String model = "gpt-3.5-turbo";

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // The request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // calls the method to extract the message.
            return extractMessageFromJSONResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content")+ 11;

        int end = response.indexOf("\"", start);

        return response.substring(start, end);

    }
}
