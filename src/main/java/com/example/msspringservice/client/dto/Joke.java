package com.example.msspringservice.client.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Joke {
    private Integer id;
    private String type;
    private String setup;
    private String punchline;
}
