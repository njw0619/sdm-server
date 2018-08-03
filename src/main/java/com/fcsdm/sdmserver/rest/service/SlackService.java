package com.fcsdm.sdmserver.rest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class SlackService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${slack.api.token}")
    String token;

    @Value("${slack.test.channel.id}")
    String channelId;

    public void sendMessageToSlack(String uri, String message){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        String params = "{\"text\":\"" + message + "\"}";

        HttpEntity<String> entity = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        log.info("Send to Slack, Response={}", response.getBody());

    }
}
