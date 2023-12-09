package com.example.hackitall2023;

import lombok.Getter;
import lombok.Value;

import java.util.List;

@Value
@Getter
public class User {
    String userLocation;
    List<Event> historyEvents;
    GreenCard greenCard;
}
