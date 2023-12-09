package com.example.hackitall2023.gpt;

import com.example.hackitall2023.UpcomingEventsController;
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

import static com.example.hackitall2023.gpt.ChatGPTController.extractMessageFromJSONResponse;

@Controller
public class ChatGPTKeywords {
    String apiKey = System.getenv("apikey");

    @PostMapping("/keywords")
    @ResponseBody
    public String getInformationAboutEvents(@RequestBody String input) {
        String eventsDetails = UpcomingEventsController.events.stream().map(ev -> " Description is " + ev.getDescription()
        ).reduce("", (a, b) -> a.concat(b));

        String s = keywords(input);
        return s;
    }

    public String keywords(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // The request body
            String body = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [ {\n" +
                    "\"role\": \"system\",\n" +
                    "\"content\": \"You will be provided with a block of text, and your task is to extract a list of keywords from it.\"\n" +
                    "},{\"role\": \"user\", \"content\": \"" + prompt + "\"}],\n" +
                    "\"max_tokens\": 20,\n" +
                    "\"top_p\": 1}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            return extractMessageFromJSONResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
