package com.example.distributed.tracing.microservice3;

import brave.sampler.Sampler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class Microservice3Controller {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    @GetMapping(value = "/microservice3")
    public String method3() {
        LOG.info("Inside method3");
        String baseUrl = "http://localhost:8083/microservice4";
        String response = (String) restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class).getBody();
        LOG.info("The response received by method3 is " + response);
        return response;
    }
}

