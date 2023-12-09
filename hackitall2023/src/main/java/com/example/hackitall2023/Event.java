package com.example.hackitall2023;

import lombok.Getter;
import lombok.Value;

import java.util.Date;
import java.util.List;

@Value
@Getter
public class Event {
    String description;
    List<String> participants;
    String location;
    Date date;
    List<String> items;
    List<String> announces;
    List<String> comments;
}
