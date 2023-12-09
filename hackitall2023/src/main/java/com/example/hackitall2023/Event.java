package com.example.hackitall2023;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Value
@Getter
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
}
