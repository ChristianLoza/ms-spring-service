package com.example.msspringservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
@ToString
public class ResponseObject {
    private Object response;
    private String traceId;
}
