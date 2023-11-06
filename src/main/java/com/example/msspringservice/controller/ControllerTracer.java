package com.example.msspringservice.controller;

import com.example.msspringservice.client.dto.Joke;
import com.example.msspringservice.dto.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.tracing.Tracer;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("v1")
public class ControllerTracer {

    @Autowired
    private Tracer tracer;

    @GetMapping(value = "hello")
    public ResponseObject hellWorld(){
        ResponseObject responseObject = new ResponseObject();
        responseObject.setResponse("Hello world, this is trace: " + tracer.currentSpan().context().traceId());
        responseObject.setTraceId(tracer.currentSpan().context().traceId());
        return responseObject;
    }

    @GetMapping(value ="joke")
    public Joke getJoke() {
        RestTemplate restTemplate = new RestTemplate();
        Joke joke = restTemplate.getForObject("https://official-joke-api.appspot.com/random_joke", Joke.class);
        ResponseObject responseObject = new ResponseObject();
        responseObject.setResponse(joke);
        responseObject.setTraceId(tracer.currentSpan().context().traceId());
        return joke;
    }

    @GetMapping(value = "hello-error")
    public String helloWorld2(){
        throw new RuntimeException(tracer.currentSpan().context().traceId());
    }


}
