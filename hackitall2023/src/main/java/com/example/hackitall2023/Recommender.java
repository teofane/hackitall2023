package com.example.hackitall2023;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Recommender {

    static List<String> stopWords = List.of("a", "about", "above", "across", "after", "again",
            "against", "all", "almost", "alone", "along", "already", "also",
            "although", "always", "among", "an", "and", "another", "any",
            "anybody", "anyone", "anything", "anywhere", "are", "area",
            "areas", "around", "as", "ask", "asked", "asking", "asks", "at",
            "away", "b", "back", "backed", "backing", "backs", "be", "became",
            "because", "become", "becomes", "been", "before", "began",
            "behind", "being", "beings", "best", "better", "between", "big",
            "both", "but", "by", "c", "came", "can", "cannot", "case", "cases",
            "certain", "certainly", "clear", "clearly", "come", "could", "d",
            "did", "differ", "different", "differently", "do", "does", "done",
            "down", "down", "downed", "downing", "downs", "during", "e",
            "each", "early", "either", "end", "ended", "ending", "ends",
            "enough", "even", "evenly", "ever", "every", "everybody",
            "everyone", "everything", "everywhere", "f", "face", "faces",
            "fact", "facts", "far", "felt", "few", "find", "finds", "first",
            "for", "four", "from", "full", "fully", "further", "furthered",
            "furthering", "furthers", "g", "gave", "general", "generally",
            "get", "gets", "give", "given", "gives", "go", "going", "good",
            "goods", "got", "great", "greater", "greatest", "group", "grouped",
            "grouping", "groups", "h", "had", "has", "have", "having", "he",
            "her", "here", "herself", "high", "high", "high", "higher",
            "highest", "him", "himself", "his", "how", "however", "i", "if",
            "important", "in", "interest", "interested", "interesting",
            "interests", "into", "is", "it", "its", "itself", "j", "just", "k",
            "keep", "keeps", "kind", "knew", "know", "known", "knows", "l",
            "large", "largely", "last", "later", "latest", "least", "less",
            "let", "lets", "like", "likely", "long", "longer", "longest", "m",
            "made", "make", "making", "man", "many", "may", "me", "member",
            "members", "men", "might", "more", "most", "mostly", "mr", "mrs",
            "much", "must", "my", "myself", "n", "necessary", "need", "needed",
            "needing", "needs", "never", "new", "new", "newer", "newest",
            "next", "no", "nobody", "non", "noone", "not", "nothing", "now",
            "nowhere", "number", "numbers", "o", "of", "off", "often", "old",
            "older", "oldest", "on", "once", "one", "only", "open", "opened",
            "opening", "opens", "or", "order", "ordered", "ordering", "orders",
            "other", "others", "our", "out", "over", "p", "part", "parted",
            "parting", "parts", "per", "perhaps", "place", "places", "point",
            "pointed", "pointing", "points", "possible", "present",
            "presented", "presenting", "presents", "problem", "problems",
            "put", "puts", "q", "quite", "r", "rather", "really", "right",
            "right", "room", "rooms", "s", "said", "same", "saw", "say",
            "says", "second", "seconds", "see", "seem", "seemed", "seeming",
            "seems", "sees", "several", "shall", "she", "should", "show",
            "showed", "showing", "shows", "side", "sides", "since", "small",
            "smaller", "smallest", "so", "some", "somebody", "someone",
            "something", "somewhere", "state", "states", "still", "still",
            "such", "sure", "t", "take", "taken", "than", "that", "the",
            "their", "them", "then", "there", "therefore", "these", "they",
            "thing", "things", "think", "thinks", "this", "those", "though",
            "thought", "thoughts", "three", "through", "thus", "to", "today",
            "together", "too", "took", "toward", "turn", "turned", "turning",
            "turns", "two", "u", "under", "until", "up", "upon", "us", "use",
            "used", "uses", "v", "very", "w", "want", "wanted", "wanting",
            "wants", "was", "way", "ways", "we", "well", "wells", "went",
            "were", "what", "when", "where", "whether", "which", "while",
            "who", "whole", "whose", "why", "will", "with", "within",
            "without", "work", "worked", "working", "works", "would", "x", "y",
            "year", "years", "yet", "you", "young", "younger", "youngest",
            "your", "yours", "z");

    public Event getEventById(@PathVariable int id) {
        return UpcomingEventsController.events.stream()
                .filter(event -> event.getId() == id)
                .collect(Collectors.toList())
                .get(0);
    }

    @GetMapping("/recommandetion")
    public Event getRecommendedEvent() {
        List<Event> userEvents = UserController.userEvents;
        List<Event> upcomingEvents = UpcomingEventsController.events;
        return getEventById(calculateCosineSimilarity(userEvents, upcomingEvents));
    }

    public static Integer calculateCosineSimilarity(List<Event> usersEvents, List<Event> upcomingEvents) {
        List<String> userListOfWords = new ArrayList<>();
        double latitudeAvg = 0.0;
        double longitudeAvg = 0.0;

        for (Event event : usersEvents) {
            latitudeAvg += event.getLatitude();
            longitudeAvg += event.getLongitude();
        }
        longitudeAvg /= usersEvents.size();
        latitudeAvg /= usersEvents.size();

        Map<Integer, Double> cosinesAndEvents = new HashMap<>();

        for (Event event : usersEvents) {
            String[] wordsInPhrase = event.getDescription().split("\\s+");
            for (String s : wordsInPhrase) {
                if (!stopWords.contains(s)) {
                    userListOfWords.add(s.toLowerCase());
                }
            }
        }
        for (Event event : upcomingEvents) {
            String[] wordsInPhrase = event.getDescription().split("\\W+");
            Set<String> set = new HashSet<>();
            for (String word : wordsInPhrase) {
                set.add(word.toLowerCase());
            }
            Set<String> intersection = new HashSet<>(userListOfWords);
            intersection.retainAll(set);
            Set<String> union = new HashSet<>(userListOfWords);
            union.addAll(set);

            if (union.size() == 0) {
                cosinesAndEvents.put(event.getId(), 0.0);
            } else {

                Double latDiff = (1 - (event.getLatitude() - latitudeAvg) / 4.5) / 4;
                Double longDiff = (1 - (event.getLongitude() - longitudeAvg) / 7.5) / 4; // intre 0 si 0.25
                cosinesAndEvents.put(event.getId(), (double) (intersection.size() / (union.size() * 0.5)) + Math.abs(latDiff) + Math.abs(longDiff)); // intre 0 si 0.5
            }
        }

        return Collections.max(cosinesAndEvents.entrySet(), Map.Entry.comparingByValue()).getKey();
    }


}

