package com.example.hackitall2023;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
@Getter
@Setter
@RequiredArgsConstructor
public class Event {
    int id;
    String title;
    String description;
    List<String> participants;
    String location;
    @JsonFormat(pattern = "yyyy/MM/dd")
    LocalDate date;
    List<String> items;
    List<String> announces;
    List<String> comments;
    List<String> keywords;
    Double latitude;
    Double longitude;
    Integer points;
}
