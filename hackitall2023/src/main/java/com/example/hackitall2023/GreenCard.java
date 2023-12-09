package com.example.hackitall2023;

import lombok.Getter;
import lombok.Value;

import java.util.List;

@Getter
@Value
public class GreenCard {
    int numberPoints;
    List<String> badges;


}
