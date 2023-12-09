package com.example.hackitall2023;

import com.example.hackitall2023.gpt.ChatGPTKeywords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    public static List<Event> userEvents = Arrays.asList(
            new Event(8, "Solar Energy Seminar", "Learn about the potential of solar energy at our engaging seminar in the Science Museum. Presented by Olive and Paul, this event will cover the basics of solar power, its benefits, and how to incorporate solar energy solutions into everyday life.", Arrays.asList("Olive", "Paul"), "Science Museum", LocalDate.of(2022, 4, 22), Arrays.asList("Notebook", "Solar device models"), Arrays.asList("Seminar begins at 2pm"), Arrays.asList("Learn about renewable energy!"), List.of("solar energy", "seminar", "Science Museum", "benefits", "everyday life"), 45.2012, 26.5123,20),
            new Event(9, "Citywide Recycling Drive", "Participate in our Citywide Recycling Drive to make a significant environmental impact. Quinn and Rachel will lead the initiative across various locations, focusing on effective sorting and recycling practices to reduce waste and promote sustainability.", Arrays.asList("Quinn", "Rachel"), "Various locations", LocalDate.of(2021, 10, 5), Arrays.asList("Recycling bins", "Sorting gloves"), Arrays.asList("Drive lasts all weekend"), Arrays.asList("Join us in recycling efforts!"), List.of("citywide recycling", "waste reduction", "sustainability"), 44.2890, 26.3122,50),
            new Event(10, "Eco-Friendly Lifestyle Workshop", "Join us at the Community Library for an Eco-Friendly Lifestyle Workshop led by Sam and Tina. This workshop will provide practical tips and strategies for adopting a sustainable lifestyle, from choosing eco-friendly products to reducing personal carbon footprints.", Arrays.asList("Sam", "Tina"), "Community Library", LocalDate.of(2023, 7, 19), Arrays.asList("Reusable items", "Eco-friendly products"), Arrays.asList("Workshop at 1pm"), Arrays.asList("Explore sustainable living!"), List.of("eco-friendly lifestyle", "sustainable living", "Community Library", "eco-friendly products", "carbon footprints"), 46.2312, 25.6712,25)
    );

    @GetMapping("/user/events")
    public List<Event> getUserEvents() {

        return userEvents.stream()
                .sorted(Comparator.comparing(Event::getDate))
                .collect(Collectors.toList());
    }
}
