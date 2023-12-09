package com.example.hackitall2023;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UpcomingEventsController {

    public static List<Event> events = Arrays.asList(
            new Event(1, "Community Tree Planting", "Join us for a day of tree planting at City Park, fostering a greener and healthier urban environment. Volunteers like Alice and Bob will guide participants in planting native trees. This hands-on experience is not only rewarding but also an educational opportunity to learn about local flora.", Arrays.asList("Alice", "Bob"), "City Park", LocalDate.of(2024, 3, 21), Arrays.asList("Gloves", "Shovel"), Arrays.asList("Planting starts at 9am"), Arrays.asList("Let's make our city greener!"), List.of("tree planting", "urban environment", "native trees","local flora"), 44.4322, 26.1062,100),
            new Event(2, "Beach Cleanup Drive", "Help us keep Seaside Beach pristine by participating in our Beach Cleanup Drive. The event, led by dedicated volunteers Dave and Eva, focuses on collecting and properly disposing of litter. It's a great way to make a tangible impact on marine life preservation and enjoy the beach's natural beauty.", Arrays.asList("Dave", "Eva"), "Seaside Beach", LocalDate.of(2024, 7, 5), Arrays.asList("Trash bags", "Rakes"), Arrays.asList("Meetup at 8am near the lifeguard station"), Arrays.asList("Help keep our beaches clean!"), List.of("beach cleanup", "marine life preservation", "litter collection", "natural beauty"), 46.3011, 27.1234,30),
            new Event(3, "Urban Gardening Workshop", "Our Urban Gardening Workshop, held at the Community Center, is perfect for city dwellers looking to grow their own food. Chris and Fiona will share insights on urban agricultural practices, from planting seeds to maintaining a thriving garden in small spaces.", Arrays.asList("Chris", "Fiona"), "Community Center", LocalDate.of(2024, 4, 18), Arrays.asList("Seeds", "Gardening tools"), Arrays.asList("Workshop begins at 10am"), Arrays.asList("Learn to grow your own food!"), List.of("urban gardening", "grow own food", "agricultural practices", "seeds", "small spaces"), 47.33, 24.0000,50),
            new Event(4, "River Cleanup Project", "Join Grace and Henry at Riverside for a River Cleanup Project to help protect our aquatic ecosystems. This event focuses on removing trash from the river, enhancing water quality, and safeguarding the habitat for local wildlife.", Arrays.asList("Grace", "Henry"), "Riverside", LocalDate.of(2024, 5, 11), Arrays.asList("Waterproof boots", "Trash picker"), Arrays.asList("Gathering at the old bridge at 7am"), Arrays.asList("Protect our river ecosystem!"), List.of("river cleanup", "aquatic ecosystems", "trash removal", "water quality", "Riverside", "local wildlife"), 44.5212, 26.2000,35),
            new Event(5, "Recycling Awareness Campaign", "Our Recycling Awareness Campaign at the Town Hall, spearheaded by Ivy and Jack, aims to educate the community about the importance of recycling. Through interactive activities and informative brochures, we'll explore effective waste management strategies.", Arrays.asList("Ivy", "Jack"), "Town Hall", LocalDate.of(2024, 9, 7), Arrays.asList("Informative brochures", "Recyclable materials"), Arrays.asList("Campaign all day"), Arrays.asList("Educate about recycling importance!"), List.of("recycling awareness", "waste management", "Town Hall", "community education"), 44.4123, 26.2600,20),
            new Event(6, "Neighborhood Park Restoration", "The Old Town Park needs your help! Join Karen and Leo in restoring and beautifying this cherished public space. This event is a collective effort to repaint, clean, and rejuvenate the park, making it a more welcoming place for everyone.", Arrays.asList("Karen", "Leo"), "Old Town Park", LocalDate.of(2024, 7, 9), Arrays.asList("Paint", "Brushes"), Arrays.asList("Restoration starts at noon"), Arrays.asList("Revitalize our local park!"), List.of("park restoration", "public space", "repaint", "clean", "rejuvenate", "Old Town Park"), 46.4646, 24.4242,60),
            new Event(7, "Wildlife Habitat Preservation Walk", "Explore and protect natural habitats with Mia and Nolan in our Wildlife Habitat Preservation Walk at the Nature Reserve. This educational walk highlights the importance of preserving habitats for local wildlife, promoting biodiversity, and enjoying the tranquility of nature.", Arrays.asList("Mia", "Nolan"), "Nature Reserve", LocalDate.of(2024, 8, 15), Arrays.asList("Binoculars", "Hiking boots"), Arrays.asList("Walk starts early at sunrise"), Arrays.asList("Discover and protect wildlife habitats!"), List.of("habitat preservation", "natural habitats", "local wildlife", "biodiversity", "Nature Reserve"), 45.1451, 26.5132,20),
            new Event(8, "Sustainable Living Expo", "Discover sustainable living practices at our Expo in the Central Hall. Experts like Anna and Tom will showcase eco-friendly home solutions, renewable energy, and sustainable agriculture techniques. It's a chance to learn about living in harmony with nature.", Arrays.asList("Anna", "Tom"), "Central Hall", LocalDate.of(2024, 10, 10), Arrays.asList("Eco-friendly products", "Informational pamphlets"), Arrays.asList("Expo starts at 11am"), Arrays.asList("Embrace a sustainable future!"), List.of("sustainable living", "eco-friendly solutions", "renewable energy", "sustainable agriculture"), 44.9375, 26.0365, 45),
            new Event(9, "City Cycling Initiative", "Join our City Cycling Initiative at Riverfront Park to promote eco-friendly transportation. Led by cycling enthusiasts Lisa and Mark, this event includes a workshop on bike maintenance, safe cycling practices, and a group city ride.", Arrays.asList("Lisa", "Mark"), "Riverfront Park", LocalDate.of(2024, 6, 20), Arrays.asList("Bike tools", "Safety gear"), Arrays.asList("Event kicks off at 9am"), Arrays.asList("Cycle your way to a greener city!"), List.of("cycling", "eco-friendly transportation", "bike maintenance", "safe cycling"), 44.9804, 26.0805, 30),
            new Event(10, "Green Tech Symposium", "Our Green Tech Symposium at the University Auditorium brings together innovators and thought leaders in green technology. Discussions led by Eva and Jim focus on clean energy, sustainable tech startups, and environmental conservation.", Arrays.asList("Eva", "Jim"), "University Auditorium", LocalDate.of(2024, 11, 5), Arrays.asList("Conference materials", "Tech demos"), Arrays.asList("Symposium begins at 10am"), Arrays.asList("Innovate for a greener tomorrow!"), List.of("green technology", "clean energy", "sustainable startups", "environmental conservation"), 44.4268, 26.1025, 50)
            );

    @GetMapping("/events")
    public List<Event> getAllEvents() {

        return events.stream()
                .sorted((e1, e2) -> e1.getDate().compareTo(e2.getDate()))
                .collect(Collectors.toList());
    }

    @GetMapping("/events/{id}")
    public Event getEventById(@PathVariable int id) {
        return events.stream()
                .filter(event -> event.getId() == id)
                .collect(Collectors.toList())
                .get(0);
    }
}
